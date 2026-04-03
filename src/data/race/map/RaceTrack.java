package data.race.map;

import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class RaceTrack {
    private final ArrayList<MapTerrain> map;
    private final String name;

    public RaceTrack(String name, Collection<MapTerrain> terrains){
        map = new ArrayList<>();
        map.addAll(terrains);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setWeather(WeatherType weather){
        for(MapTerrain mt : map){
            mt.setWeather(weather);
        }
    }

    public void setSurface(SurfaceType surface){
        for(MapTerrain mt : map){
            mt.setSurface(surface);
        }
    }

    public int getNumberOfTerrains(){
        return map.size();
    }

    public MapTerrain getTerrainByNumber(int index){
        return map.get(index);
    }
}
