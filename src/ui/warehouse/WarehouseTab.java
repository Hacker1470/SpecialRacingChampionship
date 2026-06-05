package ui.warehouse;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("========== СКЛАДБИЩЕ ========");

        if (gm.warehouse().getQuantityOfParts() > 0) {
            printPartsCatalog();
        } else {
            printEmptyWarehouse();
        }

        gm.io().printlnString("[0] Вернуться в меню");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printPartsCatalog() {
        gm.io().printlnString("На складе лежат следующие детали");

        gm.io().printlnString(gm.warehouse().generateStringCatalog());

        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Подробнее о детали N");
    }

    private void printEmptyWarehouse() {
        gm.io().printlnString("Склад пустует");
        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
        if (req.equals("0")) {
            return new MainTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        List<Long> keys = gm.warehouse().getKeysAscending();

        if (index <= keys.size() && index >= 1) {
            return new WarehousePartInfoTab(gm, gm.warehouse().getPartById(keys.get(index - 1)));
        } else {
            return null;
        }
    }
}
