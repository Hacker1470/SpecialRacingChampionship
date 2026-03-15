package game;

import data.vehicle.PartType;
import data.vehicle.Part;

import java.util.*;

public class Warehouse {
    private HashMap<Long, Part> sklad;

    public Warehouse(){
        sklad = new HashMap<>();
    }

    public void put(Part newPart){
        sklad.put(newPart.getId(), newPart);
    }

    public ArrayList<Part> getParts(PartType type){
        return new ArrayList<>(sklad.values().stream()
                .filter(part -> part.getType() == type)
                .toList());
    }

    public ArrayList<Part> getAll(){
        return new ArrayList<>(sklad.values());
    }

    public void remove(Part part){
        remove(part.getId());
    }
    public void remove(long id){
        sklad.remove(id);
    }
}
