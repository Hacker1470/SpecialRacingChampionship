package ui.garage.assembly;

import data.crew.Engineer;
import data.parts.Part;
import data.special.AssemblingHelpers;
import data.racecar.*;
import data.parts.enums.PartType;
import game.GameSession;
import ui.base.Tab;
import ui.garage.GarageTab;
import ui.garage.assembly.assemblyexceptions.*;

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
        if (warn == null) {
            outputMain();
        } else {
            outputWithWarn(warn);
        }
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("Выберите детали для сборки авто");
        gm.io().printlnString("[1] Шасси\t\t\t\t" + getLinePart(sample.getChassis()));
        gm.io().printlnString("[2] Двигатель\t\t\t" + getLinePart(sample.getEngine()));
        gm.io().printlnString("[3] Трансмиссия\t\t\t" + getLinePart(sample.getTransmission()));
        gm.io().printlnString("[4] Прижимная деталь\t" + getLinePart(sample.getDownforcePart()));
        gm.io().printlnString("[5] Подвеска\t\t\t" + getLinePart(sample.getSuspension()));
        gm.io().printlnString("[6] Колёса\t\t\t\t" + getLinePart(sample.getWheels()));
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Назначьте инженера, который будет собирать болид");
        gm.io().printlnString("[*] Инженер\t\t\t\t" + getLineEngineer(sample.getEngineer()));
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Выбрать деталь N со склада");
        gm.io().printlnString("[*] Выбрать инженера из общаги");
        gm.io().printlnString("[+] Собрать болид");
        gm.io().printlnString("[0] Вернуться к списку авто");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getLinePart(Part part) {
        if (part != null) {
            return "(выбрано: " + part.getName() + " " + part.getPostfix() + ")";
        } else {
            return "(не выбрано)";
        }
    }

    private String getLineEngineer(Engineer engineer) {
        if (engineer != null) {
            return "(выбран: " + engineer.getName() + " " + engineer.getPostfix() + ")";
        } else {
            return "(не выбран)";
        }
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
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

    private Tab assembleRacecar() {
        try {
            Racecar newCar = assemble();
            gm.garage().put(newCar);
            return new CarNamingTab(gm, newCar);
        } catch (NoEngineerAssemblyException |
                 EngineerWantsMoneyException |
                 NoPartAssemblyException |
                 UnmatchingPartsAssemblyException e) {
            return new CarAssemblyTab(gm, sample,
                    "Авто не может быть собрано.\n" + e.getMessage());
        }
    }

    private static long idCounter = Long.MIN_VALUE + 1;

    public Racecar assemble() throws NoEngineerAssemblyException,
            NoPartAssemblyException,
            UnmatchingPartsAssemblyException,
            EngineerWantsMoneyException {
        if (sample.getEngineer() == null) {
            throw new NoEngineerAssemblyException();
        }

        if (gm.getMoney() - sample.getEngineer().getSalary(1) < 0) {
            throw new EngineerWantsMoneyException(sample.getEngineer().getSalary(1));
        }

        checkNecessaryParts();
        checkWeight();
        AssemblingHelpers.checkPartsMatching(sample);

        gm.takeMoney(sample.getEngineer().getSalary(1));
        AssemblingHelpers.addEngineerPerks(sample);
        sample.getEngineer().setExperience(sample.getEngineer().getExperience() + 1);

        deletePartsFromWarehouse();

        return new Racecar(idCounter++, "Болид", sample);
    }

    private void checkNecessaryParts() throws NoPartAssemblyException {
        if (sample.getChassis() == null) {
            throw new NoPartAssemblyException(PartType.CHASSIS);
        }
        if (sample.getEngine() == null) {
            throw new NoPartAssemblyException(PartType.ENGINE);
        }
        if (sample.getTransmission() == null) {
            throw new NoPartAssemblyException(PartType.TRANSMISSION);
        }
        if (sample.getWheels() == null) {
            throw new NoPartAssemblyException(PartType.WHEELS);
        }
    }

    private void checkWeight() throws OverweightAssemblyException {
        if (sample.getWeight() > sample.getChassis().getMaxWeight()) {
            throw new OverweightAssemblyException(sample.getChassis(),
                    sample.getChassis().getMaxWeight());
        }
    }

    private void deletePartsFromWarehouse() {
        gm.warehouse().remove(sample.getChassis());
        gm.warehouse().remove(sample.getEngine());
        gm.warehouse().remove(sample.getTransmission());
        gm.warehouse().remove(sample.getWheels());
        if (sample.getSuspension() != null) {
            gm.warehouse().remove(sample.getSuspension());
        }
        if (sample.getDownforcePart() != null) {
            gm.warehouse().remove(sample.getDownforcePart());
        }
    }
}

