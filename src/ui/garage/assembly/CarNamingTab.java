package ui.garage.assembly;

import data.vehicle.Part;
import data.vehicle.Racecar;
import game.GameSession;
import ui.base.Tab;
import ui.garage.GarageTab;
import ui.handling.ConsoleControl;
import ui.market.MarketTab;

public class CarNamingTab extends Tab {

    private final Racecar chosenCar;

    public CarNamingTab(GameSession gm, Racecar car) {
        super(gm);
        chosenCar = car;
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
        ConsoleControl.printlnString("Автомобиль собран и помещён в гараж.");
        ConsoleControl.printlnString("Сейчас он имеет название \""
                + chosenCar.getName() + "\"");
        ConsoleControl.printlnString("Вы можете изменить его название");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите название и нажмите Enter");
    }

    private Tab menuHandler(){
        String request;
        while (true){
            request = ConsoleControl.getString();

            if(!request.isEmpty() && !request.replace(" ", "").isEmpty()){
                chosenCar.setName(request);
                return new GarageTab(gm);
            }
            else {
                outputWithWarn("Имя болида не может быть пустым");
            }
        }
    }
}
