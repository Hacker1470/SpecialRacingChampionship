package ui.warehouse;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.handling.ConsoleControl;
import ui.market.MarketPartInfoTab;

import java.util.ArrayList;
import java.util.List;

public class WarehouseTab extends Tab {

    public WarehouseTab(GameSession gm) {
        super(gm);
    }

    @Override
    public Tab show() {
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("========== СКЛАДБИЩЕ ========");

        if(gm.warehouse().getQuantityOfParts() > 0){
            printPartsCatalog();
        }
        else{
            printEmptyWarehouse();
        }

        ConsoleControl.printlnString("[0] Вернуться в меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printPartsCatalog(){
        ConsoleControl.printlnString("На складе лежат следующие детали");

        ConsoleControl.printlnString(gm.warehouse().generateStringCatalog());

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Подробнее о детали N");
    }

    private void printEmptyWarehouse(){
        ConsoleControl.printlnString("Склад пустует");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            response = selectResponse(request);
            if (response == null){
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        if(req.equals("0")){
            return new MainTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        List<Long> keys = gm.warehouse().getKeysAscending();

        if(index <= keys.size() && index >= 1) {
            return new WarehousePartInfoTab(gm, gm.warehouse().getPartById(keys.get(index - 1)));
        }
        else {
            return null;
        }
    }
}
