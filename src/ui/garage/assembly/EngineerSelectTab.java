package ui.garage.assembly;

import data.crew.Engineer;
import data.crew.JobType;
import data.racecar.RacecarSample;
import game.GameSession;
import ui.base.Tab;

import java.util.List;

public class EngineerSelectTab extends Tab {
    List<Engineer> availableEngineers;
    RacecarSample sample;

    public EngineerSelectTab(GameSession gm, RacecarSample sample) {
        super(gm);
        this.sample = sample;
    }

    @Override
    public Tab show() {
        availableEngineers = gm.dorm().getEmployeesByJob(JobType.ENGINEER).stream().map(x -> (Engineer) x).toList();
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus() {
        gm.io().printlnString(gm.getSponsor());
        gm.io().printlnString("");
        gm.io().printlnString(JobType.ENGINEER.getEmployGroupTitle());

        if (availableEngineers.isEmpty()) {
            printListOfMenusNoParts();
        } else {
            printListOfMenusMain();
        }

        gm.io().printlnString("[0] Вернуться к сборке");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printListOfMenusNoParts() {
        gm.io().printlnString("В общежитии нет инженеров");
        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
    }

    private void printListOfMenusMain() {
        int counter = 1;
        for (Engineer engi : availableEngineers) {
            gm.io().printlnString("[" + counter++ + "] " + engi.getName() + " " + engi.getPostfix());
        }

        gm.io().printlnString("");
        gm.io().printlnString("=============================================");
        gm.io().printlnString("[N] Выбрать инженера под номером N");
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
            return new CarAssemblyTab(gm, sample);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        } catch (NumberFormatException e) {
            return null;
        }

        if (index >= 1 && index <= availableEngineers.size()) {
            sample.setEngineer(availableEngineers.get(index - 1));
            return new CarAssemblyTab(gm, sample);
        } else {
            return null;
        }
    }
}
