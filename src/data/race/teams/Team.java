package data.race.teams;

import data.crew.Pilot;
import data.parts.PartBrokeException;
import data.race.calculators.DamageCalculator;
import data.race.calculators.TimeCalculator;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.racecar.Racecar;

public class Team extends AbstractTeam {
    private String name;

    private TimeCalculator tc;
    private DamageCalculator dc;
    private double totalTime = 0;

    public Team(String name, Racecar car, Pilot pilot) {
        this.name = name;
        this.car = car;
        this.pilot = pilot;
        tc = new TimeCalculator(car, pilot);
        dc = new DamageCalculator(car, pilot);
    }

    public String getName() {
        return name;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void goThroughZone(RaceTrack rt, MapTerrain zone) throws PartBrokeException {
        totalTime += tc.calculate(rt, zone);
        dc.addDamage(rt, zone);
    }
}
