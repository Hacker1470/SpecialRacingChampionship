package tests.employment;

import data.catalogs.CatalogOfEmployees;
import data.crew.Employee;
import data.employeeslists.PilotList;
import game.GameSession;
import iosystem.AutomaticIO;
import iosystem.IOControl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TabsHandler;
import ui.employment.EmployeeInfoTab;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeEmploymentTest {
    static GameSession gm;
    static AutomaticIO io;

    @BeforeAll
    public static void init() {
        io = new AutomaticIO(new ArrayList<>(List.of("1", "1")));
        gm = new GameSession(new IOControl(io, io));
        CatalogOfEmployees.init();
    }

    @BeforeEach
    public void giveMoneyAndRep() {
        gm.addMoney(300);
        gm.changeRep(30);
    }

    @DisplayName("Проверка покупки детали на примере пилота")
    @Test
    public void buyEngine() {
        int sizeBefore = gm.dorm().getAllEmployees().size();

        int moneyBefore = gm.getMoney();
        TabsHandler.scheduling(new EmployeeInfoTab(gm, PilotList.schumacher), 2);
        assertEquals(sizeBefore + 1, gm.dorm().getAllEmployees().size());
        assertEquals(moneyBefore - 208, gm.getMoney());
        Employee boughtEmp = gm.dorm().getAllEmployees().getLast();
        assertEquals("pilot_2_0", boughtEmp.getArticle());
        assertEquals("Шумахер в лучшие годы 1", boughtEmp.getName() + " " + boughtEmp.getPostfix());
    }
}
