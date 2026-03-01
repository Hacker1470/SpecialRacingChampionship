package ui.market.partslist;

import data.GameSession;
import data.partslists.EnginesList;
import ui.ConsoleControl;
import ui.MenuTab;
import ui.market.MarketTab;
import ui.market.partsinfo.EngineInfoTab;
import vehicle.Engine;

import java.util.HashMap;

public class EngineListTab extends MenuTab {

    HashMap<Integer, Engine> availableEnginesByRep;

    public EngineListTab(GameSession gm) {
        super(gm);
    }

    @Override
    public MenuTab show() {
        availableEnginesByRep = EnginesList.getAvailableEnginesByReputation(gm.getRep());
        printMenu();
        return menuHandler();
    }

    private void printMenu(){
        ConsoleControl.clear();
        printListOfMenus();
    }

    private void printMenuWithWarn(String warn){
        ConsoleControl.clear();
        ConsoleControl.printlnString("СООБЩЕНИЕ:");
        ConsoleControl.printlnString(warn);
        ConsoleControl.printlnString("=============================================");
        printListOfMenus();
    }

    private void printListOfMenus(){
        ConsoleControl.printlnString("ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА");
        ConsoleControl.printlnString("");

        ConsoleControl.printlnString("====== ДВИГАТЕЛИ ======");
        ConsoleControl.printlnString("Вам доступны следующие двигатели:");

        for(int i = 1; i <= availableEnginesByRep.size(); i++){
            ConsoleControl.printlnString("[" + i + "] " + availableEnginesByRep.get(i).getName());
        }

        ConsoleControl.printlnString("[0] Вернуться на рынок");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;


        while (response == null){
            request = ConsoleControl.getString();

            if(request.equals("0")){
                response = new MarketTab(gm);
                break;
            }

            for (int i = 1; i <= availableEnginesByRep.size(); i++){
                if(request.equals(String.valueOf(i))){
                    response = new EngineInfoTab(gm, availableEnginesByRep.get(i));
                    break;
                }
            }

            //если пользователь ввёл неправильное значение
            printMenuWithWarn("Меню не имеет пункта: " + request);
        }

        return response;
    }
}
