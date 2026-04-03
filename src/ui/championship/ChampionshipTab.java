package ui.championship;

import data.catalogs.CatalogOfTracks;
import data.crew.JobType;
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
        ConsoleControl.printlnString("[1] Тренировочная гонка");
        ConsoleControl.printlnString("[2] Случайная гонка");
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
        return switch (req) {
            case "1" -> new RacePreviewTab(gm, CatalogOfTracks.allCatalog.getFirst());
            case "2" -> new EmployeesByJobTab(gm, JobType.ENGINEER);
            case "0" -> new MainTab((gm));
            default -> null;
        };
    }
}
