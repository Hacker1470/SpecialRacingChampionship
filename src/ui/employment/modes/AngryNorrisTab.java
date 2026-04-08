package ui.employment.modes;

import data.crew.JobType;
import data.crew.Pilot;
import data.race.Race;
import data.race.Team;
import data.race.TeamSample;
import game.GameMode;
import game.GameSession;
import game.Hospital;
import ui.base.MainTab;
import ui.base.Tab;
import ui.championship.RaceProcessTab;
import ui.championship.initializing.UserTeamCreationTab;
import ui.handling.ConsoleControl;

import java.util.ArrayList;

public class AngryNorrisTab extends Tab {
    public AngryNorrisTab(GameSession gm) {
        super(gm);
        gm.setGameMode(GameMode.NORMAL);

        if(gm.getHospital() == null){
            gm.setHospital(new Hospital(gm));
        }

        ArrayList<Pilot> people = new ArrayList<>(gm.dorm().getEmployeesByJob(JobType.PILOT)
                .stream().map(p -> (Pilot)p).toList());
        gm.getHospital().addNewIllPeople(people);
        for(Pilot p : people){
            gm.dorm().remove(p);
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
        ConsoleControl.printlnString("Вы прошли мимо легендарного Чака Норриса, даже");
        ConsoleControl.printlnString("с ним не поздоровавшись. Заподозрив что-то неладное,");
        ConsoleControl.printlnString("Чак Норрис вышел с биржи труда. Пройдя какое-то");
        ConsoleControl.printlnString("время за вами, он остановился возле окна общаги.");
        ConsoleControl.printlnString("Кучка людей увлечённо смотрела фильм с видеокассеты.");
        ConsoleControl.printlnString("Присмотревшись, у Чака от волнения зашевелился 132-ой волос");
        ConsoleControl.printlnString("левой ноги - это ведь момент его драки с Брюсом Ли.");
        ConsoleControl.printlnString("И что самое возмутительное, зрители были явно");
        ConsoleControl.printlnString("не на стороне нашего героя.");
        ConsoleControl.printlnString("***");
        ConsoleControl.printlnString("Что было потом - никому не известно, однако той кучкой");
        ConsoleControl.printlnString("людей оказались ваши пилоты, и сейчас они не в самом");
        ConsoleControl.printlnString("лучшем физическом и душевном состоянии. Всё, что смогли");
        ConsoleControl.printlnString("сказать очевидцы - Чак Норрис, распахнув дверь и выпустив");
        ConsoleControl.printlnString("тем самым столб пыли, ушёл в розовеющий закат.");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Все ваши нанятые пилоты перемещены в госпиталь.");
        ConsoleControl.printlnString("В течение нескольких гонок их нельзя будет взять в команду");
        ConsoleControl.printlnString("Подробнее: Главное меню -> Госпиталь");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[0] Продолжить");
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
        switch (req) {
            case "0":{
                return new MainTab(gm);
            }
            default:
                return new MainTab(gm);
        }
    }
}
