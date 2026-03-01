package ui.market;

import data.GameSession;
import data.partslists.CatalogOfParts;
import data.partslists.PartType;
import ui.ConsoleControl;
import ui.MenuTab;
import ui.market.partsinfo.EngineInfoTab;
import vehicle.Engine;
import vehicle.Part;

import java.util.HashMap;

public class PartsListTab extends MenuTab {

    HashMap<Integer, Part> availablePartsByRep;
    PartType type;

    public PartsListTab(GameSession gm, PartType type) {
        super(gm);
        this.type = type;
    }

    @Override
    public MenuTab show() {
        availablePartsByRep = CatalogOfParts.getAvailableByReputation(type, gm.getRep());
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

        ConsoleControl.printlnString(getTitle());

        for(int i = 1; i <= availablePartsByRep.size(); i++){
            ConsoleControl.printlnString("[" + i + "] " + availablePartsByRep.get(i).getName());
        }

        ConsoleControl.printlnString("[0] Вернуться на рынок");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getTitle(){
        return switch (type){
            case ENGINE -> "====== ДВИГАТЕЛИ ======\nВам доступны следующие двигатели:\n";
            case TRANSMISSION -> "====== ТРАНСМИССИЯ ======\nВам доступны следующие КПП:\n";
            case ANY -> "====== ??? ======\nВам доступны следующие детали:\n";
        };
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

            if(availablePartsByRep.containsKey(Integer.parseInt(request))){
                response = new EngineInfoTab(gm, (Engine) availablePartsByRep.get(Integer.parseInt(request)));
            }

            //если пользователь ввёл неправильное значение
            printMenuWithWarn("Меню не имеет пункта: " + request);
        }

        return response;
    }
}
