package data.catalogs;

import data.parts.EmptyPart;
import data.parts.Part;
import data.partslists.*;
import data.parts.enums.PartType;

import java.util.*;

public class CatalogOfParts {

    private static HashMap<String, Part> allCatalog = new HashMap<>();

    public static void init() {
        initialize();
    }

    private static void initialize() {

        allCatalog.put(ChassisList.woodenBox.getArticle(), ChassisList.woodenBox.getCopy());
        allCatalog.put(ChassisList.ladaTazik.getArticle(), ChassisList.ladaTazik.getCopy());
        allCatalog.put(ChassisList.straus.getArticle(), ChassisList.straus.getCopy());

        allCatalog.put(EnginesList.pedal.getArticle(), EnginesList.pedal.getCopy());
        allCatalog.put(EnginesList.lada.getArticle(), EnginesList.lada.getCopy());
        allCatalog.put(EnginesList.sigma.getArticle(), EnginesList.sigma.getCopy());

        allCatalog.put(TransmissionList.chain.getArticle(), TransmissionList.chain.getCopy());
        allCatalog.put(TransmissionList.ladabox.getArticle(), TransmissionList.ladabox.getCopy());
        allCatalog.put(TransmissionList.magnum.getArticle(), TransmissionList.magnum.getCopy());

        allCatalog.put(WheelsList.bicycleWheels.getArticle(), WheelsList.bicycleWheels.getCopy());
        allCatalog.put(WheelsList.normWheels.getArticle(), WheelsList.normWheels.getCopy());
        allCatalog.put(WheelsList.niceWheels.getArticle(), WheelsList.niceWheels.getCopy());

        allCatalog.put(DownforcePartList.bricks.getArticle(), DownforcePartList.bricks.getCopy());
        allCatalog.put(DownforcePartList.spoiler.getArticle(), DownforcePartList.spoiler.getCopy());

        allCatalog.put(SuspensionList.baseSpring.getArticle(), SuspensionList.baseSpring.getCopy());
        allCatalog.put(SuspensionList.niceSpring.getArticle(), SuspensionList.niceSpring.getCopy());
    }

    public static Part getByArticle(String article) {
        return allCatalog.get(article);
    }

    /**
     *
     * @param type
     * @param rep
     * @return Детали в порядке возрастания репутации
     */
    public static ArrayList<Part> getAvailableByReputation(PartType type, int rep) {
        ArrayList<Part> availableParts = new ArrayList<>();

        for (Part i : allCatalog.values().stream()
                .filter(part -> part.getType() == type)
                .sorted(Comparator.comparingInt(Part::getReputationLevel))
                .toList()) {
            if (i.getReputationLevel() <= rep) {
                availableParts.add(i.getCopy());
            }
        }

        return availableParts;
    }

    public static ArrayList<Part> getAvailableByReputation(int rep) {
        ArrayList<Part> availableParts = new ArrayList<>();

        for (Part i : allCatalog.values()) {
            if (i.getReputationLevel() <= rep) {
                availableParts.add(i.getCopy());
            }
        }

        return availableParts;
    }

    public static ArrayList<Part> getAvailableByConnectivity(List<String> connectivityList) {
        ArrayList<Part> availableParts = new ArrayList<>();

        for (String part : connectivityList) {
            if (allCatalog.containsKey(part)) {
                availableParts.add(allCatalog.get(part).getCopy(Long.MIN_VALUE));
            } else {
                availableParts.add(new EmptyPart());
            }
        }

        return availableParts;
    }
}
