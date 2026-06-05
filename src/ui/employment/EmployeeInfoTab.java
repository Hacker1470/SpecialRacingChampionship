package ui.employment;

import game.GameModeNorris;
import game.GameSession;
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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(chosenEmployee.getType().getEmployInfoTitle());

        gm.io().printlnString(chosenEmployee.getEmploymentCharacteristics());

        gm.io().printlnString("=============================================");
        gm.io().printlnString("[1] Купить (Баланс: " + gm.getMoney() + " грошей)");
        gm.io().printlnString("[0] Вернуться к списку");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            switch (request) {
                case "0":
                    response = new EmployeesByJobTab(gm, chosenEmployee.getType());
                    break;
                case "1":
                    if (hireEmployee()) {
                        if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_APPEARANCE && chosenEmployee.getId() == Long.MAX_VALUE) {
                            gm.setGameMode(GameModeNorris.CHUCK_NORRIS_ACTIVE);
                            response = new EmployeeNamingTab(gm, gm.dorm().getEmployeeById(Long.MAX_VALUE));
                        } else {
                            response = new EmployeeNamingTab(gm, gm.dorm().getEmployeeById(idCounter - 1));
                        }
                    } else {
                        outputWithWarn("Недостаточно средств");
                    }
                    break;
                default:
                    outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private boolean hireEmployee() {
        if (gm.takeMoney(chosenEmployee.getHiringCost())) {
            if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_APPEARANCE && chosenEmployee.getId() == Long.MAX_VALUE) {
                gm.dorm().put(chosenEmployee.getCopy(Long.MAX_VALUE));
            } else {
                gm.dorm().put(chosenEmployee.getCopy(idCounter++));
            }
            return true;
        } else {
            return false;
        }
    }
}
