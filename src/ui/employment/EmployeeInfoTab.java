package ui.employment;

import game.GameMode;
import game.GameSession;
import ui.handling.ConsoleControl;
import ui.base.Tab;
import data.crew.Employee;

public class EmployeeInfoTab extends Tab {

    private static long idCounter = Long.MIN_VALUE + 1;
    private final Employee chosenEmployee;

    public EmployeeInfoTab(GameSession gm, Employee employee) {
        super(gm);
        chosenEmployee = employee;
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
        ConsoleControl.printlnString(chosenEmployee.getType().getEmployInfoTitle());

        ConsoleControl.printlnString(chosenEmployee.getEmploymentCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Купить (Баланс: " + gm.getMoney() + " грошей)");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            switch (request){
                case "0":
                    response = new EmployeesByJobTab(gm, chosenEmployee.getType());
                    break;
                case "1":
                    if(hireEmployee()){
                        if(gm.getGameMode() == GameMode.CHUCK_NORRIS_APPEARANCE  && chosenEmployee.getId() == Long.MAX_VALUE){
                            gm.setGameMode(GameMode.CHUCK_NORRIS_ACTIVE);
                            response = new EmployeeNamingTab(gm, gm.dorm().getEmployeeById(Long.MAX_VALUE));
                        }
                        else{
                            response = new EmployeeNamingTab(gm, gm.dorm().getEmployeeById(idCounter - 1));
                        }
                    }
                    else {
                        outputWithWarn("Недостаточно средств");
                    }
                    break;
                default:
                    outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private boolean hireEmployee(){
        if(gm.takeMoney(chosenEmployee.getHiringCost())) {
            if(gm.getGameMode() == GameMode.CHUCK_NORRIS_APPEARANCE && chosenEmployee.getId() == Long.MAX_VALUE){
                gm.dorm().put(chosenEmployee.getCopy(Long.MAX_VALUE));
            }
            else {
                gm.dorm().put(chosenEmployee.getCopy(idCounter++));
            }
            return true;
        }
        else {
            return false;
        }
    }
}
