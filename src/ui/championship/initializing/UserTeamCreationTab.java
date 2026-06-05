package ui.championship.initializing;

import data.crew.Pilot;
import data.race.Race;
import data.race.teams.Team;
import data.race.teams.TeamSample;
import data.racecar.*;
import game.GameModeNorris;
import game.GameSession;
import ui.base.Tab;
import ui.championship.ChampionshipTab;
import ui.championship.RaceProcessTab;

public class UserTeamCreationTab extends Tab {
    private TeamSample team;
    private Race race;
    private String warn = null;

    public UserTeamCreationTab(GameSession gm, Race race) {
        this(gm, new TeamSample(), race, null);
    }

    public UserTeamCreationTab(GameSession gm, TeamSample team, Race race) {
        this(gm, team, race, null);
    }

    public UserTeamCreationTab(GameSession gm, TeamSample team, Race race, String warn) {
        super(gm);
        this.team = team;
        this.race = race;
        this.warn = warn;
        if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_ACTIVE) {
            team.setPilot((Pilot) gm.dorm().getEmployeeById(Long.MAX_VALUE));
        }
    }

    @Override
    public Tab show() {
        if (warn == null) {
            outputMain();
        } else {
            outputWithWarn(warn);
        }
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("Соберите команду");
        gm.io().printlnString("[1] Болид\t\t\t" + getLine(team.getCar()));
        gm.io().printlnString("[2] Пилот\t\t\t" + getLine(team.getPilot()));
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[1] Выбрать болид");
        gm.io().printlnString("[2] Выбрать пилота");
        gm.io().printlnString("[+] Начать гонку");
        gm.io().printlnString("[0] Вернуться к списку чемпионатов");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private String getLine(Racecar car) {
        if (car != null) {
            return "(выбрано: " + car.getName() + ")";
        } else {
            return "(не выбрано)";
        }
    }

    private String getLine(Pilot pilot) {
        if (pilot != null) {
            return "(выбрано: " + pilot.getName() + " " + pilot.getPostfix() + ")";
        } else {
            return "(не выбрано)";
        }
    }

    private Tab menuHandler() {
        String request;
        Tab response = null;

        while (response == null) {
            request = gm.io().getString();

            response = selectResponse(request);
            if (response == null) {
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req) {
        if (gm.getGameModeNorris() == GameModeNorris.CHUCK_NORRIS_ACTIVE && req.equals("2")) {
            return new UserTeamCreationTab(gm, team, race, "У вас уже есть пилот - Чак Норрис");
        }
        return switch (req) {
            case "1" -> new RacecarSelectTab(gm, team, race);
            case "2" -> new PilotSelectTab(gm, team, race);
            case "+" -> prepareToRace();
            case "0" -> new ChampionshipTab(gm);
            default -> null;
        };
    }

    private Tab prepareToRace() {
        if (team.getCar() == null) {
            return new UserTeamCreationTab(gm, team, race,
                    "Болид не выбран");
        }
        if (team.getPilot() == null) {
            return new UserTeamCreationTab(gm, team, race,
                    "Пилот не выбран");
        }
        if (team.getCar().hasCriticalDamage()) {
            return new UserTeamCreationTab(gm, team, race,
                    "Выбранная машина содержит вышедшие из строя детали\n"
                            + "Выберите другое авто или соберите новое");
        }
        if (team.getCar().hasLotOfDamage()) {
            return new RaceWarnTab(gm, team, race,
                    "Некоторые детали автомобиля имеют повышенный урон\n"
                            + "Есть риск отказа болида во время гонки");
        }
        race.putTeam(new Team("Игрок", team.getCar(), team.getPilot()));
        team = null;
        return new RaceProcessTab(gm, race);
    }
}