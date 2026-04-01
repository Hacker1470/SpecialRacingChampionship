package ui.garage.assembly.assemblyexceptions;

import data.vehicle.Chassis;
import data.vehicle.Part;

public class OverweightAssemblyException extends RuntimeException {
    public OverweightAssemblyException(Chassis chassis, int mass) {
        super("Корпус авто не способен выдержать вес всех деталей" +
                "Ожидаемая масса: " + chassis.getMaxWeight() + "кг\n" +
                "Реальная масса: " + mass + "кг");
    }
}