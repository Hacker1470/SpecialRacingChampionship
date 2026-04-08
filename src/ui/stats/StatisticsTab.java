package ui.stats;

import data.race.ArchiveRecord;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.dorm.DormEmployeeInfoTab;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.List;

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
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("========== Статистика ========");

        int i = 1;
        for(ArchiveRecord ar : gm.getArchive()){
            ConsoleControl.printlnString("/\\/\\/\\ ГОНКА " + i++);
            ConsoleControl.printlnString("Карта:\t\t\t" + ar.getMapName());
            ConsoleControl.printlnString("Авто:\t\t\t" + ar.getCarName());
            ConsoleControl.printlnString("Пилот:\t\t\t" + ar.getPilotName());
            ConsoleControl.printlnString("Занятое место:\t"
                    + ((ar.getPlace().startsWith("0/")) ? "Сход с дистанции" : ar.getPlace()));
            ConsoleControl.printlnString("Прибыль:\t\t" + ar.getProfit());
            ConsoleControl.printlnString("=============================================");
        }

        ConsoleControl.printlnString("[0] Вернуться в меню");
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
        if(req.equals("0")){
            return new MainTab(gm);
        }
        return null;
    }
}
