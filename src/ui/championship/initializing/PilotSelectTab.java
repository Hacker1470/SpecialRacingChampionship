package ui.championship.initializing;

import data.crew.JobType;
import data.crew.Pilot;
import data.race.Race;
import data.race.teams.TeamSample;
import game.GameSession;
import ui.base.Tab;

import java.util.List;

public class PilotSelectTab extends Tab {
    private List<Pilot> availablePilots;
    private TeamSample team;
    private Race race;

    public PilotSelectTab(GameSession gm, TeamSample team, Race race) {
        super(gm);
        this.team = team;
        this.race = race;
    }

    @Override
    public Tab show() {
        availablePilots = gm.dorm().getEmployeesByJob(JobType.PILOT)
                .stream().map(x -> (Pilot) x).toList();
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(JobType.PILOT.getEmployGroupTitle());

        if (availablePilots.isEmpty()) {
            printListOfMenusNoPilots();
        } else {
            printListOfMenusMain();
        }

        gm.io().printlnString("[0] Вернуться");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printListOfMenusNoPilots() {
        gm.io().printlnString("В общежитии нет пилотов");
        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
    }

    private void printListOfMenusMain() {
        int counter = 1;
        for (Pilot pilot : availablePilots) {
            gm.io().printlnString("[" + counter++ + "] " + pilot.getName() + " " + pilot.getPostfix());
        }

        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Выбрать пилота под номером N");
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
        if (req.equals("0")) {
            return new UserTeamCreationTab(gm, team, race);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (index >= 1 && index <= availablePilots.size()) {
            team.setPilot(availablePilots.get(index - 1));
            return new UserTeamCreationTab(gm, team, race);
        } else {
            return null;
        }
    }
}
