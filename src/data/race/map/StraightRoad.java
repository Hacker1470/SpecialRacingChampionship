package data.race.map;

import data.special.RacecarCoefMng;
import data.crew.Pilot;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;
import data.vehicle.Racecar;
import ui.handling.ConsoleControl;

public class StraightRoad extends MapTerrain{
    private final int length;

    public StraightRoad(int length, SurfaceType surface, WeatherType weather) {
        super(TerrainType.STRAIGHT, surface, weather);
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
    public double getAverageSpeed(Racecar racecar, Pilot pilot){
        return racecar.getMaxPotentialSpeed() * RacecarCoefMng.getTransmissionCoef(racecar)
                * weather.getCoefficient() * surface.getCoefficient();
    }

    public String getCharacteristics(){
        StringBuilder sb = new StringBuilder();
        sb.append("Прямой участок ").append(length).append(" м\n");
        sb.append("\tПокрытие: ").append(surface.getName()).append("\n");
        sb.append("\tПогода на участке: ").append(weather.getName());
        return sb.toString();
    }
}
