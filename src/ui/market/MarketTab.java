package ui.market;

import game.GameSession;
import data.parts.enums.PartType;
import ui.base.MainTab;
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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("====== РЫНОК ======");
        gm.io().printlnString("[1] Шасси");
        gm.io().printlnString("[2] Двигатели");
        gm.io().printlnString("[3] Коробки");
        gm.io().printlnString("[4] Колёса");
        gm.io().printlnString("[5] Подвеска");
        gm.io().printlnString("[6] Обвесы для прижимной силы");
        gm.io().printlnString("[0] Вернуться в главное меню");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
        return switch (req) {
            case "1" -> new MarketPartsByTypeTab(gm, PartType.CHASSIS);
            case "2" -> new MarketPartsByTypeTab(gm, PartType.ENGINE);
            case "3" -> new MarketPartsByTypeTab(gm, PartType.TRANSMISSION);
            case "4" -> new MarketPartsByTypeTab(gm, PartType.WHEELS);
            case "5" -> new MarketPartsByTypeTab(gm, PartType.SUSPENSION);
            case "6" -> new MarketPartsByTypeTab(gm, PartType.DOWNFORCE);
            case "0" -> new MainTab(gm);
            default -> null;
        };
    }
}
