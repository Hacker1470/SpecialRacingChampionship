package data.special;

import data.crew.Engineer;
import data.vehicle.*;
import data.vehicle.RacecarSample;
import ui.garage.assembly.assemblyexceptions.UnmatchingPartsAssemblyException;

import java.util.ArrayList;
import java.util.List;

public class AssemblingHelpers {
    public static void checkPartsMatching(RacecarSample sample) throws UnmatchingPartsAssemblyException {
        List<Part> parts = new ArrayList<>(List.of(
                sample.getChassis(), sample.getEngine(),
                sample.getTransmission(), sample.getWheels()));
        List<String> articles = new ArrayList<>(List.of(
                sample.getChassis().getArticle(), sample.getEngine().getArticle(),
                sample.getTransmission().getArticle(), sample.getWheels().getArticle()));

        if(sample.getSuspension() != null){
            parts.add(sample.getSuspension());
            articles.add(sample.getSuspension().getArticle());
        }
        if(sample.getDownforcePart() != null){
            parts.add(sample.getDownforcePart());
            articles.add(sample.getDownforcePart().getArticle());
        }

        List<String> connectivities;
        boolean estProbitie;
        for(Part part : parts){
            connectivities = new ArrayList<>(part.getConnectivity());
            while(!connectivities.isEmpty()){
                String currentType = connectivities.getFirst().substring(0,3);
                estProbitie = false;
                for(String str : connectivities.stream()
                        .filter(n -> n.startsWith(currentType))
                        .toList()){
                    if(articles.contains(str)){
                        estProbitie = true;
                        break;
                    }
                }
                if(estProbitie){
                    connectivities = connectivities.stream().filter(n -> !n.startsWith(currentType)).toList();
                }
                else {
                    throw new UnmatchingPartsAssemblyException(part);
                }
            }
        }
    }

    public static void addEngineerPerks(RacecarSample sample){
        Engineer engineer = sample.getEngineer();
        Chassis chassis = sample.getChassis();
        Engine engine = sample.getEngine();
        Transmission transmission = sample.getTransmission();
        Wheels wheels = sample.getWheels();
        Suspension suspension = sample.getSuspension();
        DownforcePart downforcePart = sample.getDownforcePart();

        chassis.setConnectionReliability(
            (int)(
                (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                * (1 + engineer.getExperience()/200d)
            )
        );
        engine.setConnectionReliability(
            (int)(
                (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                * (1 + engineer.getExperience()/200d)
            )
        );
        transmission.setConnectionReliability(
            (int)(
                (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                * (1 + engineer.getExperience()/200d)
            )
        );
        wheels.setConnectionReliability(
            (int)(
                (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                * (1 + engineer.getExperience()/200d)
            )
        );
        if(suspension != null){
            suspension.setConnectionReliability(
                (int)(
                    (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                    * (1 + engineer.getExperience()/200d)
                )
            );
        }
        if(downforcePart != null){
            downforcePart.setConnectionReliability(
                (int)(
                    (50 - engineer.getWavyHands()/2d + engineer.getScrewing())
                    * (1 + engineer.getExperience()/200d)
                )
            );
        }
    }
}
