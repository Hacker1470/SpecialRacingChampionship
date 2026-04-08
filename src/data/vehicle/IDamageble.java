package data.vehicle;

import data.crew.Pilot;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;

public interface IDamageble {
    public double getBaseDamage(double coefficient, RaceTrack rt, MapTerrain terrain,
                                Racecar racecar, Pilot pilot);
}
