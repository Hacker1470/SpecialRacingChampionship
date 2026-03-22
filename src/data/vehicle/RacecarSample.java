package data.vehicle;

public class RacecarSample extends Racecar {

    public RacecarSample() {
        super(Long.MIN_VALUE, "none", null, null, null,
                null, null, null);
    }

    public void setChassis(Chassis chassis){
        this.chassis = chassis;
    }
    public void setEngine(Engine engine){
        this.engine = engine;
    }
    public void setTransmission(Transmission transmission){
        this.transmission = transmission;
    }
    public void setDownforcePart(DownforcePart downforcePart){
        this.downforcePart = downforcePart;
    }
    public void setSuspension(Suspension suspension){
        this.suspension = suspension;
    }
    public void setWheels(Wheels wheels){
        this.wheels = wheels;
    }



}
