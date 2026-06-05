package ui.warehouse;

import data.parts.Part;
import game.GameSession;
import ui.base.Tab;

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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(chosenPart.getType().getMarketInfoTitle());

        gm.io().printlnString(chosenPart.getWarehouseCharacteristics());

        gm.io().printlnString("=============================================");
        gm.io().printlnString("[0] Вернуться к списку");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            if (request.equals("0")) {
                response = new WarehouseTab(gm);
            } else {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
