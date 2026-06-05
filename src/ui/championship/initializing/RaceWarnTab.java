package ui.championship.initializing;

import data.race.Race;
import data.race.teams.Team;
import data.race.teams.TeamSample;
import game.GameSession;
import ui.base.MainTab;
import ui.base.Tab;
import ui.championship.RaceProcessTab;

public class RaceWarnTab extends Tab {
    private Race race;
    private String warn;
    private TeamSample team;

    public RaceWarnTab(GameSession gm, TeamSample team, Race race, String warn) {
        super(gm);
        this.race = race;
        this.warn = warn;
        this.team = team;
    }

    @Override
    public Tab show() {
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString("Внимание");
        gm.io().printlnString(warn);
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[1] Продолжить");
        gm.io().printlnString("[0] Собрать другую команду");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
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
        switch (req) {
            case "1": {
                race.putTeam(new Team("Игрок", team.getCar(), team.getPilot()));
                return new RaceProcessTab(gm, race);
            }
            case "0": {
                return new UserTeamCreationTab(gm, team, race);
            }
            default:
                return new MainTab(gm);
        }
    }
}
