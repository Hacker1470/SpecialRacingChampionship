package ui.employment;

import game.GameMode;
import game.GameSession;
import ui.base.MainTab;
import ui.employment.modes.AngryNorrisTab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.base.Tab;
import data.crew.JobType;
import ui.market.MarketTab;

public class EmployTab extends Tab {

    public EmployTab(GameSession gm) {
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
        ConsoleControl.printlnString("====== БИРЖА ТРУДА ======");
        ConsoleControl.printlnString("[1] Пилоты");
        ConsoleControl.printlnString("[2] Инженеры");
        ConsoleControl.printlnString("[0] Вернуться в главное меню");
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
        if(gm.getGameMode() == GameMode.CHUCK_NORRIS_APPEARANCE && req.equals("0")){
            if(gm.dorm().getEmployeesByJob(JobType.PILOT).isEmpty()){
                gm.setGameMode(GameMode.NORMAL);
                return new MainTab(gm);
            }
            else{
                return new AngryNorrisTab(gm);
            }
        }
        return switch (req) {
            case "1" -> new EmployeesByJobTab(gm, JobType.PILOT);
            case "2" -> new EmployeesByJobTab(gm, JobType.ENGINEER);
            case "0" -> new MainTab((gm));
            default -> null;
        };
    }
}
