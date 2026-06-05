package tests.assembly;

import data.catalogs.CatalogOfEmployees;
import data.catalogs.CatalogOfParts;
import data.crew.Engineer;
import data.employeeslists.EngineerList;
import data.parts.*;
import data.parts.enums.PartType;
import data.partslists.ChassisList;
import data.partslists.EnginesList;
import data.partslists.TransmissionList;
import data.partslists.WheelsList;
import data.special.AssemblingHelpers;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoodAssemblyTests {
    GameSession gm;
    AutomaticIO io;
    RacecarSample rs;

    @BeforeEach
    public void init(){
        io = new AutomaticIO(new ArrayList<>(List.of("+","0")));
        gm = new GameSession(new IOControl(io, io));
        CatalogOfParts.init();
        CatalogOfEmployees.init();

        gm.addMoney(50);

        rs = new RacecarSample();

        rs.setEngineer((Engineer) EngineerList.maslyonok.getCopy(Long.MIN_VALUE));
        rs.setEngine((Engine) EnginesList.pedal.getCopy(Long.MIN_VALUE));
        rs.setChassis((Chassis) ChassisList.woodenBox.getCopy(Long.MIN_VALUE));
        rs.setTransmission((Transmission) TransmissionList.chain.getCopy(Long.MIN_VALUE));
        rs.setWheels((Wheels) WheelsList.bicycleWheels.getCopy(Long.MIN_VALUE));
    }

    @DisplayName("Проверка на добавление в гараж")
    @Test
    public void checkCarInGarageTest(){
        int carsInGarageBefore = gm.garage().getCarsNumber();
        TabsHandler.scheduling(new CarAssemblyTab(gm, rs), 2);

        assertEquals(carsInGarageBefore + 1,gm.garage().getCarsNumber());
        ArrayList<Part> parts1 = rs.getNotNullParts();
        long idInGarage = Collections.max(gm.garage().getKeys());
        ArrayList<Part> parts2 = gm.garage().getCarById(idInGarage).getNotNullParts();
        for(int i = 0; i < Math.min(parts1.size(), parts2.size()); i++){
            assertEquals(parts1.get(i).getArticle(), parts2.get(i).getArticle());
            assertEquals(parts1.get(i).getConnectionReliability(), parts2.get(i).getConnectionReliability());
        }
    }


    @DisplayName("Проверка на оплату труда инженера")
    @Test
    public void payingForAssemblingTest(){
        int moneyBefore = gm.getMoney();
        int engineerSalary = rs.getEngineer().getSalary(1);
        TabsHandler.scheduling(new CarAssemblyTab(gm, rs), 2);
        assertEquals(moneyBefore - engineerSalary, gm.getMoney());
    }

    @DisplayName("Проверка на прибавку опыта у инженера")
    @Test
    public void engineerExperienceTest(){

        int experienceBefore = rs.getEngineer().getExperience();
        TabsHandler.scheduling(new CarAssemblyTab(gm, rs), 2);
        int experienceAfter = rs.getEngineer().getExperience();
        assertEquals(experienceBefore + 1, experienceAfter);
    }

    @DisplayName("Проверка влияния опыта на качество сборки")
    @Test
    public void engineerPerksTest(){
        rs.getEngineer().setExperience(0);
        AssemblingHelpers.addEngineerPerks(rs);
        int reliabilityBefore = rs.getEngine().getConnectionReliability();


        rs.getEngineer().setExperience(10);
        AssemblingHelpers.addEngineerPerks(rs);
        int reliabilityAfter = rs.getEngine().getConnectionReliability();

        assertTrue(reliabilityBefore < reliabilityAfter);
    }
}
