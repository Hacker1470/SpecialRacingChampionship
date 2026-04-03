package data.special;

import data.crew.Pilot;
import data.race.map.enums.SurfaceType;

public class PilotCoefMng {
    /**
     * K_агрессия
     * @return
     */
    public static double getAggressionCoef(Pilot pilot){
        return 1 + (100 - pilot.getSteering() + 100 - pilot.getPedaling()) / 200d;
    }

    /**
     * K_пилот_база
     * @return
     */
    public static double getBaseCoef(Pilot pilot){
        return 1.5d - (pilot.getExperience() + pilot.getSteering() + pilot.getPedaling()) / 300d;
    }

    /**
     * К_бездорожье
     * @param s
     * @return
     */
    public static double getOffroadCoef(Pilot pilot, SurfaceType s){
        if(s.getCoefficient() < 50){
            return 1 + (100 - pilot.getOffroadDriving()) / 200d;
        }
        else {
            return 1;
        }
    }
}
