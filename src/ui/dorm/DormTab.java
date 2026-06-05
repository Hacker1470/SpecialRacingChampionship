package ui.dorm;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;

import java.util.List;

public class DormTab extends Tab {

    public DormTab(GameSession gm) {
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
        gm.io().printlnString("========== ОБЩАГА ========");

        if (gm.dorm().getQuantityOfEmployees() > 0) {
            printEmployeesCatalog();
        } else {
            printEmptyDorm();
        }

        gm.io().printlnString("[0] Вернуться в меню");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printEmployeesCatalog() {
        gm.io().printlnString("В общежитии находятся следующие люди");

        gm.io().printlnString(gm.dorm().generateStringCatalog());

        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Подробнее о работнике N");
    }

    private void printEmptyDorm() {
        gm.io().printlnString("В общежитии никого нет");
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

        List<Long> keys = gm.dorm().getKeysAscending();

        if (index <= keys.size() && index >= 1) {
            return new DormEmployeeInfoTab(gm, gm.dorm().getEmployeeById(keys.get(index - 1)));
        } else {
            return null;
        }
    }
}
