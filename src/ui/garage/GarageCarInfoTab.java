package ui.garage;

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
        gm.warehouse().put(chosenCar.getSuspension());
        gm.warehouse().put(chosenCar.getWheels());
        gm.warehouse().put(chosenCar.getDownforcePart());

        gm.garage().deleteCar(chosenCar);
        chosenCar = null;
        /*
        * 20 03 2026 закончил здесь
        * Нужно добавить новую деталь - пустая деталь, так как не всегда нужна подвеска и
        * прижимная сила
        *
        * Далее продолжить делать сборку машины и тд
        * */
    }
}
