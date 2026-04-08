package ui.hospital;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.dorm.DormEmployeeInfoTab;
import ui.handling.ConsoleControl;

import java.util.List;

public class HospitalTab extends Tab{
    public HospitalTab(GameSession gm) {
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
        ConsoleControl.printlnString("========== БОЛЬНИЧКА ========");

        ConsoleControl.printlnString(gm.getHospital().generateString());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[0] Вернуться в меню");
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
            return new MainTab(gm);
        }
        else {
            return null;
        }
    }
}
