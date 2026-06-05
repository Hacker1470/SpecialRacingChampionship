package data.racecar;

import data.parts.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRacecar {
    protected Chassis chassis;
    protected Engine engine;
    protected DownforcePart downforcePart;
    protected Transmission transmission;
    protected Suspension suspension;
    protected Wheels wheels;

    public AbstractRacecar(Chassis chassis, Engine engine, Transmission transmission, Wheels wheels,
                           Suspension suspension, DownforcePart downforcePart) {
        this.chassis = chassis;
        this.engine = engine;
        this.transmission = transmission;
        this.wheels = wheels;
        this.suspension = suspension;
        this.downforcePart = downforcePart;
    }

    // Геттеры ============================================================

    public Chassis getChassis() {
        return chassis;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public DownforcePart getDownforcePart() {
        return downforcePart;
    }

    //====================================================================

    public ArrayList<Part> getParts() {
        return new ArrayList<>(List.of(
                chassis, engine, transmission, wheels, suspension, downforcePart
        ));
    }

    public ArrayList<Part> getNotNullParts() {
        ArrayList<Part> result = new ArrayList<>();

        if (chassis != null) result.add(chassis);
        if (engine != null) result.add(engine);
        if (transmission != null) result.add(transmission);
        if (wheels != null) result.add(wheels);
        if (suspension != null) result.add(suspension);
        if (downforcePart != null) result.add(downforcePart);

        return result;
    }

    public Integer getWeight() {
        int mass = 0;

        if (chassis != null) {
            mass += chassis.getMass();
        }
        if (engine != null) {
            mass += engine.getMass();
        }
        if (transmission != null) {
            mass += transmission.getMass();
        }
        if (wheels != null) {
            mass += wheels.getMass();
        }
        if (suspension != null) {
            mass += suspension.getMass();
        }
        if (downforcePart != null) {
            mass += downforcePart.getMass();
        }

        return mass;
    }
}
