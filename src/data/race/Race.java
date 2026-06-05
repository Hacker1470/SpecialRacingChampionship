package data.race;

import data.race.map.RaceTrack;
import data.race.teams.Team;

import java.util.ArrayList;

public class Race {
    private ArrayList<Team> teams;
    private final RaceTrack map;
    private final int deposit;
    private int prize;
    private final int teamsNumber;

    public Race(RaceTrack map, int deposit, int prize, int teamsNumber) {
        this.teams = new ArrayList<>();
        this.map = map;
        this.deposit = deposit;
        this.prize = prize;
        this.teamsNumber = teamsNumber;
    }

    public ArrayList<Team> getCopyOfTeams() {
        return new ArrayList<>(teams);
    }

    public ArrayList<String> getTeamsNames() {
        return new ArrayList<>(teams.stream().map(Team::getName).toList());
    }

    public int getDeposit() {
        return deposit;
    }

    public void disqualifyTeam(Team badTeam) {
        teams.remove(badTeam);
    }

    public int getPrize() {
        return (int) Math.round(deposit * teamsNumber * 0.9d + prize);
    }

    public RaceTrack getMap() {
        return map;
    }

    public int getRequiredTeamsNumber() {
        return teamsNumber;
    }

    /**
     * Лучше сделать реализацию через экзепшены.
     * Сделал костыль чисто чтобы оно работало
     *
     * @param newTeam
     * @return
     */
    public boolean putTeam(Team newTeam) {
        if (teams.contains(newTeam) || teams.size() > teamsNumber || newTeam == null) {
            return false;
        }
        teams.add(newTeam);
        return true;
    }
}
