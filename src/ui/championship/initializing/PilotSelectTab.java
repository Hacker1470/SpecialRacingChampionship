package ui.championship.initializing;

import data.crew.Employee;
import data.crew.JobType;
import data.crew.Pilot;
import data.race.Race;
import data.race.TeamSample;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

import java.util.ArrayList;
import java.util.List;

public class PilotSelectTab extends Tab{
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
                .stream().map(x -> (Pilot)x).toList();
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString(JobType.PILOT.getEmployGroupTitle());

        if(availablePilots.isEmpty()){
            printListOfMenusNoPilots();
        }
        else{
            printListOfMenusMain();
        }

        ConsoleControl.printlnString("[0] Вернуться");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printListOfMenusNoPilots(){
        ConsoleControl.printlnString("В общежитии нет пилотов");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
    }

    private void printListOfMenusMain(){
        int counter = 1;
        for(Pilot pilot : availablePilots){
            ConsoleControl.printlnString("[" + counter++ + "] " + pilot.getName() + " " + pilot.getPostfix());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать пилота под номером N");
    }

    private Tab menuHandler(){
        String request;
        Tab response = null;

        while (response == null){
            request = ConsoleControl.getString();

            response = selectResponse(request);
            if (response == null){
                outputWithWarn("Меню не имеет пункта: " + request);
            }
        }

        return response;
    }

    private Tab selectResponse(String req){
        if(req.equals("0")){
            return new UserTeamCreationTab(gm, team, race);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(index >= 1 && index <= availablePilots.size()){
            team.setPilot(availablePilots.get(index - 1));
            return new UserTeamCreationTab(gm, team, race);
        }
        else {
            return null;
        }
    }
}
