package data.race.map;

import data.special.PilotCoefMng;
import data.special.RandomDoubleGenerator;
import data.crew.Pilot;
import data.vehicle.Part;
import data.vehicle.Racecar;

import java.util.ArrayList;

public class TimeCalculator {

    ArrayList<Part> parts;
    Racecar racecar;
    Pilot pilot;

    public TimeCalculator(Racecar racecar, Pilot pilot){
        this.racecar = racecar;
        this.pilot = pilot;
        parts = new ArrayList<>(6);
        parts.add(racecar.getChassis());
        parts.add(racecar.getEngine());
        parts.add(racecar.getTransmission());
        parts.add(racecar.getWheels());

        if(racecar.getSuspension() != null){
            parts.add(racecar.getSuspension());
        }

        if(racecar.getDownforcePart() != null){
            parts.add(racecar.getDownforcePart());
        }
    }

    public double calculate(MapTerrain terrain){
        double time_ideal = (terrain.getLength() / terrain.getAverageSpeed(racecar, pilot))
                * getQualityCoef();
        double time_withpilot = time_ideal //T_пилот
                * PilotCoefMng.getBaseCoef(pilot)
                * PilotCoefMng.getOffroadCoef(pilot, terrain.getSurface());
        //Т_финал
        return time_withpilot * damageCoef() * RandomDoubleGenerator.generate(0.95,1.05);
    }

    private double getAveragePartQuality(){
        double sum = 0;
        for(Part part : parts){
            sum += part.getQuality();
        }
        return sum/parts.size();
    }

    private double getAveragePartConnectionReliability(){
        double sum = 0;
        for(Part part : parts){
            sum += part.getConnectionReliability();
        }
        return sum/parts.size();
    }

    /**
     * К_качество
     * @return
     */
    private double getQualityCoef(){
        return 2 - ((getAveragePartQuality() + getAveragePartConnectionReliability()) / 200d);
    }

    /**
     * K_повреждение
     * @return
     */
    private double damageCoef(){
        double totalDamage = racecar.getChassis().getDamage()
                + racecar.getEngine().getDamage()
                + racecar.getTransmission().getDamage()
                + racecar.getWheels().getDamage();
        return 1 + totalDamage/800;
    }
}
