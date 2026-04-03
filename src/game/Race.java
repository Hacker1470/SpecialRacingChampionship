package game;

import data.race.Team;
import data.race.map.RaceTrack;

import java.util.ArrayList;
import java.util.Collection;

public class Race {
    private ArrayList<Team> teams;
    private final RaceTrack track;
    private int enterCost;
    private int prize;

    public Race(Collection<Team> teams, RaceTrack track, int enterCost){
        this.teams = new ArrayList<>();
        this.teams.addAll(teams);
        this.track = track;
        this.enterCost = enterCost;
    }
}
