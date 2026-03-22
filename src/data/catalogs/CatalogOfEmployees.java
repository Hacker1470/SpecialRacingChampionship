package data.catalogs;

import data.crew.Employee;
import data.crew.JobType;
import data.employeeslists.EngineerList;
import data.employeeslists.PilotList;

import java.util.*;

public class CatalogOfEmployees {

    public static HashMap<String, Employee> allCatalog = new HashMap<>();

    public static void catalogInit(){
        initialize();
    }

    private static void initialize(){

        allCatalog.put(EngineerList.maslyonok.getArticle(), EngineerList.maslyonok);
        allCatalog.put(EngineerList.pazhiloypauk.getArticle(), EngineerList.pazhiloypauk);

        allCatalog.put(PilotList.cheboks.getArticle(), PilotList.cheboks);
        allCatalog.put(PilotList.schumacher.getArticle(), PilotList.schumacher);
    }

    public static ArrayList<Employee> getAvailableByReputation(JobType type, int rep){
        ArrayList<Employee> availableEmployees = new ArrayList<>();

        for (Employee i : allCatalog.values().stream()
                .filter(entry -> entry.getType() == type)
                .sorted(Comparator.comparingInt(Employee::getReputationLevel))
                .toList())
        {
            if(i.getReputationLevel() <= rep){
                availableEmployees.add(i);
            }
        }

        return availableEmployees;
    }
}
