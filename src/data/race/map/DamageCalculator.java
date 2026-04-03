package data.race.map;

import data.special.RandomDoubleGenerator;
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
    public void addDamage(MapTerrain terrain) throws PartBrokeException{
        double damage;
        for(Part part : parts){
            damage = part.getBaseDamage(getCoefficient(part, terrain), terrain, racecar, pilot)
                    * getPartQualityCoefficient(part)
                    * getPartConnectionCoefficient(part)
                    * RandomDoubleGenerator.generate(0.8d, 1.2d);
            part.setDamage(part.getDamage() + damage);
        }
    }

    public double getCoefficient(Part part, MapTerrain terrain){
        return switch (part.getType()){
            case PartType.CHASSIS -> terrain.type.getChassisCoef();
            case PartType.ENGINE -> terrain.type.getEngineCoef();
            case PartType.TRANSMISSION -> terrain.type.getTransmissionCoef();
            case PartType.WHEELS -> terrain.type.getWheelsCoef();
            case PartType.SUSPENSION -> terrain.type.getSuspensionCoef();
            case PartType.DOWNFORCE -> terrain.type.getDownforceCoef();
            default -> 1;
        };
    }

    public double getPartQualityCoefficient(Part part){
        return 1 + (100 - part.getQuality()) / 100d;
    }
    public double getPartConnectionCoefficient(Part part){
        return 1 + (100 - part.getConnectionReliability()) / 100d;
    }
}
