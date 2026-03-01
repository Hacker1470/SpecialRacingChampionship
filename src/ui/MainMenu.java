package ui;

import data.GameSession;
import ui.market.MarketTab;

public class MainMenu extends MenuTab{

    public MainMenu(GameSession gm){
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
        ConsoleControl.printlnString("[1] Чемпионаты");
        ConsoleControl.printlnString("[2] Статистика");
        ConsoleControl.printlnString("[3] Гараж");
        ConsoleControl.printlnString("[4] Мир запчастей");
        ConsoleControl.printlnString("[5] Биржа труда");
        ConsoleControl.printlnString("[6] Выход из игры");
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
                    gm.exit();
                    break;
                default:
                    printMenuWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
