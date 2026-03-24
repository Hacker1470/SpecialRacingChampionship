package ui.market;

import data.vehicle.Part;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

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
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("Деталь приобретена и помещена на склад.");
        ConsoleControl.printlnString("Сейчас она имеет название \""
                + chosenPart.getName() + chosenPart.getPostfix() + "\"");
        ConsoleControl.printlnString("Вы можете добавить приписку справа");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Нажмите Enter, чтобы пропустить этот пункт");
        ConsoleControl.printlnString("или введите строку, для добавления приписки");
    }

    private Tab menuHandler(){
        String request;
        request = ConsoleControl.getString();

        if(!request.isEmpty()){
            chosenPart.setPostfix(request);
        }
        return new MarketTab(gm);
    }
}
