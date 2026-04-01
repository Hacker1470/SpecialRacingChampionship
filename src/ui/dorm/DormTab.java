package ui.dorm;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.handling.ConsoleControl;
import ui.warehouse.WarehousePartInfoTab;

import java.util.List;

public class DormTab extends Tab{

    public DormTab(GameSession gm) {
        super(gm);
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
        ConsoleControl.printlnString("========== ОБЩАГА ========");

        if(gm.dorm().getQuantityOfEmployees() > 0){
            printEmployeesCatalog();
        }
        else{
            printEmptyDorm();
        }

        ConsoleControl.printlnString("[0] Вернуться в меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printEmployeesCatalog(){
        ConsoleControl.printlnString("В общежитии находятся следующие люди");

        ConsoleControl.printlnString(gm.dorm().generateStringCatalog());

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Подробнее о работнике N");
    }

    private void printEmptyDorm(){
        ConsoleControl.printlnString("В общежитии никого нет");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
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
            return new MainTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        List<Long> keys = gm.dorm().getKeysAscending();

        if(index <= keys.size() && index >= 1) {
            return new DormEmployeeInfoTab(gm, gm.dorm().getEmployeeById(keys.get(index - 1)));
        }
        else {
            return null;
        }
    }
}
