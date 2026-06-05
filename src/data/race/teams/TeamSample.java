package data.race.teams;

import data.crew.Pilot;
import data.racecar.Racecar;

public class TeamSample extends AbstractTeam {

    public TeamSample() {
        this.car = null;
        this.pilot = null;
    }

    public TeamSample(Racecar car, Pilot pilot) {
        this.car = car;
        this.pilot = pilot;
    }

    public void setCar(Racecar newCar) {
        car = newCar;
    }

    public void setPilot(Pilot newPilot) {
        pilot = newPilot;
    }
}
