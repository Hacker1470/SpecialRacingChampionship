package ui.market;

import data.GameSession;
import ui.ConsoleControl;
import ui.market.partslist.EngineListTab;
import ui.MenuTab;

public class MarketTab extends MenuTab {

    public MarketTab(GameSession gm) {
        super(gm);
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
        ConsoleControl.printlnString("====== РЫНОК ======");
        ConsoleControl.printlnString("[1] Двигатели");
        ConsoleControl.printlnString("[2] Коробки");
        ConsoleControl.printlnString("[3] Шасси");
        ConsoleControl.printlnString("[4] Колёса");
        ConsoleControl.printlnString("[5] Подвеска");
        ConsoleControl.printlnString("[6] Обвесы для прижимной силы");
        ConsoleControl.printlnString("[0] Вернуться в главное меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;

        while (response == null){
            request = ConsoleControl.getString();

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
                    response = gm.getMainMenu();
                    break;
                default:
                    printMenuWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
