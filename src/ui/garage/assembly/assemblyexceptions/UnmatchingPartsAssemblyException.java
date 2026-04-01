package ui.garage.assembly.assemblyexceptions;

import data.vehicle.Part;

public class UnmatchingPartsAssemblyException extends Exception {
    public UnmatchingPartsAssemblyException(Part p1) {
        super("Авто не содержит деталей, которые требует" +
                 p1.getName() + " " + p1.getPostfix());
    }
}
