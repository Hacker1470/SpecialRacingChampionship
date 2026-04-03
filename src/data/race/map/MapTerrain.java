package data.race.map;

import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;
import ui.handling.ConsoleControl;

public abstract class MapTerrain implements ITimable{
    protected TerrainType type;
    protected SurfaceType surface;
    protected WeatherType weather;

    public MapTerrain(TerrainType terrainType, SurfaceType surface, WeatherType weather){
        type = terrainType;
        this.surface = surface;
        this.weather = weather;
    }

    public SurfaceType getSurface(){
        return surface;
    }
    public WeatherType getWeather(){
        return weather;
    }
    public TerrainType getType(){
        return type;
    }

    public abstract String getCharacteristics();

    public void setSurface(SurfaceType newType){
        surface = newType;
    }
    public void setWeather(WeatherType newType){
        weather = newType;
    }
}
