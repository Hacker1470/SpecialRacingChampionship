package ui.championship;

import data.crew.Engineer;
import data.crew.Pilot;
import data.race.Team;
import data.vehicle.*;
import data.vehicle.enums.PartType;
import game.GameSession;
import ui.base.Tab;
import ui.garage.GarageTab;
import ui.garage.assembly.CarNamingTab;
import ui.garage.assembly.EngineerSelectTab;
import ui.garage.assembly.PartSelectTab;
import ui.garage.assembly.assemblyexceptions.*;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.List;

public class UserTeamCreationTab extends Tab {
    private Team team;

    public UserTeamCreationTab(GameSession gm) {
        this(gm, new Team());
    }
    public UserTeamCreationTab(GameSession gm, Team team) {
        super(gm);
        this.team = team;
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
        ConsoleControl.printlnString("Соберите команду");
        ConsoleControl.printlnString("[1] Болид\t\t\t\t" + getLine(team.getCar()));
        ConsoleControl.printlnString("[2] Пилот\t\t\t" + getLine(team.getPilot()));
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Выбрать деталь N со склада");
        ConsoleControl.printlnString("[2] Выбрать инженера из общаги");
        ConsoleControl.printlnString("[+] Начать гонку");
        ConsoleControl.printlnString("[0] Вернуться к списку авто");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getLine(Racecar car) {
        if(car != null) {
            return "(выбрано: " + car.getName() + ")";
        }
        else {
            return "(не выбрано)";
        }
    }

    private String getLine(Pilot pilot) {
        if(pilot != null) {
            return "(выбрано: " + pilot.getName() + " " + pilot.getPostfix() + ")";
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
            case "1" -> new PilotSelectTab(gm, team);
            case "2" -> new CarSelectTab(gm, team);
            case "+" -> new Race(gm, team);
            case "0" -> new ChampionshipTab(gm);
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
                EngineerWantsMoneyException |
                NoPartAssemblyException |
                UnmatchingPartsAssemblyException e){
            return new UserTeamCreationTab(gm, sample,
                    "Авто не может быть собрано.\n" + e.getMessage());
        }
    }

    private static long idCounter = Long.MIN_VALUE + 1;

    public Racecar assemble() throws NoEngineerAssemblyException,
            NoPartAssemblyException,
            UnmatchingPartsAssemblyException,
            EngineerWantsMoneyException {
        if(sample.getEngineer() == null){
            throw new NoEngineerAssemblyException();
        }

        if(!gm.takeMoney(sample.getEngineer().getAssembleFee())){
            throw new EngineerWantsMoneyException(sample.getEngineer().getAssembleFee());
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

