package data.race;

import data.race.map.enums.WeatherType;

public class ArchiveRecord {
    private final String mapName;
    private final String place;
    private final String carName;
    private final String pilotName;
    private final int profit;

    public ArchiveRecord(String mapName, String carName, String pilotName,
                         String place, int profit){
        this.mapName = mapName;
        this.carName = carName;
        this.pilotName = pilotName;
        this.place = place;
        this.profit = profit;
    }

    public String getMapName() {
        return mapName;
    }
    public String getPlace(){
        return place;
    }
    public String getCarName(){
        return carName;
    }
    public String getPilotName() {
        return pilotName;
    }
    public int getProfit() {
        return profit;
    }
}
