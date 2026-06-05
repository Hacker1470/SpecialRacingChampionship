package tests.market;

import data.catalogs.CatalogOfParts;
import data.partslists.EnginesList;
import data.parts.Part;
import game.GameSession;
import iosystem.AutomaticIO;
import iosystem.IOControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.TabsHandler;
import ui.market.MarketPartInfoTab;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartBuyingTest {
    GameSession gm;
    AutomaticIO io;

    @BeforeEach
    public void init(){
        io = new AutomaticIO(new ArrayList<>(List.of("1","1")));
        gm = new GameSession(new IOControl(io, io));
        gm.addMoney(300);
        gm.changeRep(30);
        CatalogOfParts.init();
    }

    @DisplayName("Проверка покупки детали на примере двигателя")
    @Test
    public void buyEngine(){
        int sizeBefore = gm.warehouse().getAllParts().size();
        int moneyBefore = gm.getMoney();
        TabsHandler.scheduling(new MarketPartInfoTab(gm, EnginesList.lada.getCopy(Long.MIN_VALUE)), 2);
        assertEquals(sizeBefore + 1,gm.warehouse().getAllParts().size());
        assertEquals(moneyBefore - 20, gm.getMoney());
        Part boughtPart = gm.warehouse().getAllParts().getLast();
        assertEquals("engi_2_0", boughtPart.getArticle());
        assertEquals("Двигатель ZovAuto 1", boughtPart.getName() + " " + boughtPart.getPostfix());
    }
}
