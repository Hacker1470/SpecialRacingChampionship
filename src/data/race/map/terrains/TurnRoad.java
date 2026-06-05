package data.race.map.terrains;

import data.crew.Pilot;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;
import data.special.RacecarCoefMng;
import data.racecar.Racecar;

public class TurnRoad extends MapTerrain {
    private final int degree;
    private final int radius;

    public TurnRoad(int radius, int degree, SurfaceType surface) {
        super(TerrainType.TURN, surface);
        this.degree = degree;
        this.radius = radius;
    }

    /**
     * K_градус
     *
     * @return
     */
    private double getDegreeCoef() {
        return 1 - (degree / 180d);
    }

    /**
     * V_участка_поворот
     *
     * @return
     */
    @Override
    public double getAverageSpeed(Racecar racecar, Pilot pilot, WeatherType weather) {
        return racecar.getMaxPotentialSpeed() * RacecarCoefMng.getSummaryMovementCoef(racecar)
                * getDegreeCoef() * weather.getCoefficient() * surface.getCoefficient();
    }

    /**
     * L_дуги
     *
     * @return
     */
    private double getCurveLength() {
        return (3.1415d * radius * degree) / 180;
    }

    /**
     * L_поворота_эффективная
     *
     * @return
     */
    @Override
    public double getLength() {
        return getCurveLength() * (1 + degree / 180d);
    }

    @Override
    public String getCharacteristics() {
        String sb = "Поворот на " + degree + " градусов\n" +
                "\tРадиус поворота: " + radius + " м\n" +
                "\tПокрытие: " + surface.getName() + "\n";
        return sb;
    }
}
