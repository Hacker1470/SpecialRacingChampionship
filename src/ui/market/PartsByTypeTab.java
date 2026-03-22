package ui.market;

import game.GameSession;
import data.catalogs.CatalogOfParts;
import data.vehicle.PartType;
import ui.employment.EmployTab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.base.Tab;
import data.vehicle.Part;

import java.util.ArrayList;

public class PartsByTypeTab extends Tab {

    ArrayList<Part> availablePartsByRep;
    PartType type;

    public PartsByTypeTab(GameSession gm, PartType type) {
        super(gm);
        this.type = type;
    }

    @Override
    public Tab show() {
        availablePartsByRep = CatalogOfParts.getAvailableByReputation(type, gm.getRep());
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString(type.getMarketGroupTitle());

        for(int i = 1; i <= availablePartsByRep.size(); i++){
            ConsoleControl.printlnString("[" + i + "] " + availablePartsByRep.get(i - 1).getName());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("[0] Вернуться на рынок");
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
            return new MarketTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(index <= availablePartsByRep.size() && index >= 1) {
            return new PartInfoTab(gm, availablePartsByRep.get(index - 1));
        }
        else {
            return null;
        }
    }
}
