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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonRaceTests {
    GameSession gm;
    AutomaticIO io;

    @BeforeEach
    public void init(){
        io = new AutomaticIO(new ArrayList<>());
        gm = new GameSession(new IOControl(io, io));
        gm.addMoney(300);
        gm.changeRep(30);
        CatalogOfParts.init();
        CatalogOfEmployees.init();
        CatalogOfRaces.init();
    }

    @DisplayName("Проверка на запись гонки и обновление статистики")
    @Test
    public void raceTest(){
        Race race = CatalogOfRaces.allCatalog.get(1);
        int archiveBefore = gm.archive().size();

        Racecar car = new Racecar(
                Long.MIN_VALUE,
                "Болид",
                (Chassis)ChassisList.woodenBox.getCopy(),
                (Engine)EnginesList.pedal.getCopy(),
                (Transmission) TransmissionList.chain.getCopy(),
                (Wheels) WheelsList.bicycleWheels.getCopy(),
                null,
                null
        );

        Team ts = new Team("Игрок", car, (Pilot)PilotList.cheboks.getCopy());
        race.putTeam(ts);

        TabsHandler.scheduling(new RaceProcessTab(gm, race), 1);

        int archiveAfter = gm.archive().size();

        assertEquals(archiveBefore + 1, archiveAfter);

        ArchiveRecord ar = gm.archive().getLast();
        assertEquals("Жигулёвские горы", ar.getMapName());
        assertEquals("Болид", ar.getCarName());
        assertEquals("Сын фермера ", ar.getPilotName());

        //инцидент на игроке и инцидент на боте
        //проверка на то, чтобы бот с лучшими характеристиками побеждал игрока
        //
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @DisplayName("Проверка влияния характеристик на победу (сильный бот побеждает слабого игрока)")
    @Test
    public void characteristicsImpactOnVictoryTest() {
        Racecar playerBadCar = new Racecar(
                1000L, "Болид игрока",
                (Chassis) ChassisList.woodenBox.getCopy(),
                (Engine) EnginesList.pedal.getCopy(),
                (Transmission) TransmissionList.chain.getCopy(),
                (Wheels) WheelsList.bicycleWheels.getCopy(),
                null, null
        );
        Pilot weakPilot = (Pilot) PilotList.cheboks.getCopy();

        // Создаём сильную машину для бота
        Racecar botCoolCar = new Racecar(
                1001L, "Болид бота",
                (Chassis) ChassisList.ladaTazik.getCopy(),
                (Engine) EnginesList.lada.getCopy(),
                (Transmission) TransmissionList.ladabox.getCopy(),
                (Wheels) WheelsList.normWheels.getCopy(),
                null, null
        );
        Pilot strongPilot = (Pilot) PilotList.schumacher.getCopy();

        for (Part p : playerBadCar.getNotNullParts()) {
            p.setConnectionReliability(50);
        }
        for (Part p : botCoolCar.getNotNullParts()) {
            p.setConnectionReliability(80);
        }

        Race testRace = new Race(
                new RaceTrack("Учебный полигон", List.of(
                        new StraightRoad(80, SurfaceType.ASPHALT),
                        new TurnRoad(40, 60, SurfaceType.ASPHALT),
                        new StraightRoad(80, SurfaceType.GRAVEL),
                        new TurnRoad(40, 60, SurfaceType.GRAVEL),
                        new StraightRoad(80, SurfaceType.FIELD),
                        new TurnRoad(40, 60, SurfaceType.FIELD)
                )),
                0,
                0,
                2
        );

        testRace.putTeam(new Team("Игрок", playerBadCar, weakPilot));
        testRace.putTeam(new Team("Бот", botCoolCar, strongPilot));

        TabsHandler.scheduling(new RaceProcessTab(gm, testRace), 1);

        //важно: обе команды не выбыли
        assertEquals(2, testRace.getTeamsNames().size());

        ArchiveRecord ar = gm.archive().getLast();
        assertEquals("2/2", ar.getPlace());
    }
}
