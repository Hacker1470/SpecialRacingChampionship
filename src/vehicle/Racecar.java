package vehicle;

public class Racecar {
    private Chassis chassis;
    private Engine engine;
    private DownforcePart downforcePart;
    private Transmission transmission;
    private Suspension suspension;
    private Wheels wheels;

    public Integer getWeight(){
        return chassis.mass + engine.mass + downforcePart.mass + transmission.mass + suspension.mass + wheels.mass;
    }
}
