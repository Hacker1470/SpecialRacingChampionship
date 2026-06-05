package ui.employment.modes;

import data.crew.JobType;
import data.crew.Pilot;
import game.GameModeNorris;
import game.GameSession;
import game.Hospital;
import ui.base.MainTab;
import ui.base.Tab;

import java.util.ArrayList;

public class AngryNorrisTab extends Tab {
    public AngryNorrisTab(GameSession gm) {
        super(gm);
        gm.setGameMode(GameModeNorris.NORMAL);

        if (gm.hospital() == null) {
            gm.setHospital(new Hospital(gm));
        }

        ArrayList<Pilot> people = new ArrayList<>(gm.dorm().getEmployeesByJob(JobType.PILOT)
                .stream().map(p -> (Pilot) p).toList());
        gm.hospital().addNewIllPeople(people);
        for (Pilot p : people) {
            gm.dorm().remove(p);
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
        gm.io().printlnString("Вы прошли мимо легендарного Чака Норриса, даже");
        gm.io().printlnString("с ним не поздоровавшись. Заподозрив что-то неладное,");
        gm.io().printlnString("Чак Норрис вышел с биржи труда. Пройдя какое-то");
        gm.io().printlnString("время за вами, он остановился возле окна общаги.");
        gm.io().printlnString("Кучка людей увлечённо смотрела фильм с видеокассеты.");
        gm.io().printlnString("Присмотревшись, у Чака от волнения зашевелился 132-ой волос");
        gm.io().printlnString("левой ноги - это ведь момент его драки с Брюсом Ли.");
        gm.io().printlnString("И что самое возмутительное, зрители были явно");
        gm.io().printlnString("не на стороне нашего героя.");
        gm.io().printlnString("***");
        gm.io().printlnString("Что было потом - никому не известно, однако той кучкой");
        gm.io().printlnString("людей оказались ваши пилоты, и сейчас они не в самом");
        gm.io().printlnString("лучшем физическом и душевном состоянии. Всё, что смогли");
        gm.io().printlnString("сказать очевидцы - Чак Норрис, распахнув дверь и выпустив");
        gm.io().printlnString("тем самым столб пыли, ушёл в розовеющий закат.");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Все ваши нанятые пилоты перемещены в госпиталь.");
        gm.io().printlnString("В течение нескольких гонок их нельзя будет взять в команду");
        gm.io().printlnString("Подробнее: Главное меню -> Госпиталь");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[0] Продолжить");
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
            case "0": {
                return new MainTab(gm);
            }
            default:
                return new MainTab(gm);
        }
    }
}
