package data.vehicle;

import data.crew.Pilot;
import data.race.map.MapTerrain;

public interface IDamageble {
    public double getBaseDamage(double coefficient, MapTerrain terrain,
                                Racecar racecar, Pilot pilot);
}
