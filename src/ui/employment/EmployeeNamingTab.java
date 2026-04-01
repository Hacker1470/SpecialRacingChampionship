package ui.employment;

import data.crew.Employee;
import data.vehicle.Part;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;
import ui.market.MarketTab;

public class EmployeeNamingTab extends Tab{

    private final Employee chosenEmp;

    public EmployeeNamingTab(GameSession gm, Employee emp) {
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
        ConsoleControl.printlnString("Работник нанят и перемещён в общежитие.");
        ConsoleControl.printlnString("Сейчас он имеет название \""
                + chosenEmp.getName() + chosenEmp.getPostfix() + "\"");
        ConsoleControl.printlnString("Вы можете добавить приписку справа");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Нажмите Enter, чтобы пропустить этот пункт");
        ConsoleControl.printlnString("или введите строку, для добавления приписки");
    }

    private Tab menuHandler(){
        String request;
        request = ConsoleControl.getString();

        if(!request.isEmpty()){
            chosenEmp.setPostfix(request);
        }
        return new EmployTab(gm);
    }
}
