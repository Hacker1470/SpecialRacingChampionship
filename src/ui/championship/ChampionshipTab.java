package ui.championship;

import data.catalogs.CatalogOfRaces;
import data.crew.JobType;
import data.special.RandomGenerator;
import game.GameMode;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.employment.EmployeesByJobTab;
import ui.handling.ConsoleControl;

public class ChampionshipTab extends Tab{
    public ChampionshipTab(GameSession gm) {
        super(gm);
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
        ConsoleControl.printlnString("====== ГОНОЧКИ ======");
        ConsoleControl.printlnString("[1] Случайная гонка");
        if(gm.getGameMode() != GameMode.CHUCK_NORRIS_ACTIVE){
            ConsoleControl.printlnString("[2] Тренировочная гонка");
        }
        ConsoleControl.printlnString("[0] Вернуться в главное меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            response = selectResponse(request);
            if (response == null){
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        if(gm.getGameMode() == GameMode.CHUCK_NORRIS_ACTIVE && req.equals("2")){
            return null;
        }
        return switch (req) {
            case "1" -> new RacePreviewTab(gm, CatalogOfRaces.allCatalog
                    .get(RandomGenerator.getInteger(1,CatalogOfRaces.allCatalog.size() - 1)));
            case "2" -> new RacePreviewTab(gm, CatalogOfRaces.allCatalog.getFirst());
            case "0" -> new MainTab((gm));
            default -> null;
        };
    }
}
