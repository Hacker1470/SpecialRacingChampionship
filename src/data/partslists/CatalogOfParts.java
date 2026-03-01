package data.partslists;

import vehicle.Engine;
import vehicle.Part;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogOfParts {

    public static HashMap<String, Engine> allCatalog = new HashMap<>();
    public static HashMap<String, Engine> engineCatalog = new HashMap<>();

    public static void catalogInit(){
        initializeEngines();
    }

    private static void initializeEngines(){
        engineCatalog.put(EnginesList.pedal.getId(), EnginesList.pedal);
        engineCatalog.put(EnginesList.lada.getId(), EnginesList.lada);
        engineCatalog.put(EnginesList.sigma.getId(), EnginesList.sigma);

        allCatalog.put(EnginesList.pedal.getId(), EnginesList.pedal);
        allCatalog.put(EnginesList.lada.getId(), EnginesList.lada);
        allCatalog.put(EnginesList.sigma.getId(), EnginesList.sigma);
    }

    //ВАЖНО!!! Надо проверить на адекватность. Пока что я просто это написал.
    public static HashMap<Integer, Part> getAvailableByReputation(PartType type, int rep){
        HashMap<Integer, Part> availableParts = new HashMap<>();

        int counter = 0;
        for (Part i : allCatalog.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(type.getId()))
                .map(Map.Entry::getValue)
                .toList()){
            if(i.getReputationLevel() <= rep){
                availableParts.put(++counter, i);
            }
        }

        return availableParts;
    }
}
