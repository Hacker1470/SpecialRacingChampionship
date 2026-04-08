package ui.market;

import game.GameSession;
import ui.handling.ConsoleControl;
import ui.base.Tab;
import data.vehicle.Part;

public class MarketPartInfoTab extends Tab {

    private static long idCounter = Long.MIN_VALUE + 1;
    private final Part chosenPart;

    public MarketPartInfoTab(GameSession gm, Part part) {
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
        ConsoleControl.printlnString(chosenPart.getType().getMarketInfoTitle());

        ConsoleControl.printlnString(chosenPart.getMarketCharacteristics());

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Купить (Баланс: " + gm.getMoney() + " грошей)");
        ConsoleControl.printlnString("[0] Вернуться к списку");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            switch (request){
                case "0":
                    response = new MarketPartsByTypeTab(gm, chosenPart.getType());
                    break;
                case "1":
                    if(buyPart()){
                        response = new MarketPartNamingTab(gm, gm.warehouse().getPartById(idCounter - 1));
                    }
                    else {
                        outputWithWarn("Недостаточно средств");
                    }
                    break;
                default:
                    outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private boolean buyPart(){
        if(gm.takeMoney(chosenPart.getRealPrice())) {
            gm.warehouse().put(chosenPart.getCopy(idCounter++));
            return true;
        }
        else {
            return false;
        }
    }
}
