package data.vehicle;

public class Racecar {
    protected long id;
    protected String name;
    protected Chassis chassis;
    protected Engine engine;
    protected DownforcePart downforcePart;
    protected Transmission transmission;
    protected Suspension suspension;
    protected Wheels wheels;

    public Racecar(long id, String name, Chassis chassis, Engine engine, Transmission transmission,
                   DownforcePart downforcePart, Suspension suspension, Wheels wheels){
        this.id = id;
        this.name = name;
        this.chassis = chassis;
        this.engine = engine;
        this.transmission = transmission;
        this.downforcePart = downforcePart;
        this.suspension = suspension;
        this.wheels = wheels;
    }

    public void rebuild(Chassis chassis, Engine engine, Transmission transmission, DownforcePart downforcePart,
                        Suspension suspension, Wheels wheels){
        this.chassis = chassis;
        this.engine = engine;
        this.transmission = transmission;
        this.downforcePart = downforcePart;
        this.suspension = suspension;
        this.wheels = wheels;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Chassis getChassis(){
        return chassis;
    }
    public Engine getEngine(){
        return engine;
    }
    public Transmission getTransmission(){
        return transmission;
    }
    public DownforcePart getDownforcePart(){
        return downforcePart;
    }
    public Suspension getSuspension(){
        return suspension;
    }
    public Wheels getWheels(){
        return wheels;
    }

    public Integer getWeight(){
        return chassis.getMass() + engine.getMass() + downforcePart.getMass() + transmission.getMass() +
                suspension.getMass() + wheels.getMass();
    }
}
