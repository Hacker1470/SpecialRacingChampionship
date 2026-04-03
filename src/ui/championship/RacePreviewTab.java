package ui.championship;

import data.crew.JobType;
import data.race.map.RaceTrack;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.employment.EmployeesByJobTab;
import ui.handling.ConsoleControl;

import java.util.Collection;

public class RacePreviewTab extends Tab{

    RaceTrack track;
    public RacePreviewTab(GameSession gm, RaceTrack raceTrack) {
        super(gm);
        track = raceTrack;
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
        ConsoleControl.printlnString("====== ПОДГОТОВКА К ВЫЕЗДУ ======");
        ConsoleControl.printlnString("Выбранная карта: " + track.getName());
        ConsoleControl.printlnString("Карта трассы:");
        for (int i = 0; i < track.getNumberOfTerrains(); i++){
            ConsoleControl.printlnString(track.getTerrainByNumber(i).getCharacteristics());
        }
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Продолжить");
        ConsoleControl.printlnString("[0] Назад");
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
            case "1" -> new EmployeesByJobTab(gm, JobType.PILOT);
            case "0" -> new ChampionshipTab((gm));
            default -> null;
        };
    }
}
