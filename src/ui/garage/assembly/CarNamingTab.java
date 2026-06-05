package ui.garage.assembly;

import data.racecar.Racecar;
import game.GameSession;
import ui.base.Tab;
import ui.garage.GarageTab;

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
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("Автомобиль собран и помещён в гараж.");
        gm.io().printlnString("Сейчас он имеет название \""
                + chosenCar.getName() + "\"");
        gm.io().printlnString("Вы можете изменить его название");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите название и нажмите Enter");
    }

    private Tab menuHandler() {
        String request;
        while (true) {
            request = gm.io().getString();

            if (!request.isEmpty() && !request.replace(" ", "").isEmpty()) {
                chosenCar.setName(request);
                return new GarageTab(gm);
            } else {
                outputWithWarn("Имя болида не может быть пустым");
            }
        }
    }
}
