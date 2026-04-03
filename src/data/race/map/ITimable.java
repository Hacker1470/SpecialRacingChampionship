package data.race.map;

import data.crew.Pilot;
import data.vehicle.Racecar;

public interface ITimable {
    public double getAverageSpeed(Racecar racecar, Pilot pilot);
    public double getLength();
}
