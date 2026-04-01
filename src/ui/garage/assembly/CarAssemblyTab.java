package ui.garage.assembly;

import data.crew.Engineer;
import data.vehicle.*;
import data.vehicle.enums.PartType;
import game.GameSession;
import ui.base.Tab;
import ui.garage.GarageTab;
import ui.garage.assembly.assemblyexceptions.NoEngineerAssemblyException;
import ui.garage.assembly.assemblyexceptions.NoPartAssemblyException;
import ui.garage.assembly.assemblyexceptions.OverweightAssemblyException;
import ui.garage.assembly.assemblyexceptions.UnmatchingPartsAssemblyException;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.List;

public class CarAssemblyTab extends Tab {
    private RacecarSample sample;
    private String warn = null;

    public CarAssemblyTab(GameSession gm) {
        super(gm);
        sample = new RacecarSample();
    }
    public CarAssemblyTab(GameSession gm, RacecarSample sample) {
        this(gm);
        this.sample = sample;
    }
    public CarAssemblyTab(GameSession gm, RacecarSample sample, String warn) {
        this(gm);
        this.sample = sample;
        this.warn = warn;
    }

    @Override
    public Tab show() {
        if(warn == null){
            outputMain();
        }
        else {
            outputWithWarn(warn);
        }
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
        ConsoleControl.printlnString("Назначьте инженера, который будет собирать болид");
        ConsoleControl.printlnString("[*] Инженер\t\t\t\t" + getLineEngineer(sample.getEngineer()));
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать деталь N со склада");
        ConsoleControl.printlnString("[*] Выбрать инженера из общаги");
        ConsoleControl.printlnString("[+] Собрать болид");
        ConsoleControl.printlnString("[0] Вернуться к списку авто");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getLinePart(Part part) {
        if(part != null) {
            return "(выбрано: " + part.getName() + " " + part.getPostfix() + ")";
        }
        else {
            return "(не выбрано)";
        }
    }
    private String getLineEngineer(Engineer engineer) {
        if(engineer != null) {
            return "(выбран: " + engineer.getName() + " " + engineer.getPostfix() + ")";
        }
        else {
            return "(не выбран)";
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
            case "1" -> new PartSelectTab(gm, sample, PartType.CHASSIS);
            case "2" -> new PartSelectTab(gm, sample, PartType.ENGINE);
            case "3" -> new PartSelectTab(gm, sample, PartType.TRANSMISSION);
            case "4" -> new PartSelectTab(gm, sample, PartType.DOWNFORCE);
            case "5" -> new PartSelectTab(gm, sample, PartType.SUSPENSION);
            case "6" -> new PartSelectTab(gm, sample, PartType.WHEELS);
            case "*" -> new EngineerSelectTab(gm, sample);
            case "+" -> assembleRacecar();
            case "0" -> new GarageTab(gm);
            default -> null;
        };
    }

    private Tab assembleRacecar(){
        try{
            Racecar newCar = assemble();
            gm.garage().put(newCar);
            return new CarNamingTab(gm, newCar);
        }
        catch (NoEngineerAssemblyException |
                NoPartAssemblyException |
                UnmatchingPartsAssemblyException e){
            return new CarAssemblyTab(gm, sample,
                    "Авто не может быть собрано.\n" + e.getMessage());
        }
    }

    private static long idCounter = Long.MIN_VALUE + 1;

    public Racecar assemble() throws NoEngineerAssemblyException,
            NoPartAssemblyException,
            UnmatchingPartsAssemblyException {
        if(sample.getEngineer() == null){
            throw new NoEngineerAssemblyException();
        }

        checkNecessaryParts();
        checkWeight();
        checkPartsMatching();

        addPerks();
        deletePartsFromWarehouse();

        return new Racecar(idCounter++, "Болид", sample.getChassis(), sample.getEngine(),
                sample.getTransmission(), sample.getDownforcePart(),
                sample.getSuspension(), sample.getWheels());
    }

    private void checkNecessaryParts() throws NoPartAssemblyException{
        if(sample.getChassis() == null){
            throw new NoPartAssemblyException(PartType.CHASSIS);
        }
        if(sample.getEngine() == null){
            throw new NoPartAssemblyException(PartType.ENGINE);
        }
        if(sample.getTransmission() == null){
            throw new NoPartAssemblyException(PartType.TRANSMISSION);
        }
        if(sample.getWheels() == null){
            throw new NoPartAssemblyException(PartType.WHEELS);
        }
    }

    private void checkWeight() throws OverweightAssemblyException {
        if(sample.getWeight() > sample.getChassis().getMaxWeight()){
            throw new OverweightAssemblyException(sample.getChassis(),
                    sample.getChassis().getMaxWeight());
        }
    }

    private void checkPartsMatching() throws UnmatchingPartsAssemblyException{
        List<Part> parts = new ArrayList<>(List.of(
                sample.getChassis(), sample.getEngine(),
                sample.getTransmission(), sample.getWheels()));
        List<String> articles = new ArrayList<>(List.of(
                sample.getChassis().getArticle(), sample.getEngine().getArticle(),
                sample.getTransmission().getArticle(), sample.getWheels().getArticle()));

        if(sample.getSuspension() != null){
            parts.add(sample.getSuspension());
            articles.add(sample.getSuspension().getArticle());
        }
        if(sample.getDownforcePart() != null){
            parts.add(sample.getDownforcePart());
            articles.add(sample.getDownforcePart().getArticle());
        }

        List<String> connectivities;
        boolean estProbitie;
        for(Part part : parts){
            connectivities = new ArrayList<>(part.getConnectivity());
            while(!connectivities.isEmpty()){
                String currentType = connectivities.getFirst().substring(0,3);
                estProbitie = false;
                for(String str : connectivities.stream()
                        .filter(n -> n.startsWith(currentType))
                        .toList()){
                    if(articles.contains(str)){
                        estProbitie = true;
                        break;
                    }
                }
                if(estProbitie){
                    connectivities = connectivities.stream().filter(n -> !n.startsWith(currentType)).toList();
                }
                else {
                    throw new UnmatchingPartsAssemblyException(part);
                }
            }
        }
    }

    private void addPerks(){
        Engineer engineer = sample.getEngineer();
        Chassis chassis = sample.getChassis();
        Engine engine = sample.getEngine();
        Transmission transmission = sample.getTransmission();
        Wheels wheels = sample.getWheels();
        Suspension suspension = sample.getSuspension();
        DownforcePart downforcePart = sample.getDownforcePart();

        chassis.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        engine.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        transmission.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        wheels.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        if(suspension != null){
            suspension.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        }
        if(downforcePart != null){
            downforcePart.setConnectionReliability(50 - engineer.getWawyHands()/2 + engineer.getScrewing());
        }
    }

    private void deletePartsFromWarehouse(){
        gm.warehouse().remove(sample.getChassis());
        gm.warehouse().remove(sample.getEngine());
        gm.warehouse().remove(sample.getTransmission());
        gm.warehouse().remove(sample.getWheels());
        if(sample.getSuspension() != null){
            gm.warehouse().remove(sample.getSuspension());
        }
        if(sample.getDownforcePart() != null){
            gm.warehouse().remove(sample.getDownforcePart());
        }
    }
}

