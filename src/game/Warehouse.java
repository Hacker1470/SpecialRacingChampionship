package game;

import data.vehicle.enums.PartType;
import data.vehicle.Part;

import java.util.*;

public class Warehouse {
    private HashMap<Long, Part> sklad;

    public Warehouse(){
        sklad = new HashMap<>();
    }

    public List<Long> getKeysAscending(){
        return sklad.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    public String generateStringCatalog(){
        StringBuilder sb = new StringBuilder(sklad.size() * 40);
        int counter = 1;
        for(Part part : sklad.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList()){
            sb.append(counter).append(") ").append(part.getName()).append(" ").append(part.getPostfix()).append("\n");
            counter++;
        }

        sb.append("\n");

        return sb.toString();
    }

    public void put(Part newPart){
        sklad.put(newPart.getId(), newPart);
    }

    public ArrayList<Part> getPartsByType(PartType type){
        return new ArrayList<>(sklad.values().stream()
                .filter(part -> part.getType() == type)
                .toList());
    }
    public ArrayList<Part> getAllParts(){
        return new ArrayList<>(sklad.values());
    }
    public Part getPartById(long id){
        return sklad.get(id);
    }

    public void remove(Part part){
        remove(part.getId());
    }
    public void remove(long id){
        sklad.remove(id);
    }

    public long getQuantityOfParts(){
        return sklad.size();
    }
}
