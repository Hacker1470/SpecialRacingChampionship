package ui.championship;

import data.race.Race;
import data.race.map.enums.WeatherType;
import data.special.RandomGenerator;
import game.GameSession;
import ui.base.Tab;
import ui.championship.initializing.UserTeamCreationTab;

public class RacePreviewTab extends Tab {

    private Race race;

    public RacePreviewTab(GameSession gm, Race race) {
        super(gm);
        this.race = race;

        setRacemapWeather();
    }

    private void setRacemapWeather(){
        int weatherCode = RandomGenerator.getInteger(1, 100);
        if (weatherCode < 40) {
            race.getMap().setWeather(WeatherType.SUNNY);
        } else if (weatherCode < 85) {
            race.getMap().setWeather(WeatherType.CLOUDY);
        } else {
            race.getMap().setWeather(WeatherType.RAINING);
        }
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
        gm.io().printlnString("====== ПОДГОТОВКА К ГОНКЕ ======");
        gm.io().printlnString("Выбранная карта: " + race.getMap().getName());
        gm.io().printlnString("Погода: " + race.getMap().getWeather().getName());
        gm.io().printlnString("Карта трассы:");
        for (int i = 0; i < race.getMap().getNumberOfTerrains(); i++) {
            gm.io().printlnString(race.getMap().getTerrainByNumber(i).getCharacteristics());
        }
        gm.io().printlnString("Число команд: " + race.getRequiredTeamsNumber());
        gm.io().printlnString("Сумма для участия: " + race.getDeposit());
        gm.io().printlnString("Награда за первое место: " + (int)(race.getPrize() * 0.55));
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[1] Продолжить");
        gm.io().printlnString("[0] Назад");
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
        return switch (req) {
            case "1" -> new UserTeamCreationTab(gm, race);
            case "0" -> new ChampionshipTab((gm));
            default -> null;
        };
    }
}
