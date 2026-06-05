package data.race.teams;

import data.crew.Pilot;
import data.racecar.Racecar;

public abstract class AbstractTeam {
    protected Racecar car;
    protected Pilot pilot;

    public Racecar getCar() {
        return car;
    }
    public Pilot getPilot() {
        return pilot;
    }
}
