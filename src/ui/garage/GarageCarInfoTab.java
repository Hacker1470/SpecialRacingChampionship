package ui.garage;

import data.vehicle.enums.PartType;
import data.vehicle.Racecar;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

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
    protected void printListOfMenus(){
        ConsoleControl.printlnString("ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА");
        ConsoleControl.printlnString("");

        printCharacteristics();

        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Разобрать");
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

    private void carDisassembly(){
        gm.warehouse().put(chosenCar.getEngine());
        gm.warehouse().put(chosenCar.getTransmission());
        gm.warehouse().put(chosenCar.getChassis());
        gm.warehouse().put(chosenCar.getWheels());
        if(chosenCar.getSuspension() != null){
            gm.warehouse().put(chosenCar.getSuspension());
        }
        if(chosenCar.getDownforcePart() != null){
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

    private void printCharacteristics(){
        ConsoleControl.printlnString("Болид: " + chosenCar.getName());
        ConsoleControl.printlnString(PartType.CHASSIS.getMarketInfoTitle());
        ConsoleControl.printlnString(chosenCar.getChassis().getGarageCharacteristics());
        ConsoleControl.printlnString(PartType.ENGINE.getMarketInfoTitle());
        ConsoleControl.printlnString(chosenCar.getEngine().getGarageCharacteristics());
        ConsoleControl.printlnString(PartType.TRANSMISSION.getMarketInfoTitle());
        ConsoleControl.printlnString(chosenCar.getTransmission().getGarageCharacteristics());
        ConsoleControl.printlnString(PartType.WHEELS.getMarketInfoTitle());
        ConsoleControl.printlnString(chosenCar.getWheels().getGarageCharacteristics());
        if(chosenCar.getSuspension() != null){
            ConsoleControl.printlnString(PartType.SUSPENSION.getMarketInfoTitle());
            ConsoleControl.printlnString(chosenCar.getSuspension().getGarageCharacteristics());
        }
        if(chosenCar.getSuspension() != null){
            ConsoleControl.printlnString(PartType.DOWNFORCE.getMarketInfoTitle());
            ConsoleControl.printlnString(chosenCar.getDownforcePart().getGarageCharacteristics());
        }
    }
}
