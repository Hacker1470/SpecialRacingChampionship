package ui.base;

import game.GameSession;
import ui.employment.EmployTab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.market.MarketTab;

public class MainTab extends Tab {

    public MainTab(GameSession gm){
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
        ConsoleControl.printlnString("Баланс: " + gm.getMoney() + " грошей");
        ConsoleControl.printlnString("[1] Чемпионаты");
        ConsoleControl.printlnString("[2] Статистика");
        ConsoleControl.printlnString("[3] Гараж");
        ConsoleControl.printlnString("[4] Склад");
        ConsoleControl.printlnString("[5] Мир запчастей");
        ConsoleControl.printlnString("[6] Общежитие");
        ConsoleControl.printlnString("[7] Биржа труда");
        ConsoleControl.printlnString("[0] Выход из игры");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();
            if(request.equals("0")){
                gm.exit();
            }

            response = selectResponse(request);
            if (response == null){
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        return switch (req) {
            case "1" -> null;
            case "2" -> null;
            case "3" -> new GarageTab(gm);
            case "4" -> null;
            case "5" -> new MarketTab(gm);
            case "6" -> null;
            case "7" -> new EmployTab(gm);
            default -> null;
        };
    }
}
