package data.racecar;

import data.crew.Engineer;
import data.parts.*;

public class RacecarSample extends AbstractRacecar {

    private Engineer engineer;

    public RacecarSample() {
        super(null, null, null,
                null, null, null);
        engineer = null;
    }

    // Геттеры ======================================================

    public Engineer getEngineer() {
        return engineer;
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    // Сеттеры ============================================================

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }
    public void setDownforcePart(DownforcePart downforcePart) {
        this.downforcePart = downforcePart;
    }
    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }
    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
