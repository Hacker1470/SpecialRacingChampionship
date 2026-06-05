package data.race;

import data.crew.Pilot;
import data.race.map.enums.WeatherType;
import data.racecar.Racecar;

public interface ITimable {
    public double getAverageSpeed(Racecar racecar, Pilot pilot, WeatherType weather);

    public double getLength();
}
