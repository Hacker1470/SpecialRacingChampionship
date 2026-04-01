package ui.warehouse;

import data.vehicle.Part;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

public class WarehousePartInfoTab extends Tab {

    private final Part chosenPart;

    public WarehousePartInfoTab(GameSession gm, Part part) {
        super(gm);
        chosenPart = part;
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
        ConsoleControl.printlnString(chosenPart.getType().getMarketInfoTitle());

        ConsoleControl.printlnString(chosenPart.getStringOfCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            if (request.equals("0")) {
                response = new WarehouseTab(gm);
            }
            else {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
