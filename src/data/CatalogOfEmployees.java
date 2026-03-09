package data;

import data.crew.Employee;
import data.crew.JobType;
import data.crew.Pilot;
import data.employeeslists.EngineerList;
import data.employeeslists.PilotList;

import java.util.*;

public class CatalogOfEmployees {

    public static HashMap<String, Employee> allCatalog = new HashMap<>();

    public static void catalogInit(){
        initialize();
    }

    private static void initialize(){

        allCatalog.put(EngineerList.maslyonok.getId(), EngineerList.maslyonok);
        allCatalog.put(EngineerList.pazhiloypauk.getId(), EngineerList.pazhiloypauk);

        allCatalog.put(PilotList.cheboks.getId(), PilotList.cheboks);
        allCatalog.put(PilotList.schumacher.getId(), PilotList.schumacher);
    }

    public static HashMap<Integer, Employee> getAvailableByReputation(JobType type, int rep){
        HashMap<Integer, Employee> availableEmployees = new HashMap<>();

        int counter = 0;
        for (Employee i : allCatalog.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(type.getId()))
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparingInt(Employee::getReputationLevel))
                .toList())
        {
            if(i.getReputationLevel() <= rep){
                availableEmployees.put(++counter, i);
            }
        }

        return availableEmployees;
    }
}
