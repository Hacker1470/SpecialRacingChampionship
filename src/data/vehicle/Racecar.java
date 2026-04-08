package data.vehicle;

import data.special.RacecarCoefMng;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Part> getPartsList(){
        ArrayList<Part> result = new ArrayList<>(
                List.of(chassis, engine, transmission, wheels));
        if(suspension != null) result.add(suspension);
        if(downforcePart != null) result.add(downforcePart);

        return result;
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
     * V_база = min(Коробка.макс_скорость, (Двигатель.мощность × Двигатель.обороты) / (масса × 100))
     */
    public double getBaseSpeed(){
        double speed = Math.min(transmission.getMaxSpeed(),
                (engine.getPower() * engine.getMaxRpm())/(getWeight() * 100d));
        if(speed < 8){
            return 8;
        }
        else {
            return speed;
        }
    }

    /**
     * V_max_потенциал = V_база × K_аэро × K_прижим
     * @return
     */
    public double getMaxPotentialSpeed(){
        double bs = getBaseSpeed();
        double ac = RacecarCoefMng.getAeroCoef(this);
        double dc = RacecarCoefMng.getDownforceCoef(this);
        return getBaseSpeed()
                * RacecarCoefMng.getAeroCoef(this)
                * RacecarCoefMng.getDownforceCoef(this);
    }

    /**
     * Одна из деталей имеет высокий урон
     * @return
     */
    public boolean hasLotOfDamage(){
        boolean ans = (engine.getDamage() > 50)
                || (transmission.getDamage() > 50)
                || (chassis.getDamage() > 50)
                || (wheels.getDamage() > 50);
        if(suspension != null){
            ans = ans || (suspension.getDamage() > 50);
        }
        if(downforcePart != null){
            ans = ans || (downforcePart.getDamage() > 50);
        }
        return ans;
    }
    public boolean hasCriticalDamage(){
        boolean ans = (engine.getDamage() == 100)
                || (transmission.getDamage() == 100)
                || (chassis.getDamage() == 100)
                || (wheels.getDamage() == 100);
        if(suspension != null){
            ans = ans || (suspension.getDamage() == 100);
        }
        if(downforcePart != null){
            ans = ans || (downforcePart.getDamage() == 100);
        }
        return ans;
    }
}
