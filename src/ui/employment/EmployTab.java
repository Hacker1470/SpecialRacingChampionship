package ui.employment;

import game.GameModeNorris;
import game.GameSession;
import ui.base.MainTab;
import ui.employment.modes.AngryNorrisTab;
import ui.base.Tab;
import data.crew.JobType;

public class EmployTab extends Tab {

    public EmployTab(GameSession gm) {
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
        gm.io().printlnString("====== БИРЖА ТРУДА ======");
        gm.io().printlnString("[1] Пилоты");
        gm.io().printlnString("[2] Инженеры");
        gm.io().printlnString("[0] Вернуться в главное меню");
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
        if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_APPEARANCE && req.equals("0")) {
            if (gm.dorm().getEmployeesByJob(JobType.PILOT).isEmpty()) {
                gm.setGameMode(GameModeNorris.NORMAL);
                return new MainTab(gm);
            } else {
                return new AngryNorrisTab(gm);
            }
        }
        return switch (req) {
            case "1" -> new EmployeesByJobTab(gm, JobType.PILOT);
            case "2" -> new EmployeesByJobTab(gm, JobType.ENGINEER);
            case "0" -> new MainTab((gm));
            default -> null;
        };
    }
}
