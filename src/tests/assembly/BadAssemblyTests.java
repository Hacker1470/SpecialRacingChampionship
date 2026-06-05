package tests.assembly;

import data.catalogs.CatalogOfEmployees;
import data.catalogs.CatalogOfParts;
import data.crew.Engineer;
import data.employeeslists.EngineerList;
import data.parts.Chassis;
import data.parts.Engine;
import data.parts.Transmission;
import data.parts.Wheels;
import data.partslists.ChassisList;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import data.partslists.WheelsList;
import data.racecar.*;
import game.GameSession;
import iosystem.AutomaticIO;
import iosystem.IOControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TabsHandler;
import ui.garage.assembly.CarAssemblyTab;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadAssemblyTests {
    GameSession gm;
    AutomaticIO io;

    @BeforeEach
    public void init(){
        io = new AutomaticIO(new ArrayList<>(List.of("+")));
        gm = new GameSession(new IOControl(io, io));
        CatalogOfParts.init();
        CatalogOfEmployees.init();
    }

    @DisplayName("Проверка на отсутствие инженера")
    @Test
    public void noEngineerTest(){
        RacecarSample badRacecar = new RacecarSample();
        gm.addMoney(50);

        badRacecar.setEngine((Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE));
        badRacecar.setChassis((Chassis) ChassisList.woodenBox.getCopy(Long.MIN_VALUE));
        badRacecar.setTransmission((Transmission) TransmissionList.chain.getCopy(Long.MIN_VALUE));
        badRacecar.setWheels((Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE));

        TabsHandler.scheduling(new CarAssemblyTab(gm, badRacecar), 2);
        assertEquals("Авто не может быть собрано.\nНе назначен инженер\n",io.getOutput().get(27));
    }

    @DisplayName("Проверка на отсутствие обязательной детали")
    @Test
    public void noRequiredPartTest(){
        RacecarSample badRacecar = new RacecarSample();
        gm.addMoney(50);

        badRacecar.setEngineer((Engineer) EngineerList.maslyonok.getCopy(Long.MIN_VALUE));
        badRacecar.setEngine((Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE));
        badRacecar.setTransmission((Transmission) TransmissionList.chain.getCopy(Long.MIN_VALUE));
        badRacecar.setWheels((Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE));

        TabsHandler.scheduling(new CarAssemblyTab(gm, badRacecar), 2);
        assertEquals("Авто не может быть собрано.\n" +
                "Не назначена обязательная деталь - Шасси\n",
                io.getOutput().get(27));
    }

    @DisplayName("Проверка на ошибку сборки, если выбрана несовместимая деталь")
    @Test
    public void badPartsTest(){
        RacecarSample badRacecar = new RacecarSample();
        gm.addMoney(50);

        badRacecar.setEngineer((Engineer) EngineerList.maslyonok.getCopy(Long.MIN_VALUE));
        badRacecar.setEngine((Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE));
        badRacecar.setChassis((Chassis) ChassisList.woodenBox.getCopy(Long.MIN_VALUE));
        badRacecar.setTransmission((Transmission) TransmissionList.ladabox.getCopy(Long.MIN_VALUE));
        badRacecar.setWheels((Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE));

        TabsHandler.scheduling(new CarAssemblyTab(gm, badRacecar), 2);
        assertEquals("Авто не может быть собрано.\n" +
                "Авто не содержит деталей, которые требует Деревянный ящик \n",
                io.getOutput().get(27));
    }

    @DisplayName("Проверка на отсутствие денег на сборку")
    @Test
    public void notEnoughMoney(){
        RacecarSample niceRacecar = new RacecarSample();

        niceRacecar.setEngineer((Engineer) EngineerList.maslyonok.getCopy(Long.MIN_VALUE));
        niceRacecar.setEngine((Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE));
        niceRacecar.setChassis((Chassis) ChassisList.woodenBox.getCopy(Long.MIN_VALUE));
        niceRacecar.setTransmission((Transmission) TransmissionList.chain.getCopy(Long.MIN_VALUE));
        niceRacecar.setWheels((Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE));

        TabsHandler.scheduling(new CarAssemblyTab(gm, niceRacecar), 2);
        assertEquals("Авто не может быть собрано." +
                "\nНе хватает денег на оплату труда инженера.\n" +
                "Он хочет 10 грошей\n",io.getOutput().get(27));
    }
}
