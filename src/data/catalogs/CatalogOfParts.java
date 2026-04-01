package data.catalogs;

import data.partslists.*;
import data.vehicle.*;
import data.vehicle.enums.PartType;

import java.util.*;

public class CatalogOfParts {

    private static HashMap<String, Part> allCatalog = new HashMap<>();

    public static void catalogInit(){
        initialize();
    }

    private static void initialize(){

        allCatalog.put(EnginesList.pedal.getArticle(), EnginesList.pedal);
        allCatalog.put(EnginesList.lada.getArticle(), EnginesList.lada);
        allCatalog.put(EnginesList.sigma.getArticle(), EnginesList.sigma);

        allCatalog.put(TransmissionList.chain.getArticle(), TransmissionList.chain);
        allCatalog.put(TransmissionList.ladabox.getArticle(), TransmissionList.ladabox);
        allCatalog.put(TransmissionList.magnum.getArticle(), TransmissionList.magnum);

        allCatalog.put(ChassisList.woodenBox.getArticle(), ChassisList.woodenBox);
        allCatalog.put(ChassisList.ladaTazik.getArticle(), ChassisList.ladaTazik);
        allCatalog.put(ChassisList.straus.getArticle(), ChassisList.straus);

        allCatalog.put(DownforcePartList.bricks.getArticle(), DownforcePartList.bricks);
        allCatalog.put(DownforcePartList.spoiler.getArticle(), DownforcePartList.spoiler);

        allCatalog.put(SuspensionList.baseSpring.getArticle(), SuspensionList.baseSpring);
        allCatalog.put(SuspensionList.niceSpring.getArticle(), SuspensionList.niceSpring);

        allCatalog.put(WheelsList.bicycleWheels.getArticle(), WheelsList.bicycleWheels);
        allCatalog.put(WheelsList.normWheels.getArticle(), WheelsList.normWheels);
        allCatalog.put(WheelsList.niceWheels.getArticle(), WheelsList.niceWheels);
    }

    public static Part getPartWithArticle(String article){
        return allCatalog.get(article);
    }

    //ВАЖНО!!! Надо проверить на адекватность. Пока что я просто это написал.
    //07 03 2026 - похоже на правду
    public static ArrayList<Part> getAvailableByReputation(PartType type, int rep){
        ArrayList<Part> availableParts = new ArrayList<>();

        for (Part i : allCatalog.values().stream()
                .filter(part -> part.getType() == type)
                .sorted(Comparator.comparingInt(Part::getReputationLevel))
                .toList())
        {
            if(i.getReputationLevel() <= rep){
                availableParts.add(i);
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
                availableParts.add(new EmptyPart());
            }
        }

        return availableParts;
    }
}
