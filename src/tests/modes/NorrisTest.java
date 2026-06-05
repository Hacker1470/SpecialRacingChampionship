package tests.modes;

import data.catalogs.CatalogOfEmployees;
import data.catalogs.CatalogOfParts;
import data.catalogs.CatalogOfRaces;
import data.crew.JobType;
import data.crew.Pilot;
import data.employeeslists.PilotList;
import data.parts.Chassis;
import data.parts.Engine;
import data.parts.Transmission;
import data.parts.Wheels;
import data.partslists.ChassisList;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import data.partslists.WheelsList;
import data.racecar.Racecar;
import game.GameModeNorris;
import game.GameSession;
import iosystem.AutomaticIO;
import iosystem.IOControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TabsHandler;
import ui.championship.ChampionshipTab;
import ui.employment.EmployeesByJobTab;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NorrisTest {
    GameSession gm;
    AutomaticIO io;

    @BeforeEach
    public void init(){
        CatalogOfParts.init();
        CatalogOfEmployees.init();
        CatalogOfRaces.init();
    }

    @DisplayName("Проверка на появление Норриса и перенос пилотов в больничку")
    @Test
    public void checkNorrisAppearanceTest(){
        io = new AutomaticIO(new ArrayList<>(List.of("0","0")));
        gm = new GameSession(new IOControl(io, io));
        gm.addMoney(300);
        gm.changeRep(30);
        gm.dorm().put(PilotList.cheboks.getCopy(Long.MIN_VALUE + 1));
        gm.setGameMode(GameModeNorris.CHUCK_NORRIS_APPEARANCE);

        assertNull(gm.hospital());
        int pilotsAtDormBefore = gm.dorm().getAllEmployees().size();

        TabsHandler.scheduling(new EmployeesByJobTab(gm, JobType.PILOT), 3);

        assertEquals("[1] Chuck Norris\n", io.getOutput().get(6));
        assertTrue(io.getOutput().contains("Все ваши нанятые пилоты перемещены в госпиталь.\n"));
        assertEquals(0, gm.dorm().getAllEmployees().size());
        assertNotNull(gm.hospital());
        assertEquals(pilotsAtDormBefore, gm.hospital().getAllEmployees().size());
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @DisplayName("Проверка работы гонки при режиме Чака Норриса")
    @Test
    public void norrisVictoryRaceTest() {
        //1 - выбор случ гонки
        //1 - согласие со случ гонкой
        //1 - меню выбрать болид
        //1 - выбрать болид
        //2 - выбрать пилота -> варн: чак норрис
        //+ - перейти в гонку
        io = new AutomaticIO(new ArrayList<>(List.of("1", "1", "1", "1", "2", "+", "1")));
        gm = new GameSession(new IOControl(io, io));
        gm.addMoney(10000);
        gm.changeRep(50);

        gm.setGameMode(GameModeNorris.CHUCK_NORRIS_ACTIVE);
        gm.dorm().put(new Pilot(
                Long.MAX_VALUE, "chuck_norris", "Chuck Norris", 1,
                0, 0, 10, 90, 1)
        );

        // Добавляем машину в гараж
        Racecar car = new Racecar(
                1000L, "Тестовая машина",
                (Chassis) ChassisList.ladaTazik.getCopy(),
                (Engine) EnginesList.lada.getCopy(),
                (Transmission) TransmissionList.ladabox.getCopy(),
                (Wheels) WheelsList.normWheels.getCopy(),
                null, null
        );

        gm.garage().put(car);
        gm.dorm().put(PilotList.cheboks.getCopy(1234L));

        int moneyBefore = gm.getMoney();
        int archiveBefore = gm.archive().size();

        TabsHandler.scheduling(new ChampionshipTab(gm), 8);

        ArrayList<String> output = io.getOutput();
        assertTrue(output.contains("У вас уже есть пилот - Чак Норрис\n"));
        assertTrue(output.contains("В этой гонке награды распределятся по усмотрению Норриса\n"));

        //гонка с Норрисом учлась в архиве
        assertEquals(archiveBefore + 1, gm.archive().size());

        //режим после гонки с Норрисом вернулся обратно
        assertEquals(GameModeNorris.NORMAL, gm.getGameModeNorris());

        //Норрис покинул общагу
        assertNull(gm.dorm().getEmployeeById(Long.MAX_VALUE));

        int a = output.indexOf("Награды\n");
        for(int i = a; i < a + 3; i++) {
            if (output.get(i).startsWith("Игрок")) {
                assertNotEquals(moneyBefore, gm.getMoney());
                break;
            }
        }
    }
}
