package ui.dorm;

import data.crew.Employee;
import game.GameSession;
import ui.base.Tab;

public class DormEmployeeInfoTab extends Tab {

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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(chosenEmp.getType().getEmployInfoTitle());

        gm.io().printlnString(chosenEmp.getDormCharacteristics());

        gm.io().printlnString("=============================================");
        gm.io().printlnString("[0] Вернуться к списку");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            if (request.equals("0")) {
                response = new DormTab(gm);
            } else {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }
}
