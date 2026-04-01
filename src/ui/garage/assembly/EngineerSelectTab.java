package ui.garage.assembly;

import data.crew.Engineer;
import data.crew.JobType;
import game.GameSession;
import ui.base.Tab;
import ui.handling.ConsoleControl;

import java.util.List;

public class EngineerSelectTab extends Tab{
    List<Engineer> availableEngineers;
    RacecarSample sample;

    public EngineerSelectTab(GameSession gm, RacecarSample sample) {
        super(gm);
        this.sample = sample;
    }

    @Override
    public Tab show() {
        availableEngineers = gm.dorm().getEmployeesByJob(JobType.ENGINEER).stream().map(x -> (Engineer)x).toList();
        outputMain();
        return menuHandler();
    }

    @Override
    protected void printListOfMenus(){
        ConsoleControl.printlnString(gm.getSponsor());
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString(JobType.ENGINEER.getEmployGroupTitle());

        if(availableEngineers.isEmpty()){
            printListOfMenusNoParts();
        }
        else{
            printListOfMenusMain();
        }

        ConsoleControl.printlnString("[0] Вернуться к сборке");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("Введите число, чтобы открыть пункт меню");
    }

    private void printListOfMenusNoParts(){
        ConsoleControl.printlnString("В общежитии нет инженеров");
        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
    }

    private void printListOfMenusMain(){
        int counter = 1;
        for(Engineer engi : availableEngineers){
            ConsoleControl.printlnString("[" + counter++ + "] " + engi.getName() + " " + engi.getPostfix());
        }

        ConsoleControl.printlnString("");
        ConsoleControl.printlnString("=============================================");
        ConsoleControl.printlnString("[N] Выбрать инженера под номером N");
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
            return new CarAssemblyTab(gm, sample);
        }

        int index;
        try {
            index = Integer.parseInt(req);
        }
        catch (NumberFormatException e){
            return null;
        }

        if(index >= 1 && index <= availableEngineers.size()){
            sample.setEngineer(availableEngineers.get(index - 1));
            return new CarAssemblyTab(gm, sample);
        }
        else {
            return null;
        }
    }
}
