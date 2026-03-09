package ui.employment;

import game.GameSession;
import ui.service.ConsoleControl;
import ui.base.MenuTab;
import data.crew.JobType;

public class EmployTab extends MenuTab {

    public EmployTab(GameSession gm) {
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
        ConsoleControl.printlnString("====== БИРЖА ТРУДА ======");
        ConsoleControl.printlnString("[1] Пилоты");
        ConsoleControl.printlnString("[2] Инженеры");
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
                    response = new EmployeesListTab(gm, JobType.PILOT);
                    break;
                case "2":
                    response = new EmployeesListTab(gm, JobType.ENGINEER);
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
