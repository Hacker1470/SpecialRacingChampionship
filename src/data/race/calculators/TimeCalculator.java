package data.race.calculators;

import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.special.PilotCoefMng;
import data.special.RandomGenerator;
import data.crew.Pilot;
import data.parts.Part;
import data.racecar.Racecar;

import java.util.ArrayList;

public class TimeCalculator {

    ArrayList<Part> parts;
    Racecar racecar;
    Pilot pilot;

    public TimeCalculator(Racecar racecar, Pilot pilot) {
        this.racecar = racecar;
        this.pilot = pilot;
        parts = racecar.getNotNullParts();
    }

    public double calculate(RaceTrack map, MapTerrain terrain) {
        double l = terrain.getLength();
        double av = terrain.getAverageSpeed(racecar, pilot, map.getWeather());
        double q = getQualityCoef();
        double time_ideal = (terrain.getLength() / terrain.getAverageSpeed(racecar, pilot, map.getWeather()))
                * getQualityCoef();
        double time_withpilot = time_ideal //T_пилот
                * PilotCoefMng.getBaseCoef(pilot)
                * PilotCoefMng.getOffroadCoef(pilot, terrain.getSurface());
        //Т_финал
        return time_withpilot * damageCoef() * RandomGenerator.getDouble(0.95, 1.05);
    }

    private double getAveragePartQuality() {
        double sum = 0;
        for (Part part : parts) {
            sum += part.getQuality();
        }
        return sum / parts.size();
    }

    private double getAveragePartConnectionReliability() {
        double sum = 0;
        for (Part part : parts) {
            sum += part.getConnectionReliability();
        }
        return sum / parts.size();
    }

    /**
     * К_качество
     *
     * @return
     */
    private double getQualityCoef() {
        return 2 - ((getAveragePartQuality() + getAveragePartConnectionReliability()) / 200d);
    }

    /**
     * K_повреждение
     *
     * @return
     */
    private double damageCoef() {
        double totalDamage = racecar.getChassis().getDamage()
                + racecar.getEngine().getDamage()
                + racecar.getTransmission().getDamage()
                + racecar.getWheels().getDamage();
        return 1 + totalDamage / 800;
    }
}
