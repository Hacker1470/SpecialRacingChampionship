package ui.championship;

import data.catalogs.CatalogOfRaces;
import data.special.RandomGenerator;
import game.GameModeNorris;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;

public class ChampionshipTab extends Tab {
    public ChampionshipTab(GameSession gm) {
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
        gm.io().printlnString("====== ГОНОЧКИ ======");
        gm.io().printlnString("[1] Случайная гонка");
        if (gm.getGameModeNorris() != GameModeNorris.CHUCK_NORRIS_ACTIVE) {
            gm.io().printlnString("[2] Тренировочная гонка");
        }
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
        if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_ACTIVE && req.equals("2")) {
            return null;
        }
        return switch (req) {
            case "1" -> new RacePreviewTab(gm, CatalogOfRaces.allCatalog
                    .get(RandomGenerator.getInteger(1, CatalogOfRaces.allCatalog.size() - 1)));
            case "2" -> new RacePreviewTab(gm, CatalogOfRaces.allCatalog.getFirst());
            case "0" -> new MainTab((gm));
            default -> null;
        };
    }
}
