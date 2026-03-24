package ui.employment;

import data.catalogs.CatalogOfEmployees;
import game.GameSession;
import ui.handling.ConsoleControl;
import ui.base.Tab;
import data.crew.Employee;
import data.crew.JobType;

import java.util.ArrayList;

public class EmployeesByJobTab extends Tab {

    ArrayList<Employee> availableEmployeesByRep;
    JobType type;

    public EmployeesByJobTab(GameSession gm, JobType type) {
        super(gm);
        this.type = type;
    }

    @Override
    public Tab show() {
        availableEmployeesByRep = CatalogOfEmployees.getAvailableByReputation(type, gm.getRep());
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString(type.getEmployGroupTitle());

        for(int i = 1; i <= availableEmployeesByRep.size(); i++){
            ConsoleControl.printlnString("[" + i + "] " + availableEmployeesByRep.get(i - 1).getName());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("[0] Вернуться на биржу");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            response = selectResponse(request);
            if (response == null){
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        if(req.equals("0")){
            return new EmployTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(index <= availableEmployeesByRep.size() && index >= 1) {
            return new EmployeeInfoTab(gm, availableEmployeesByRep.get(index - 1));
        }
        else {
            return null;
        }
    }
}
