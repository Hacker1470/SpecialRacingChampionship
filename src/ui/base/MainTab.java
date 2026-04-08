package ui.base;

import game.GameMode;
import game.GameSession;
import game.Hospital;
import ui.championship.ChampionshipTab;
import ui.dorm.DormTab;
import ui.employment.EmployTab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.hospital.HospitalTab;
import ui.market.MarketTab;
import ui.stats.StatisticsTab;
import ui.warehouse.WarehouseTab;

public class MainTab extends Tab {

    public MainTab(GameSession gm){
        super(gm);
        if(gm.getHospital() != null){
            gm.getHospital().update();
        }
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
        ConsoleControl.printlnString("Реп: " + gm.getRep());
        ConsoleControl.printlnString("Баланс: " + gm.getMoney() + " грошей");
        ConsoleControl.printlnString("[1] Чемпионаты");
        ConsoleControl.printlnString("[2] Статистика");
        ConsoleControl.printlnString("[3] Гараж");
        ConsoleControl.printlnString("[4] Склад");
        ConsoleControl.printlnString("[5] Мир запчастей");
        ConsoleControl.printlnString("[6] Общежитие");
        ConsoleControl.printlnString("[7] Биржа труда");
        if(gm.getHospital() != null){
            ConsoleControl.printlnString("[8] Госпиталь");
        }
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
        if(gm.getHospital() != null && req.equals("8")){
            return new HospitalTab(gm);
        }
        return switch (req) {
            case "1" -> new ChampionshipTab(gm);
            case "2" -> new StatisticsTab(gm);
            case "3" -> new GarageTab(gm);
            case "4" -> new WarehouseTab(gm);
            case "5" -> new MarketTab(gm);
            case "6" -> new DormTab(gm);
            case "7" -> new EmployTab(gm);
            default -> null;
        };
    }
}
