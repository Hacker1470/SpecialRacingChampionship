package data.special;

import data.crew.Pilot;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.racecar.Racecar;

public class RacecarCoefMng {
    /**
     * К_прижим = К_прижим_поворот
     *
     * @return
     */
    public static double getDownforceCoef(Racecar racecar) {
        if (racecar.getDownforcePart() == null) {
            return 1;
        } else {
            return 1 + (racecar.getDownforcePart().getDownforce() / 200d);
        }
    }

    /**
     * K_аэро = 1 + (Шасси.аэродинамика / 200)
     *
     * @return
     */
    public static double getAeroCoef(Racecar racecar) {
        return 1 + (racecar.getChassis().getAerodynamics() / 200d);
    }

    /**
     * K_передачи = 0.8 + 0.4 × (Коробка.передачи / 10)
     *
     * @return
     */
    public static double getTransmissionCoef(Racecar racecar) {
        return 0.8d + 0.4d * (racecar.getTransmission().getGears() / 10d);
    }

    /**
     * K_управление_база
     *
     * @return
     */
    public static double getWheelCoef(Racecar racecar) {
        return racecar.getWheels().getAdhesion() / 100d;
    }

    /**
     * К_стабильность
     *
     * @return
     */
    public static double getStabilityCoef(Racecar racecar) {
        if (racecar.getSuspension() != null) {
            return 1 + (racecar.getSuspension().getStability() / 200d);
        } else {
            return 1;
        }
    }

    /**
     * K_управление
     */
    public static double getSummaryMovementCoef(Racecar racecar) {
        return getWheelCoef(racecar) * getStabilityCoef(racecar) * getDownforceCoef(racecar);
    }

    /**
     * К_нагрузка
     *
     * @return
     */
    public static double getLoadCoef(Racecar racecar, Pilot pilot, RaceTrack rt, MapTerrain terrain) {
        return Math.min(100 * terrain.getAverageSpeed(racecar, pilot, rt.getWeather())
                        / racecar.getMaxPotentialSpeed(),
                terrain.getAverageSpeed(racecar, pilot, rt.getWeather()) / 200);
    }
}
