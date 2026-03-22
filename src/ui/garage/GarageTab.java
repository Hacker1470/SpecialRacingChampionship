package ui.garage;

import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.handling.ConsoleControl;
import ui.market.MarketTab;
import ui.market.PartInfoTab;

public class GarageTab extends Tab {

    public GarageTab(GameSession gm) {
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
        ConsoleControl.printlnString("ВАШ ГАРАЖ");

        if(gm.garage().getCarsNumber() == 0){
            printEmptyGarage();
        }
        else{
            printListOfMenusMain();
        }

        ConsoleControl.printlnString("[+] Собрать новый автомобиль");
        ConsoleControl.printlnString("[0] Выход в меню");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printEmptyGarage(){
        ConsoleControl.printlnString("Гараж пустует");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
    }

    private void printListOfMenusMain(){
        ConsoleControl.printlnString("В гараже стоят следующие авто");

        ConsoleControl.printlnString(gm.garage().getListOfCars());

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Открыть карточку машины N");
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
            return new MainTab(gm);
        }
        if(req.equals("+")){
            return new CarAssemblyTab(gm);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(gm.garage().getCarsNumber() > 0 && index >= 1  && index <= gm.garage().getCarsNumber()){
            return new CarInfoTab(gm, gm.garage().getCarByIndex(index - 1));
        }
        else {
            return null;
        }
    }
}
