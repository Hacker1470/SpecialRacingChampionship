package data.race.map;

import data.crew.Pilot;
import data.race.map.enums.Surface;
import data.race.map.enums.Weather;
import data.vehicle.Racecar;

public abstract class MapTerrain {
    private Surface surface;

    public MapTerrain(int steering, int driving, Surface surface){
        this.surface = surface;
    }

    public Surface getSurface(){
        return surface;
    }

    public abstract double getTime(Pilot pilot, Racecar racecar, Weather weather);


}
