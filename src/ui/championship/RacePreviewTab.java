package ui.championship;

import data.race.Race;
import data.race.map.enums.WeatherType;
import data.special.RandomGenerator;
import game.GameSession;
import ui.base.Tab;
import ui.championship.initializing.UserTeamCreationTab;
import ui.handling.ConsoleControl;

public class RacePreviewTab extends Tab{

    private Race race;
    public RacePreviewTab(GameSession gm, Race race) {
        super(gm);
        this.race = race;

        int weather = RandomGenerator.getInteger(1,100);
        if(weather < 50){
            race.getMap().setWeather(WeatherType.SUNNY);
        }
        else if (weather < 85){
            race.getMap().setWeather(WeatherType.CLOUDY);
        }
        else {
            race.getMap().setWeather(WeatherType.RAINING);
        }
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
        ConsoleControl.printlnString("====== ПОДГОТОВКА К ГОНКЕ ======");
        ConsoleControl.printlnString("Выбранная карта: " + race.getMap().getName());
        ConsoleControl.printlnString("Погода: " + race.getMap().getWeather().getName());
        ConsoleControl.printlnString("Карта трассы:");
        for (int i = 0; i < race.getMap().getNumberOfTerrains(); i++){
            ConsoleControl.printlnString(race.getMap().getTerrainByNumber(i).getCharacteristics());
        }
        ConsoleControl.printlnString("Число команд: " + race.getTeamsNumber());
        ConsoleControl.printlnString("Сумма для участия: " + race.getDeposit());
        ConsoleControl.printlnString("Награда за первое место: " + race.getPrize() * 0.6);
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[1] Продолжить");
        ConsoleControl.printlnString("[0] Назад");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
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
        return switch (req) {
            case "1" -> new UserTeamCreationTab(gm, race);
            case "0" -> new ChampionshipTab((gm));
            default -> null;
        };
    }
}
