package data.special;

import data.crew.Engineer;
import data.parts.*;
import data.racecar.RacecarSample;
import ui.garage.assembly.assemblyexceptions.UnmatchingPartsAssemblyException;

import java.util.ArrayList;
import java.util.List;

public class AssemblingHelpers {
    public static void checkPartsMatching(RacecarSample sample)
            throws UnmatchingPartsAssemblyException {
        List<Part> parts = sample.getNotNullParts();
        List<String> articles = new ArrayList<>(parts.stream().map(Part::getArticle).toList());

        List<String> connectivities;
        boolean estProbitie;
        for (Part part : parts) {
            connectivities = new ArrayList<>(part.getConnectivity());
            while (!connectivities.isEmpty()) {
                String currentType = connectivities.getFirst().substring(0, 3);
                estProbitie = false;
                for (String str : connectivities.stream()
                        .filter(n -> n.startsWith(currentType))
                        .toList()) {
                    if (articles.contains(str)) {
                        estProbitie = true;
                        break;
                    }
                }
                if (estProbitie) {
                    connectivities = connectivities.stream().filter(n -> !n.startsWith(currentType)).toList();
                } else {
                    throw new UnmatchingPartsAssemblyException(part);
                }
            }
        }
    }

    public static void addEngineerPerks(RacecarSample sample) {
        Engineer engineer = sample.getEngineer();

        ArrayList<Part> parts = sample.getNotNullParts();

        for(Part p : parts){
            p.setConnectionReliability(
                    (int) (
                            (50 - engineer.getWavyHands() / 2d + engineer.getScrewing())
                                    * (1 + engineer.getExperience() / 100d)
                    )
            );
        }
    }
}
