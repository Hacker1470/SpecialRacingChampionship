package ui.championship.initializing;

import data.race.Race;
import data.race.teams.TeamSample;
import game.GameSession;
import ui.base.Tab;

public class RacecarSelectTab extends Tab {
    private TeamSample team;
    private Race race;

    public RacecarSelectTab(GameSession gm, TeamSample team, Race race) {
        super(gm);
        this.team = team;
        this.race = race;
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
        gm.io().printlnString("ВАШ ГАРАЖ");

        if (gm.garage().getCarsNumber() > 0) {
            printCarsCatalog();
        } else {
            printEmptyGarage();
        }

        gm.io().printlnString("[0] Назад");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printCarsCatalog() {
        gm.io().printlnString("В гараже стоят следующие авто");

        gm.io().printlnString(gm.garage().generateStringCatalog());
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Выбрать болид N");
    }

    private void printEmptyGarage() {
        gm.io().printlnString("Гараж пустует");
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
            return new UserTeamCreationTab(gm, team, race);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (gm.garage().getCarsNumber() > 0 && index >= 1 && index <= gm.garage().getCarsNumber()) {
            team.setCar(gm.garage().getCarById(gm.garage().getKeys().get(index - 1)));
            return new UserTeamCreationTab(gm, team, race);
        } else {
            return null;
        }
    }
}
