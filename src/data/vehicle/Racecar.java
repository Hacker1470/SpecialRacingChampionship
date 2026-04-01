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
        int mass = chassis.getMass() + engine.getMass() + transmission.getMass() + wheels.getMass();
        if(suspension != null){
            mass += suspension.getMass();
        }
        if(downforcePart != null){
            mass += downforcePart.getMass();
        }
        return mass;
    }

    /**
     * V_база
     */
    public int getBaseSpeed(){
        return Math.min(transmission.getMaxSpeed(),
                (engine.getPower() * engine.getMaxRpm())/(getWeight() * 100));

    }

    /**
     * К_аэро
     * @return
     */
    public double getAeroCoef(){
        return 1 + (chassis.getAerodynamics() / 200d);
    }

    /**
     * К_прижим = К_прижим_поворот
     * @return
     */
    public double getDownFCoef(){
        if(downforcePart == null){
            return 1;
        }
        else{
            return 1 + (downforcePart.getDownforce() / 200d);
        }
    }

    /**
     * V_max_потенциал
     * @return
     */
    public double getMaxPotentialSpeed(){
        return getBaseSpeed() * getAeroCoef() * getDownFCoef();
    }

    /**
     * K_передачи
     * @return
     */
    public double getTransmissionCoef(){
        return 0.8d + 0.4d * (transmission.getGears() / 10d);
    }

    /**
     * K_управление_база
     * @return
     */
    public double getWheelCoef(){
        return wheels.getAdhesion() / 100d;
    }

    /**
     * К_стабильность
     * @return
     */
    public double getStabilityCoef(){
        if(suspension != null){
            return 1 + (suspension.getStability() / 200d);
        }
        else {
            return 1;
        }
    }

    /**
     * K_управление
     */
    public double getSummaryMovementCoef(){
        return getWheelCoef() * getStabilityCoef() * getDownFCoef();
    }

    public double getAveragePartQuality(){
        double sum = chassis.getQuality() + engine.getQuality()
                + transmission.getQuality() + wheels.getQuality();
        int num = 4;
        if(suspension != null){
            sum += suspension.getQuality();
            num++;
        }
        if(downforcePart != null){
            sum += downforcePart.getQuality();
            num++;
        }
        return sum/num;
    }

    public double getAveragePartConnectionReliability(){
        double sum = chassis.getConnectionReliability() + engine.getConnectionReliability()
                + transmission.getConnectionReliability() + wheels.getConnectionReliability();
        int num = 4;
        if(suspension != null){
            sum += suspension.getConnectionReliability();
            num++;
        }
        if(downforcePart != null){
            sum += downforcePart.getConnectionReliability();
            num++;
        }
        return sum/num;
    }

    /**
     * К_качество
     * @return
     */
    public double getQualityCoef(){
        return 2 - ((getAveragePartQuality() + getAveragePartConnectionReliability()) / 200d);
    }
}
