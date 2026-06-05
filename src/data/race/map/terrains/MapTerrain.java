package data.race.map.terrains;

import data.race.ITimable;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.TerrainType;
import data.race.map.enums.WeatherType;

public abstract class MapTerrain implements ITimable {
    protected TerrainType type;
    protected SurfaceType surface;

    public MapTerrain(TerrainType terrainType, SurfaceType surface) {
        type = terrainType;
        this.surface = surface;
    }

    public SurfaceType getSurface() {
        return surface;
    }

    public TerrainType getType() {
        return type;
    }

    public String getName() {
        return type.name();
    }

    public abstract String getCharacteristics();
}
