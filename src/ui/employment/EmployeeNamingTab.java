package ui.employment;

import data.crew.Employee;
import game.GameSession;
import ui.base.Tab;

public class EmployeeNamingTab extends Tab {

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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("Работник нанят и перемещён в общежитие.");
        gm.io().printlnString("Сейчас он имеет название \""
                + chosenEmp.getName() + chosenEmp.getPostfix() + "\"");
        gm.io().printlnString("Вы можете добавить приписку справа");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Нажмите Enter, чтобы пропустить этот пункт");
        gm.io().printlnString("или введите строку, для добавления приписки");
    }

    private Tab menuHandler() {
        String request;
        request = gm.io().getString();

        if (!request.isEmpty()) {
            chosenEmp.setPostfix(request);
        }
        return new EmployTab(gm);
    }
}
