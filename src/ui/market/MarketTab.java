package ui.market;

import game.GameSession;
import data.vehicle.PartType;
import ui.base.MainTab;
import ui.employment.EmployTab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.base.Tab;

public class MarketTab extends Tab {

    public MarketTab(GameSession gm) {
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
        ConsoleControl.printlnString("====== РЫНОК ======");
        ConsoleControl.printlnString("[1] Шасси");
        ConsoleControl.printlnString("[2] Двигатели");
        ConsoleControl.printlnString("[3] Коробки");
        ConsoleControl.printlnString("[4] Колёса");
        ConsoleControl.printlnString("[5] Подвеска");
        ConsoleControl.printlnString("[6] Обвесы для прижимной силы");
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
        return switch (req) {
            case "1" -> new PartsByTypeTab(gm, PartType.CHASSIS);
            case "2" -> new PartsByTypeTab(gm, PartType.ENGINE);
            case "3" -> new PartsByTypeTab(gm, PartType.TRANSMISSION);
            case "4" -> new PartsByTypeTab(gm, PartType.WHEELS);
            case "5" -> new PartsByTypeTab(gm, PartType.SUSPENSION);
            case "6" -> new PartsByTypeTab(gm, PartType.DOWNFORCE);
            case "0" -> new MainTab(gm);
            default -> null;
        };
    }
}
