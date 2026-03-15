package ui.employment;

import data.catalogs.CatalogOfEmployees;
import game.GameSession;
import ui.service.ConsoleControl;
import ui.base.MenuTab;
import data.crew.Employee;
import data.crew.JobType;

import java.util.HashMap;

public class EmployeesListTab extends MenuTab {

    HashMap<Integer, Employee> availableEmployeesByRep;
    JobType type;

    public EmployeesListTab(GameSession gm, JobType type) {
        super(gm);
        this.type = type;
    }

    @Override
    public MenuTab show() {
        availableEmployeesByRep = CatalogOfEmployees.getAvailableByReputation(type, gm.getRep());
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

        ConsoleControl.printlnString(type.getEmployGroupTitle());

        for(int i = 1; i <= availableEmployeesByRep.size(); i++){
            ConsoleControl.printlnString("[" + i + "] " + availableEmployeesByRep.get(i).getName());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("[0] Вернуться на биржу");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private MenuTab menuHandler(){
        String request;
        MenuTab response = null;


        while (response == null){
            request = ConsoleControl.getString();

            if(request.equals("0")){
                response = new EmployTab(gm);
                break;
            }

            if(availableEmployeesByRep.containsKey(Integer.parseInt(request))){
                response = new EmployeeInfoTab(gm, availableEmployeesByRep.get(Integer.parseInt(request)));
            }

            //если пользователь ввёл неправильное значение
            printMenuWithWarn("Меню не имеет пункта: " + request);
        }

        return response;
    }
}
