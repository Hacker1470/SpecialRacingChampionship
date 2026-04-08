package data.race.map;

import data.race.map.enums.SurfaceType;
import data.race.map.enums.WeatherType;
import data.race.map.terrains.MapTerrain;

import java.util.ArrayList;
import java.util.Collection;

public class RaceTrack {
    private final ArrayList<MapTerrain> map;
    private final String name;
    private WeatherType weather;

    public RaceTrack(String name, Collection<MapTerrain> terrains){
        map = new ArrayList<>();
        map.addAll(terrains);
        this.name = name;
        this.weather = WeatherType.SUNNY;
    }

    public String getName(){
        return name;
    }

    public WeatherType getWeather(){
        return weather;
    }
    public void setWeather(WeatherType weather){
        this.weather = weather;
    }

    public int getNumberOfTerrains(){
        return map.size();
    }

    public MapTerrain getTerrainByNumber(int index){
        return map.get(index);
    }
}
