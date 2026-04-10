package tests.market;

import data.catalogs.CatalogOfParts;
import data.partslists.EnginesList;
import game.GameSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.base.Tab;
import ui.market.MarketPartInfoTab;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartBuyingTest {
    static GameSession gm;

    @BeforeAll
    public static void init(){
        gm = new GameSession();
        CatalogOfParts.catalogInit();
    }

    @BeforeEach
    public void giveMoneyAndRep(){
        gm.addMoney(300);
        gm.changeRep(30);
    }

    @Test
    public void buyEngine(){
        MarketPartInfoTab tab = new MarketPartInfoTab(gm, EnginesList.pedal);
        tab.buyPart();
        assertEquals();
    }


}
