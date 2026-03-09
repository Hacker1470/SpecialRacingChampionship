package game;

import data.vehicle.PartType;
import data.vehicle.Part;

import java.util.ArrayList;
import java.util.LinkedList;

public class Warehouse {
    LinkedList<Part> sklad;

    public Warehouse(){
        sklad = new LinkedList<>();
    }

    public void put(Part newPart){
        sklad.push(newPart);
    }

    public ArrayList<Part> getParts(PartType type){
        return new ArrayList<>(sklad.stream()
                .filter(part -> part.getType() == type)
                .toList());
    }

    public ArrayList<Part> getAll(){
        return new ArrayList<>(sklad);
    }

    public void remove(Part part){
        sklad.remove(part);
    }
}
