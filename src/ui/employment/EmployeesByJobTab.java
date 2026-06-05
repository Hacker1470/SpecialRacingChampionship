package ui.employment;

import data.catalogs.CatalogOfEmployees;
import data.crew.Pilot;
import data.special.RandomGenerator;
import game.GameModeNorris;
import game.GameSession;
import ui.base.Tab;
import data.crew.Employee;
import data.crew.JobType;

import java.util.ArrayList;

public class EmployeesByJobTab extends Tab {

    ArrayList<Employee> availableEmployeesByRep;
    JobType type;

    public EmployeesByJobTab(GameSession gm, JobType type) {
        super(gm);
        this.type = type;

        if (gm.getGameModeNorris() == GameModeNorris.NORMAL
                && type == JobType.PILOT
                && RandomGenerator.getInteger(1, 100) > 30) {
            gm.setGameMode(GameModeNorris.CHUCK_NORRIS_APPEARANCE);
        }
    }

    @Override
    public Tab show() {
        availableEmployeesByRep = CatalogOfEmployees.getAvailableByReputation(type, gm.getRep());
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(type.getEmployGroupTitle());

        if (type == JobType.PILOT && gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_APPEARANCE) {
            gm.io().printlnString("[1] Chuck Norris");
            for (int i = 2; i <= availableEmployeesByRep.size() + 1; i++) {
                gm.io().printlnString("[" + i + "] " + availableEmployeesByRep.get(i - 2).getName());
            }
        } else {
            for (int i = 1; i <= availableEmployeesByRep.size(); i++) {
                gm.io().printlnString("[" + i + "] " + availableEmployeesByRep.get(i - 1).getName());
            }
        }


        gm.io().printlnString("");
        gm.io().printlnString("[0] Вернуться на биржу");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            if (type == JobType.PILOT && gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_APPEARANCE) {
                response = norrisSelectResponse(request);
            } else {
                response = selectResponse(request);
            }
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab norrisSelectResponse(String req) {
        if (req.equals("0")) {
            return new EmployTab(gm);
        }
        if (req.equals("1")) {
            return new EmployeeInfoTab(gm, new Pilot(
                    Long.MAX_VALUE, "chuck_norris", "Chuck Norris", 1,
                    0, 0, 10, 90, 1)
            );
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (index <= availableEmployeesByRep.size() + 1 && index >= 2) {
            return new EmployeeInfoTab(gm, availableEmployeesByRep.get(index - 2));
        } else {
            return null;
        }
    }

    private Tab selectResponse(String req) {
        if (req.equals("0")) {
            return new EmployTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (index <= availableEmployeesByRep.size() && index >= 1) {
            return new EmployeeInfoTab(gm, availableEmployeesByRep.get(index - 1));
        } else {
            return null;
        }
    }
}
