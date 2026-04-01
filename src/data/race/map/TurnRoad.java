package data.race.map;

import data.crew.Pilot;
import data.race.map.enums.Surface;
import data.race.map.enums.Weather;
import data.vehicle.Racecar;

public class TurnRoad extends MapTerrain{
    private final int degree;
    private final int radius;
    public TurnRoad(int length, int radius, int steering, int driving, Surface surface, int degree) {
        super(steering, driving, surface);
        this.degree = degree;
        this.radius = radius;
    }

    /**
     * T_поворот
     * @param pilot
     * @param racecar
     * @param weather
     * @return
     */
    @Override
    public double getTime(Pilot pilot, Racecar racecar, Weather weather) {
        return getEffectiveCurve() / getSpeed(racecar, weather,getSurface());
    }

    /**
     * K_градус
     * @return
     */
    private double getDegreeCoef(){
        return 1 - (degree / 180d);
    }

    /**
     * V_участка_поворот
     * @param rc
     * @param wt
     * @param sf
     * @return
     */
    private double getSpeed(Racecar rc, Weather wt, Surface sf){
        return rc.getMaxPotentialSpeed() * rc.getSummaryMovementCoef()
                * getDegreeCoef() * wt.getKoef() * sf.getKoef();
    }

    /**
     * L_дуги
     * @return
     */
    private double getCurveLength(){
        return (3.1415d * radius * degree)/180;
    }

    /**
     * L_поворота_эффективная
     * @return
     */
    private double getEffectiveCurve(){
        return getCurveLength() * (1 + degree/180d);
    }
}
