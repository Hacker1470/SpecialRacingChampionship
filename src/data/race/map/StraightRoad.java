package data.race.map;

import data.crew.Pilot;
import data.race.map.enums.Surface;
import data.race.map.enums.Weather;
import data.vehicle.Racecar;

public class StraightRoad extends MapTerrain{
    private final int length;
    public StraightRoad(int length, int steering, int driving, Surface surface) {
        super(steering, driving, surface);
        this.length = length;
    }

    @Override
    public double getTime(Pilot pilot, Racecar racecar, Weather weather) {
        return (
                    (
                            pilot.getOffroadDriving()
                            + weather.getKoef()
                            + getSurface().getKoef()
                    )/100f
                * pilot.getPedaling()/100f
                * (1 + (Math.random() - 0.5)/0.5)
                * length/(1f*racecar.getEngine().getMaxRpm()
                / racecar.getTransmission().getGears())
            ) % racecar.getTransmission().getMaxSpeed();
    }

    /**
     * Т_идеал
     * @return
     */
    public double getPerfectTime(Racecar rc, Weather wt, Surface sf){
        return (length / getSpeed(rc, wt, sf)) * rc.getQualityCoef();
    }

    /**
     * Т_пилот
     */
    public

    /**
     * V_участка_прямая
     * @param rc
     * @param wt
     * @param sf
     * @return
     */
    private double getSpeed(Racecar rc, Weather wt, Surface sf){
        return rc.getMaxPotentialSpeed() * rc.getTransmissionCoef()
                * wt.getKoef() * sf.getKoef();
    }
}
