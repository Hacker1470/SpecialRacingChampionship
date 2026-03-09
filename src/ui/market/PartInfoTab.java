package ui.market;

import game.GameSession;
import ui.service.ConsoleControl;
import ui.base.MenuTab;
import data.vehicle.Part;

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
        ConsoleControl.printlnString("[1] Купить (Баланс: " + gm.getMoney() + " грошей)");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            switch (request){
                case "0":
                    response = new PartsListTab(gm, chosenPart.getType());
                    break;
                case "1":
                    if(buyPart()){
                        printMenuWithWarn("Деталь " + chosenPart.getName() + " куплена");
                    }
                    else {
                        printMenuWithWarn("Недостаточно средств");
                    }
                    break;
                default:
                    printMenuWithWarn("Меню не имеет пункта: " + request);
            }
        }



        return response;
    }

    private boolean buyPart(){
        if(gm.getMoney() - chosenPart.getRealPrice() >= 0) {
            gm.takeMoney(chosenPart.getRealPrice());
            gm.warehouse().put(chosenPart.getCopy());
            return true;
        }
        else {
            return false;
        }
    }
}
