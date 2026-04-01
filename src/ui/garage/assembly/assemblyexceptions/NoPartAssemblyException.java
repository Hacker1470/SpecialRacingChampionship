package ui.garage.assembly.assemblyexceptions;

import data.vehicle.enums.PartType;

public class NoPartAssemblyException extends Exception {
    public NoPartAssemblyException(PartType pt) {
        super("Не назначена обязательная деталь - " + pt.getSimpleName());
    }
}
