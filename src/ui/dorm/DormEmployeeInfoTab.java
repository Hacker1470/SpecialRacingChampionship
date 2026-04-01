package ui.dorm;

import data.crew.Employee;
import data.vehicle.Part;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;
import ui.warehouse.WarehouseTab;

public class DormEmployeeInfoTab extends Tab{

    private final Employee chosenEmp;

    public DormEmployeeInfoTab(GameSession gm, Employee emp) {
        super(gm);
        chosenEmp = emp;
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
        ConsoleControl.printlnString(chosenEmp.getType().getEmployInfoTitle());

        ConsoleControl.printlnString(chosenEmp.getStringOfCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            if (request.equals("0")) {
                response = new DormTab(gm);
            }
            else {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
