package ui.base;

import game.GameSession;
import ui.championship.ChampionshipTab;
import ui.dorm.DormTab;
import ui.employment.EmployTab;
import ui.garage.GarageTab;
import ui.hospital.HospitalTab;
import ui.market.MarketTab;
import ui.stats.StatisticsTab;
import ui.warehouse.WarehouseTab;

public class MainTab extends Tab {

    public MainTab(GameSession gm) {
        super(gm);
        if (gm.hospital() != null) {
            gm.hospital().update();
        }
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
        gm.io().printlnString("Реп: " + gm.getRep());
        gm.io().printlnString("Баланс: " + gm.getMoney() + " грошей");
        gm.io().printlnString("[1] Чемпионаты");
        gm.io().printlnString("[2] Статистика");
        gm.io().printlnString("[3] Гараж");
        gm.io().printlnString("[4] Склад");
        gm.io().printlnString("[5] Мир запчастей");
        gm.io().printlnString("[6] Общежитие");
        gm.io().printlnString("[7] Биржа труда");
        if (gm.hospital() != null) {
            gm.io().printlnString("[8] Госпиталь");
        }
        gm.io().printlnString("[0] Выход из игры");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();
            if (request.equals("0")) {
                gm.exit();
            }

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
        if (gm.hospital() != null && req.equals("8")) {
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
