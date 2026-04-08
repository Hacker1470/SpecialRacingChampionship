package data.race.calculators;

import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.special.RandomGenerator;
import data.crew.Pilot;
import data.vehicle.*;
import data.vehicle.enums.PartType;

import java.util.ArrayList;

public class DamageCalculator {
    ArrayList<Part> parts;
    Racecar racecar;
    Pilot pilot;

    public DamageCalculator(Racecar racecar, Pilot pilot){
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

    //Δ = Δ_база × (1 + (100 - Качество_детали) / 100) × (1 + (100 - Качество_сборки_детали) / 100) × R(0.8, 1.2)
    public void addDamage(RaceTrack rt, MapTerrain terrain) throws PartBrokeException{
        double damage;
        PartBrokeException e = null;
        for(Part part : parts){
            damage = part.getBaseDamage(getCoefficient(part, terrain), rt, terrain, racecar, pilot)
                    * getPartQualityCoefficient(part)
                    * getPartConnectionCoefficient(part)
                    * RandomGenerator.getDouble(0.8d, 1.2d);
            try{
                part.setDamage(part.getDamage() + damage);
            }
            catch (PartBrokeException exc){
                e = exc;
            }
        }
        if(e != null){
            throw e;
        }
    }

    private double getCoefficient(Part part, MapTerrain terrain){
        return switch (part.getType()){
            case PartType.CHASSIS -> terrain.getType().getChassisCoef();
            case PartType.ENGINE -> terrain.getType().getEngineCoef();
            case PartType.TRANSMISSION -> terrain.getType().getTransmissionCoef();
            case PartType.WHEELS -> terrain.getType().getWheelsCoef();
            case PartType.SUSPENSION -> terrain.getType().getSuspensionCoef();
            case PartType.DOWNFORCE -> terrain.getType().getDownforceCoef();
            default -> 1;
        };
    }

    private double getPartQualityCoefficient(Part part){
        return 1 + (100 - part.getQuality()) / 100d;
    }
    private double getPartConnectionCoefficient(Part part){
        return 1 + (100 - part.getConnectionReliability()) / 100d;
    }
}
