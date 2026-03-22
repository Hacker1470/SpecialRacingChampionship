package ui.garage;

import data.vehicle.Part;
import data.vehicle.PartType;
import data.vehicle.RacecarSample;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

public class CarAssemblyTab extends Tab {
    private RacecarSample sample;

    public CarAssemblyTab(GameSession gm) {
        super(gm);
        sample = new RacecarSample();
    }
    public CarAssemblyTab(GameSession gm, RacecarSample sample) {
        this(gm);
        this.sample = sample;
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
        ConsoleControl.printlnString("Выберите детали для сборки авто");
        ConsoleControl.printlnString("[1] Шасси\t\t\t\t" + getLinePart(sample.getChassis()));
        ConsoleControl.printlnString("[2] Двигатель\t\t\t" + getLinePart(sample.getEngine()));
        ConsoleControl.printlnString("[3] Трансмиссия\t\t\t" + getLinePart(sample.getTransmission()));
        ConsoleControl.printlnString("[4] Прижимная деталь\t" + getLinePart(sample.getDownforcePart()));
        ConsoleControl.printlnString("[5] Подвеска\t\t\t" + getLinePart(sample.getSuspension()));
        ConsoleControl.printlnString("[6] Колёса\t\t\t\t" + getLinePart(sample.getWheels()));
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать деталь N со склада");
        ConsoleControl.printlnString("[+] Далее");
        ConsoleControl.printlnString("[0] Вернуться к списку авто");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getLinePart(Part part) {
        if(part != null) {
            return "(выбрано: " + part.getName() + ")";
        }
        else {
            return "(не выбрано)";
        }
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
        return switch (req) {
            case "1" -> new CarAssemblyPartSelectTab(gm, sample, PartType.CHASSIS);
            case "2" -> new CarAssemblyPartSelectTab(gm, sample, PartType.ENGINE);
            case "3" -> new CarAssemblyPartSelectTab(gm, sample, PartType.TRANSMISSION);
            case "4" -> new CarAssemblyPartSelectTab(gm, sample, PartType.DOWNFORCE);
            case "5" -> new CarAssemblyPartSelectTab(gm, sample, PartType.SUSPENSION);
            case "6" -> new CarAssemblyPartSelectTab(gm, sample, PartType.WHEELS);
            case "0" -> new GarageTab(gm);
            default -> null;
        };
    }
}

