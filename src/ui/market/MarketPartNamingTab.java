package ui.market;

import data.parts.Part;
import game.GameSession;
import ui.base.Tab;

public class MarketPartNamingTab extends Tab {

    private final Part chosenPart;

    public MarketPartNamingTab(GameSession gm, Part part) {
        super(gm);
        chosenPart = part;
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
        gm.io().printlnString("Деталь приобретена и помещена на склад.");
        gm.io().printlnString("Сейчас она имеет название \""
                + chosenPart.getName() + chosenPart.getPostfix() + "\"");
        gm.io().printlnString("Вы можете добавить приписку справа");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Нажмите Enter, чтобы пропустить этот пункт");
        gm.io().printlnString("или введите строку, для добавления приписки");
    }

    private Tab menuHandler() {
        String request;
        request = gm.io().getString();

        if (!request.isEmpty()) {
            chosenPart.setPostfix(request);
        }
        return new MarketTab(gm);
    }
}
