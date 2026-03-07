package ui.market;

import data.GameSession;
import ui.ConsoleControl;
import ui.MenuTab;
import ui.market.MarketTab;
import vehicle.Part;

public class PartInfoTab extends MenuTab{

    private final Part chosenPart;

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
        ConsoleControl.printlnString(chosenPart.getType().getMarketInfoTitle());

        ConsoleControl.printlnString(chosenPart.getStringOfCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Купить");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            if(request.equals("0")){
                response = new PartsListTab(gm, chosenPart.getType());
                break;
            }


            switch (request){
                default:
                    printMenuWithWarn("Меню не имеет пункта: " + request);
            }
        }



        return response;
    }
}
