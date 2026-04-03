package data.catalogs;

import data.crew.Employee;
import data.crew.JobType;
import data.employeeslists.EngineerList;
import data.employeeslists.PilotList;
import data.race.map.RaceTrack;
import data.race.map.StraightRoad;
import data.race.map.TurnRoad;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.WeatherType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CatalogOfTracks {

    public static ArrayList<RaceTrack> allCatalog = new ArrayList<>();

    public static void catalogInit(){
        initialize();
    }

    private static void initialize(){

        allCatalog.add(new RaceTrack("Учебный полигон", List.of(
                new StraightRoad(500, SurfaceType.ASPHALT, WeatherType.SUNNY),
                new TurnRoad(80, 60, SurfaceType.ASPHALT, WeatherType.SUNNY),
                new StraightRoad(800, SurfaceType.ASPHALT, WeatherType.SUNNY),
                new TurnRoad(60, 120, SurfaceType.ASPHALT, WeatherType.SUNNY),
                new StraightRoad(700, SurfaceType.ASPHALT, WeatherType.SUNNY)
        )));
    }
}
