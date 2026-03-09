package data.vehicle;

public class Racecar {
    private Chassis chassis;
    private Engine engine;
    private DownforcePart downforcePart;
    private Transmission transmission;
    private Suspension suspension;
    private Wheels wheels;

    public Integer getWeight(){
        return chassis.getMass() + engine.getMass() + downforcePart.getMass() + transmission.getMass() +
                suspension.getMass() + wheels.getMass();
    }
}
