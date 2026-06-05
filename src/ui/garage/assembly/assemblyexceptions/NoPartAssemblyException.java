package ui.garage.assembly.assemblyexceptions;

import data.parts.enums.PartType;

public class NoPartAssemblyException extends Exception {
    public NoPartAssemblyException(PartType pt) {
        super("Не назначена обязательная деталь - " + pt.getSimpleName());
    }
}
