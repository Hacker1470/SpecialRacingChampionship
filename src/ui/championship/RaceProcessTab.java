package ui.championship;

import data.crew.Pilot;
import data.race.ArchiveRecord;
import data.race.BotGenerator;
import data.race.Race;
import data.race.map.enums.WeatherType;
import data.race.map.terrains.MapTerrain;
import data.race.Team;
import data.special.RandomGenerator;
import data.vehicle.Part;
import data.vehicle.PartBrokeException;
import data.vehicle.Racecar;
import game.GameMode;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RaceProcessTab extends Tab {
    private Race race;
    private Team userTeam;
    private int moneyBefore;

    public RaceProcessTab(GameSession gm, Race race){
        super(gm);
        this.race = race;
        userTeam = race.getCopyOfTeams().getFirst();
        moneyBefore = gm.getMoney();
    }

    private void prepareRace(){
        if(race.getTeamsNumber() > 1){
            BotGenerator.activate(gm, race);
        }
    }

    @Override
    public Tab show() {
        prepareRace();
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        ConsoleControl.printlnString("ГОНКА НАЧИНАЕТСЯ");
        printTeamsList();
        ConsoleControl.printlnString("3..2..1.. СТАРТ!!!");
        try{
            racing();
            defaultEnding();
        }
        catch (PartBrokeException e){
            badEnding(e.getMessage());
        }
        ConsoleControl.printlnString("===============================");
        ConsoleControl.printlnString("Введите [0], чтобы вернуться в главное меню");
    }

    private void printTeamsList(){
        ConsoleControl.printlnString("Команды:");
        for(Team s : race.getCopyOfTeams()){
            ConsoleControl.printlnString("* " + s.getName());
            String str = "";
            for(Part p : s.getCar().getPartsList()){
                str = str + p.getDamage() + " - ";
            }
            ConsoleControl.printlnString(str);
        }
    }

    private void racing() throws PartBrokeException{
        MapTerrain currentZone;
        for(int i = 0; i < race.getMap().getNumberOfTerrains(); i++){
            currentZone = race.getMap().getTerrainByNumber(i);
            ConsoleControl.printlnString("Локация " + currentZone.getName());
            ConsoleControl.printlnString("Погода: " + race.getMap().getWeather().getName());
            tryChangeWeather();
            for(Team team : race.getCopyOfTeams()){
                try{
                    team.goThroughZone(race.getMap(), currentZone);
                }
                catch (PartBrokeException e){
                    if(!team.getName().equals("Игрок")){
                        race.disqualifyTeam(team);
                        ConsoleControl.printlnString("Команда " + team.getName() + " выбыла из гонки. " + e.getMessage());
                    }
                    else {
                        throw e;
                    }
                }
            }
            printCurrentRating();
        }
    }

    private void tryChangeWeather(){
        int weather = RandomGenerator.getInteger(1,100);
        switch (race.getMap().getWeather()){
            case SUNNY:
            {
                if(weather < 10){
                    race.getMap().setWeather(WeatherType.CLOUDY);
                }
                else if (weather < 15){
                    race.getMap().setWeather(WeatherType.RAINING);
                }
                break;
            }
            case CLOUDY:
            {
                if(weather < 30){
                    race.getMap().setWeather(WeatherType.SUNNY);
                }
                else if (weather < 45){
                    race.getMap().setWeather(WeatherType.RAINING);
                }
                break;
            }
            case RAINING:
            {
                if(weather < 50){
                    race.getMap().setWeather(WeatherType.CLOUDY);
                }
                else if (weather < 55){
                    race.getMap().setWeather(WeatherType.SUNNY);
                }
                break;
            }
        }
    }

    private void printCurrentRating(){
        ArrayList<Team> teams = new ArrayList<>(race.getCopyOfTeams().stream().sorted(Comparator.comparingDouble(Team::getTotalTime)).toList());
        for(int i = 1; i <= teams.size(); i++){
            ConsoleControl.printlnString(i + ". " + teams.get(i - 1).getName() + " : " + String.format("%." + 2 + "f", teams.get(i - 1).getTotalTime()));
        }
    }

    private void badEnding(String s){
        ConsoleControl.printlnString("================================");
        ConsoleControl.printlnString("ТЕХНИЧЕСКОЕ ПОРАЖЕНИЕ");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("Во время гонки у болида " + s);
        gm.changeRep(-1);
        updateArchive();
    }

    private void defaultEnding(){
        ConsoleControl.printlnString("================================");
        ConsoleControl.printlnString("ГОНКА ОКОНЧЕНА");
        ConsoleControl.printlnString("");
        printCurrentRating();

        if(gm.getGameMode() == GameMode.NORMAL){
            givePrizesToWinners();
            givePilotSalaryAndExp();
        }
        if(gm.getGameMode() == GameMode.CHUCK_NORRIS_ACTIVE){
            redistributeAvard();
            gm.setGameMode(GameMode.NORMAL);
            gm.dorm().remove(Long.MAX_VALUE);
        }
        calculateRep();
        updateArchive();
    }

    public void givePrizesToWinners(){
        ConsoleControl.printlnString("Награды");
        ArrayList<Team> teams = new ArrayList<>(race.getCopyOfTeams().stream()
                .sorted(Comparator.comparingDouble(Team::getTotalTime))
                .toList());

        double prizeCoef;
        for(int i = 1; i <= Math.min(3, teams.size()); i++){
            prizeCoef = switch (i){
                case 1 -> 0.55d;
                case 2 -> 0.30d;
                case 3 -> 0.15d;
                default -> 0;
            };
            ConsoleControl.printlnString(teams.get(i - 1).getName() + " : " + race.getPrize() * prizeCoef + " грошей");
            if(teams.get(i - 1).getName().equals("Игрок")){
                gm.addMoney((int)(race.getPrize() * prizeCoef));
            }
        }
    }

    private void givePilotSalaryAndExp(){
        Team playersTeam = race.getCopyOfTeams().getFirst();
        Pilot playersPilot = race.getCopyOfTeams().getFirst().getPilot();
        int salary = playersPilot.getSalary((int)playersTeam.getTotalTime()/2);
        if(!gm.takeMoney(salary)){
            gm.takeMoney(gm.getMoney());
            gm.dorm().remove(playersPilot);
            ConsoleControl.printlnString("=====================================================");
            ConsoleControl.printlnString("ПИЛОТ" + playersPilot.getName() + " " + playersPilot.getPostfix() +
                    "УВОЛИЛСЯ, так как ему не хватило денег на ЗПку");
            ConsoleControl.printlnString("=====================================================");
        }
        else {
            playersPilot.setExperience(playersPilot.getExperience() + 1);
            ConsoleControl.printlnString("=====================================================");
            ConsoleControl.printlnString("ЗП пилота: " + salary);
            ConsoleControl.printlnString("=====================================================");
        }
    }

    private void redistributeAvard(){
        ConsoleControl.printlnString("===================================================================");
        ConsoleControl.printlnString("Чак Норрис вышел \"поговорить\" с организатором гонок тет-а-тет");
        ConsoleControl.printlnString("В этой гонке награды распределятся по усмотрению Норриса");
        ConsoleControl.printlnString("Он запросил информацию из банка о финансовом положении команд");
        HashMap<Team, Integer> teamsAndCash = getBankInfo();
        printCurrentRatingWithMoney(teamsAndCash);
        ConsoleControl.printlnString("Теперь выигрыш будет распределён между 3 беднейшими командами");
        ConsoleControl.printlnString("Награды");

        ArrayList<Team> awardedTeams = new ArrayList<>(teamsAndCash.keySet().stream()
                .sorted(Comparator.comparingInt(teamsAndCash::get))
                .toList());

        for(int i = 1; i <= Math.min(3, awardedTeams.size()); i++) {
            ConsoleControl.printlnString(awardedTeams.get(i - 1).getName() + " : " + race.getPrize()/awardedTeams.size() + " грошей");
            if (awardedTeams.get(i - 1).getName().equals("Игрок")) {
                gm.addMoney((int) (race.getPrize()/awardedTeams.size()));
            }
        }
    }

    private HashMap<Team, Integer> getBankInfo(){
        HashMap<Team, Integer> result = new HashMap<>();
        for(Team t : race.getCopyOfTeams()){
            if(t.getName().equals("Игрок")){
                result.put(t, gm.getMoney());
            }
            else {
                result.put(t, RandomGenerator.getInteger(100, gm.getMoney() * 10));
            }
        }
        return result;
    }

    private void printCurrentRatingWithMoney(HashMap<Team, Integer> teams){
        ArrayList<Team> out = new ArrayList<>(race.getCopyOfTeams().stream().sorted(Comparator.comparingDouble(Team::getTotalTime)).toList());
        for(int i = 1; i <= teams.size(); i++){
            ConsoleControl.printlnString(i + ". " + out.get(i - 1).getName() + " : " + String.format("%." + 2 + "f", out.get(i - 1).getTotalTime())
            + " (Баланс: " + teams.get(out.get(i - 1)) + " грошей)");
        }
    }

    private void calculateRep(){
        ArrayList<Team> teams = new ArrayList<>(race.getCopyOfTeams().stream()
                .sorted(Comparator.comparingDouble(Team::getTotalTime))
                .toList());
        int place = 1 + teams.indexOf(race.getCopyOfTeams().getFirst());
        int result = 0;
        int allPlaces = race.getTeamsNumber();

        double relPos = place;
        if(allPlaces/2 - place < 0){
            relPos = Math.abs(place - 10) + 1;
        }

        if(relPos <= allPlaces/10d){
            result = 4;
        }
        else if(relPos <= 2 * allPlaces/10d){
            result = 3;
        }
        else if(relPos <= 3 * allPlaces/10d){
            result = 2;
        }
        else if(relPos <= 4 * allPlaces/10d){
            result = 1;
        }

        if (allPlaces/2 - place < 0){
            result *= -1;
        }

        gm.changeRep(result);
    }

    private void updateArchive(){
        String place;
        ArrayList<Team> teams = new ArrayList<>(race.getCopyOfTeams().stream()
                .sorted(Comparator.comparingDouble(Team::getTotalTime))
                .toList());
        if(race.getCopyOfTeams().getFirst().getCar().hasCriticalDamage()){
            place = 0 + "/" + race.getTeamsNumber();
        }
        else{
            place = (1 + teams.indexOf(race.getCopyOfTeams().getFirst())) + "/" + race.getTeamsNumber();
        }

        gm.addRecordToArchive(new ArchiveRecord(
                race.getMap().getName(),
                userTeam.getCar().getName(),
                userTeam.getPilot().getName() + " " + userTeam.getPilot().getPostfix(),
                place,
                gm.getMoney() - moneyBefore));
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            response = selectResponse(request);
            if (response == null){
                ConsoleControl.printlnString("Неверное меню");
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        return switch (req) {
            case "0" -> new MainTab(gm);
            default -> null;
        };
    }
}
