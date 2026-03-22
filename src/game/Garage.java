package game;

import data.catalogs.CatalogOfParts;
import data.vehicle.Part;
import data.vehicle.PartType;
import data.vehicle.Racecar;

import java.util.*;

public class Garage {
    LinkedList<Racecar> cars;

    public Garage(){
        cars = new LinkedList<>();
    }

    public String getListOfCars(){
        StringBuilder sb = new StringBuilder(cars.size() * 40);

        for (int i = 1; i <= cars.size(); i++){
            sb.append(i).append(") ").append(cars.get(i).getName()).append("\n");
        }

        sb.append("\n");

        return sb.toString();
    }

    public Racecar getCarByIndex(int index){
        return cars.get(index);
    }

    public int getCarsNumber(){
        return cars.size();
    }
    public void deleteCar(Racecar car){
        cars.remove(car);
    }
}
