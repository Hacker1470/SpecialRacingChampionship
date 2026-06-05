package ui.stats;

import data.special.ArchiveRecord;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;

public class StatisticsTab extends Tab {
    public StatisticsTab(GameSession gm) {
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
        gm.io().printlnString("========== Статистика ========");

        int i = 1;
        for (ArchiveRecord ar : gm.archive()) {
            gm.io().printlnString("/\\/\\/\\ ГОНКА " + i++);
            gm.io().printlnString("Карта:\t\t\t" + ar.getMapName());
            gm.io().printlnString("Авто:\t\t\t" + ar.getCarName());
            gm.io().printlnString("Пилот:\t\t\t" + ar.getPilotName());
            gm.io().printlnString("Занятое место:\t"
                    + ((ar.getPlace().startsWith("0/")) ? "Сход с дистанции" : ar.getPlace()));
            gm.io().printlnString("Прибыль:\t\t" + ar.getProfit());
            gm.io().printlnString("=============================================");
        }

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
        }
        return null;
    }
}
