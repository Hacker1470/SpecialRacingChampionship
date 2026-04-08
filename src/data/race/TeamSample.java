package data.race;

import data.crew.Pilot;
import data.vehicle.Racecar;

public class TeamSample {
    private String name;
    private Racecar car;
    private Pilot pilot;

    public TeamSample(){}

    public String getName() {
        return name;
    }
    public void setName(String newValue){
        name = newValue;
    }

    public Racecar getCar() {
        return car;
    }
    public void setCar(Racecar newCar){
        car = newCar;
    }

    public Pilot getPilot() {
        return pilot;
    }
    public void setPilot(Pilot newPilot){
        pilot = newPilot;
    }
}
