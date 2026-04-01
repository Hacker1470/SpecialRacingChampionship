package ui.garage.assembly;

import data.vehicle.*;
import data.vehicle.enums.PartType;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

import java.util.List;

public class PartSelectTab extends Tab {
    List<Part> availableParts;
    RacecarSample sample;
    PartType type;

    public PartSelectTab(GameSession gm, RacecarSample sample, PartType type) {
        super(gm);
        this.sample = sample;
        this.type = type;
    }

    @Override
    public Tab show() {
        availableParts = gm.warehouse().getPartsByType(type);
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString(type.getMarketGroupTitle());

        if(availableParts.isEmpty()){
            printListOfMenusNoParts();
        }
        else{
            printListOfMenusMain();
        }

        ConsoleControl.printlnString("[0] Вернуться к сборке");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printListOfMenusNoParts(){
        ConsoleControl.printlnString("На складе нет деталей такого типа");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
    }

    private void printListOfMenusMain(){
        int counter = 1;
        for(Part part : availableParts){
            ConsoleControl.printlnString("[" + counter++ + "] " + part.getName() + " " + part.getPostfix());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать деталь под номером N");
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
            return new CarAssemblyTab(gm, sample);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(index >= 1 && index <= availableParts.size()){
            setPartToSample(index - 1);
            return new CarAssemblyTab(gm, sample);
        }
        else {
            return null;
        }
    }

    private void setPartToSample(int index){
        switch (type){
            case PartType.CHASSIS:
                sample.setChassis((Chassis) availableParts.get(index));
                break;
            case PartType.ENGINE:
                sample.setEngine((Engine) availableParts.get(index));
                break;
            case PartType.TRANSMISSION:
                sample.setTransmission((Transmission) availableParts.get(index));
                break;
            case PartType.DOWNFORCE:
                sample.setDownforcePart((DownforcePart) availableParts.get(index));
                break;
            case PartType.SUSPENSION:
                sample.setSuspension((Suspension) availableParts.get(index));
                break;
            case PartType.WHEELS:
                sample.setWheels((Wheels) availableParts.get(index));
                break;
        }
    }
}
