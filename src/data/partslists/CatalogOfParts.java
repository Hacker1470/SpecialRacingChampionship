package data.partslists;

import vehicle.*;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogOfParts {

    public static HashMap<String, Part> allCatalog = new HashMap<>();
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

        allCatalog.put(TransmissionList.chain.getId(), TransmissionList.chain);
        allCatalog.put(TransmissionList.ladabox.getId(), TransmissionList.ladabox);
        allCatalog.put(TransmissionList.magnum.getId(), TransmissionList.magnum);

        allCatalog.put(ChassisList.woodenBox.getId(), ChassisList.woodenBox);
        allCatalog.put(ChassisList.ladaTazik.getId(), ChassisList.ladaTazik);
        allCatalog.put(ChassisList.straus.getId(), ChassisList.straus);

        allCatalog.put(DownforcePartList.bricks.getId(), DownforcePartList.bricks);
        allCatalog.put(DownforcePartList.spoiler.getId(), DownforcePartList.spoiler);

        allCatalog.put(SuspensionList.baseSpring.getId(), SuspensionList.baseSpring);
        allCatalog.put(SuspensionList.niceSpring.getId(), SuspensionList.niceSpring);

        allCatalog.put(WheelsList.bicycleWheels.getId(), WheelsList.bicycleWheels);
        allCatalog.put(WheelsList.normWheels.getId(), WheelsList.normWheels);
        allCatalog.put(WheelsList.niceWheels.getId(), WheelsList.niceWheels);
    }

    //ВАЖНО!!! Надо проверить на адекватность. Пока что я просто это написал.
    //07 03 2026 - похоже на правду
    public static HashMap<Integer, Part> getAvailableByReputation(PartType type, int rep){
        HashMap<Integer, Part> availableParts = new HashMap<>();

        int counter = 0;
        for (Part i : allCatalog.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(type.getId()))
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparingInt(Part::getReputationLevel))
                .toList())
        {
            if(i.getReputationLevel() <= rep){
                availableParts.put(++counter, i);
            }
        }

        return availableParts;
    }

    public static ArrayList<Part> getAvailableByConnectivity(List<String> connectivityList){
        ArrayList<Part> availableParts = new ArrayList<>();

        for (String part : connectivityList){
            if(allCatalog.containsKey(part)){
                availableParts.add(allCatalog.get(part));
            }
            else{
                availableParts.add(new UndefinedPart());
            }
        }

        return availableParts;
    }
}
