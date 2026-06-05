package ui.hospital;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;

public class HospitalTab extends Tab {
    public HospitalTab(GameSession gm) {
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
        gm.io().printlnString("========== БОЛЬНИЧКА ========");

        gm.io().printlnString(gm.hospital().generateString());

        gm.io().printlnString("=============================================");
        gm.io().printlnString("[0] Вернуться в меню");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
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
        } else {
            return null;
        }
    }
}
