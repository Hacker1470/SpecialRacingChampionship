package ui.championship.initializing;

import data.race.Race;
import data.race.TeamSample;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

public class RacecarSelectTab extends Tab {
    private TeamSample team;
    private Race race;
    public RacecarSelectTab(GameSession gm, TeamSample team, Race race) {
        super(gm);
        this.team = team;
        this.race = race;
    }

    @Override
    public Tab show() {
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("ВАШ ГАРАЖ");

        if(gm.garage().getCarsNumber() > 0){
            printCarsCatalog();
        }
        else{
            printEmptyGarage();
        }

        ConsoleControl.printlnString("[0] Назад");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printCarsCatalog(){
        ConsoleControl.printlnString("В гараже стоят следующие авто");

        ConsoleControl.printlnString(gm.garage().generateStringCatalog());
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать болид N");
    }

    private void printEmptyGarage(){
        ConsoleControl.printlnString("Гараж пустует");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
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

        if(gm.garage().getCarsNumber() > 0 && index >= 1  && index <= gm.garage().getCarsNumber()){
            team.setCar(gm.garage().getCarById(gm.garage().getKeys().get(index - 1)));
            return new UserTeamCreationTab(gm, team, race);
        }
        else {
            return null;
        }
    }
}
