package tests.race;

import data.catalogs.CatalogOfEmployees;
import data.catalogs.CatalogOfParts;
import data.catalogs.CatalogOfRaces;
import data.crew.Pilot;
import data.employeeslists.PilotList;
import data.parts.*;
import data.partslists.ChassisList;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import data.partslists.WheelsList;
import data.race.map.RaceTrack;
import data.race.map.enums.SurfaceType;
import data.race.map.terrains.StraightRoad;
import data.race.map.terrains.TurnRoad;
import data.race.teams.Team;
import data.special.ArchiveRecord;
import data.race.Race;
import data.race.teams.TeamSample;
import data.racecar.*;
import game.GameSession;
import iosystem.AutomaticIO;
import iosystem.IOControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TabsHandler;
import ui.championship.RaceProcessTab;
import ui.championship.initializing.UserTeamCreationTab;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentsRaceTest {
    GameSession gm;
    AutomaticIO io;

    @BeforeEach
    public void init(){
        io = new AutomaticIO(new ArrayList<>(List.of("+","1")));
        gm = new GameSession(new IOControl(io, io));
        gm.addMoney(300);
        gm.changeRep(30);
        CatalogOfParts.init();
        CatalogOfEmployees.init();
        CatalogOfRaces.init();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @DisplayName("Проверка инцидента игрока")
    @Test
    public void playerIncidentTest() {
        Race race = CatalogOfRaces.allCatalog.getFirst();

        Chassis brokenChassis = (Chassis) ChassisList.woodenBox.getCopy();
        Engine brokenEngine = (Engine) EnginesList.pedal.getCopy();
        Transmission brokenTransmission = (Transmission) TransmissionList.chain.getCopy();
        Wheels brokenWheels = (Wheels) WheelsList.bicycleWheels.getCopy();

        try {
            brokenChassis.setDamage(99);
            brokenEngine.setDamage(99);
            brokenTransmission.setDamage(99);
            brokenWheels.setDamage(99);
        } catch (PartBrokeException e) {
            fail("Возникает ошибка при установке урона в 99, хотя не должно");
        }

        Racecar brokenCar = new Racecar(
                1000, "Болид",
                brokenChassis, brokenEngine, brokenTransmission, brokenWheels,
                null, null
        );

        Pilot pilot = (Pilot) PilotList.cheboks.getCopy();

        TeamSample ts = new TeamSample();
        ts.setCar(brokenCar);
        ts.setPilot(pilot);

        int moneyBefore = gm.getMoney();
        int repBefore = gm.getRep();

        TabsHandler.scheduling(new UserTeamCreationTab(gm, ts, race), 3);

        assertTrue(gm.archive().getLast().getPlace().startsWith("0/"));
        assertEquals(repBefore - 1, gm.getRep());
        assertEquals(moneyBefore, gm.getMoney());
    }


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @DisplayName("Проверка инцидента бота")
    @Test
    public void botIncidentTest() {

        Racecar playerCar = new Racecar(
                1000L, "Болид",
                (Chassis) ChassisList.woodenBox.getCopy(),
                (Engine) EnginesList.pedal.getCopy(),
                (Transmission) TransmissionList.chain.getCopy(),
                (Wheels) WheelsList.bicycleWheels.getCopy(),
                null, null
        );
        Pilot playerPilot = (Pilot) PilotList.cheboks.getCopy();

        Chassis brokenChassis = (Chassis) ChassisList.woodenBox.getCopy();
        Engine brokenEngine = (Engine) EnginesList.pedal.getCopy();
        Transmission brokenTransmission = (Transmission) TransmissionList.chain.getCopy();
        Wheels brokenWheels = (Wheels) WheelsList.bicycleWheels.getCopy();

        try {
            brokenChassis.setDamage(99);
            brokenEngine.setDamage(99);
            brokenTransmission.setDamage(99);
            brokenWheels.setDamage(99);
        } catch (PartBrokeException e) {
            fail("Возникает ошибка при установке урона в 99, хотя не должно");
        }

        Racecar botCar = new Racecar(
                1001L, "Болид бота",
                brokenChassis, brokenEngine, brokenTransmission, brokenWheels,
                null, null
        );

        Pilot botPilot = (Pilot) PilotList.cheboks.getCopy();

        Race testRace = new Race(
                new RaceTrack("Учебный полигон", List.of(
                        new StraightRoad(80, SurfaceType.ASPHALT),
                        new TurnRoad(40, 60, SurfaceType.ASPHALT),
                        new StraightRoad(80, SurfaceType.GRAVEL),
                        new TurnRoad(40, 60, SurfaceType.GRAVEL),
                        new StraightRoad(80, SurfaceType.FIELD),
                        new TurnRoad(40, 60, SurfaceType.FIELD)
                )
                ),
                0,
                0,
                2
        );

        testRace.putTeam(new Team("Игрок", playerCar, playerPilot));
        testRace.putTeam(new Team("Бот", botCar, botPilot));

        TabsHandler.scheduling(new RaceProcessTab(gm, testRace), 1);

        //Всего 2 команды - игрок и бот. Если бот выбыл, то число команд = 1 и
        //первая команда - это команда игрока
        assertEquals(1, testRace.getTeamsNames().size());
        assertEquals("Игрок", testRace.getTeamsNames().getFirst());
    }
}
