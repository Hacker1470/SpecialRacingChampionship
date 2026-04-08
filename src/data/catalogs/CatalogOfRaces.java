package data.catalogs;

import data.race.Race;
import data.race.map.RaceTrack;
import data.race.map.terrains.StraightRoad;
import data.race.map.terrains.TurnRoad;
import data.race.map.enums.SurfaceType;
import data.race.map.enums.WeatherType;

import java.util.ArrayList;
import java.util.List;

public class CatalogOfRaces {

    public static ArrayList<Race> allCatalog = new ArrayList<>();

    public static void catalogInit(){
        initialize();
    }

    private static void initialize(){

        allCatalog.add(new Race(
                new RaceTrack("Учебный полигон", List.of(
                    new StraightRoad(80, SurfaceType.ASPHALT),
                    new TurnRoad(40, 60, SurfaceType.ASPHALT),
                    new StraightRoad(80, SurfaceType.GRAVEL),
                    new TurnRoad(40, 60, SurfaceType.GRAVEL),
                    new StraightRoad(80, SurfaceType.FIELD),
                    new TurnRoad(40, 60, SurfaceType.FIELD)
                )
            ),
                0,
                0,
                1
            )
        );
        allCatalog.add(new Race(
            new RaceTrack("Жигулёвские горы", List.of(
                    new StraightRoad(20, SurfaceType.ASPHALT),
                    new TurnRoad(10, 60, SurfaceType.GRAVEL),
                    new StraightRoad(80, SurfaceType.ASPHALT),
                    new TurnRoad(30, 90, SurfaceType.ASPHALT),
                    new StraightRoad(50, SurfaceType.ASPHALT),
                    new TurnRoad(10, 60, SurfaceType.FIELD),
                    new StraightRoad(80, SurfaceType.GRAVEL),
                    new TurnRoad(30, 90, SurfaceType.ASPHALT),
                    new StraightRoad(100, SurfaceType.ASPHALT)
            )
            ),
            50,
            200,
            16
            )
        );
        allCatalog.add(new Race(
            new RaceTrack("Волжская набережная", List.of(
                    new StraightRoad(40, SurfaceType.ASPHALT),
                    new StraightRoad(20, SurfaceType.GRAVEL),
                    new TurnRoad(40, 30, SurfaceType.FIELD),
                    new StraightRoad(100, SurfaceType.FIELD),
                    new TurnRoad(30, 90, SurfaceType.GRAVEL),
                    new StraightRoad(50, SurfaceType.ASPHALT)
            )
            ),
            70,
            300,
            10
            )
        );
    }
}
