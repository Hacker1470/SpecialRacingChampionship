package ui.garage;

import data.parts.enums.PartType;
import data.racecar.Racecar;
import game.GameSession;
import ui.base.Tab;

public class GarageCarInfoTab extends Tab {

    private Racecar chosenCar;

    public GarageCarInfoTab(GameSession gm, Racecar car) {
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
        gm.io().printlnString("ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА");
        gm.io().printlnString("");

        printCharacteristics();

        gm.io().printlnString("=============================================");
        gm.io().printlnString("[1] Разобрать");
        gm.io().printlnString("[0] Вернуться к списку");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            switch (request) {
                case "0":
                    response = new GarageTab(gm);
                    break;
                case "1":
                    carDisassembly();
                    response = new GarageTab(gm);
                    break;
                default:
                    outputWithWarn("Меню не имеет пункта: " + request);
            }
        }


        return response;
    }

    private void carDisassembly() {
        gm.warehouse().put(chosenCar.getEngine());
        gm.warehouse().put(chosenCar.getTransmission());
        gm.warehouse().put(chosenCar.getChassis());
        gm.warehouse().put(chosenCar.getWheels());
        if (chosenCar.getSuspension() != null) {
            gm.warehouse().put(chosenCar.getSuspension());
        }
        if (chosenCar.getDownforcePart() != null) {
            gm.warehouse().put(chosenCar.getDownforcePart());
        }

        gm.garage().deleteCar(chosenCar.getId());
        chosenCar = null;
        /*
         * 20 03 2026 закончил здесь
         * Нужно добавить новую деталь - пустая деталь, так как не всегда нужна подвеска и
         * прижимная сила
         *
         * Далее продолжить делать сборку машины и тд
         * */
    }

    private void printCharacteristics() {
        gm.io().printlnString("Болид: " + chosenCar.getName());
        gm.io().printlnString(PartType.CHASSIS.getMarketInfoTitle());
        gm.io().printlnString(chosenCar.getChassis().getGarageCharacteristics());
        gm.io().printlnString(PartType.ENGINE.getMarketInfoTitle());
        gm.io().printlnString(chosenCar.getEngine().getGarageCharacteristics());
        gm.io().printlnString(PartType.TRANSMISSION.getMarketInfoTitle());
        gm.io().printlnString(chosenCar.getTransmission().getGarageCharacteristics());
        gm.io().printlnString(PartType.WHEELS.getMarketInfoTitle());
        gm.io().printlnString(chosenCar.getWheels().getGarageCharacteristics());
        if (chosenCar.getSuspension() != null) {
            gm.io().printlnString(PartType.SUSPENSION.getMarketInfoTitle());
            gm.io().printlnString(chosenCar.getSuspension().getGarageCharacteristics());
        }
        if (chosenCar.getSuspension() != null) {
            gm.io().printlnString(PartType.DOWNFORCE.getMarketInfoTitle());
            gm.io().printlnString(chosenCar.getDownforcePart().getGarageCharacteristics());
        }
    }
}
