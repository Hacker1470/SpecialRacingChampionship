package ui.market.partsinfo;

import data.GameSession;
import data.partslists.ConnectivitySearcher;
import data.partslists.EnginesList;
import data.partslists.PartType;
import ui.ConsoleControl;
import ui.MenuTab;
import ui.market.MarketTab;
import ui.market.partslist.EngineListTab;
import vehicle.Engine;
import vehicle.Part;

import java.util.ArrayList;

public class PartInfoTab extends MenuTab{

    private Part chosenPart;

    public PartInfoTab(GameSession gm, Part part) {
        super(gm);
        chosenPart = part;
    }

    @Override
    public MenuTab show() {
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

        ConsoleControl.printlnString(chosenPart.getStringOfCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Купить");
        ConsoleControl.printlnString("[0] Вернуться в главное меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getTitle(){
        return switch (chosenPart.getType()){
            case ENGINE -> "====== ПОДРОБНЕЕ О ДВИГАТЕЛЕ ======\n";
            case TRANSMISSION -> "====== ПОДРОБНЕЕ О ТРАНСМИСИИ ======\n";
            case ANY -> "====== ПОДРОБНЕЕ ======\n";
        };
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            if(request.equals("0")){
                response = gm.getMainMenu();
                break;
            }


            switch (request){
                case "1":
                    response = new EngineListTab(gm);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    response = new MarketTab(gm);
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "0":

                    break;
                default:
                    printMenuWithWarn("Меню не имеет пункта: " + request);
            }
        }



        return response;
    }
}
