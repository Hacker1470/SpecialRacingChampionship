package data.race.map.terrains;

import data.special.RacecarCoefMng;
import data.crew.Pilot;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;
import data.vehicle.Racecar;

public class StraightRoad extends MapTerrain {
    private final int length;

    public StraightRoad(int length, SurfaceType surface) {
        super(TerrainType.STRAIGHT, surface);
        this.length = length;
    }

    @Override
    public double getLength(){
        return length;
    }

    /**
     * V_прямая = V_max_потенциал × K_передачи × K_погода × K_поверхность
     * @return
     */
    @Override
    public double getAverageSpeed(Racecar racecar, Pilot pilot, WeatherType weather){
        double mp = racecar.getMaxPotentialSpeed();
        double tc = RacecarCoefMng.getTransmissionCoef(racecar);
        double wc = weather.getCoefficient();
        double sc = surface.getCoefficient();
        return racecar.getMaxPotentialSpeed() * RacecarCoefMng.getTransmissionCoef(racecar)
                * weather.getCoefficient() * surface.getCoefficient();
    }

    public String getCharacteristics(){
        StringBuilder sb = new StringBuilder();
        sb.append("Прямой участок ").append(length).append(" м\n");
        sb.append("\tПокрытие: ").append(surface.getName()).append("\n");
        return sb.toString();
    }
}
