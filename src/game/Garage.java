package game;

import data.racecar.Racecar;

import java.util.*;

public class Garage {
    HashMap<Long, Racecar> cars;

    public Garage() {
        cars = new HashMap<>();
    }

    public String generateStringCatalog() {
        StringBuilder sb = new StringBuilder(cars.size() * 40);

        ArrayList<Long> keys = getKeys();

        for (int i = 1; i <= cars.size(); i++) {
            sb.append(i).append(") ").append(cars.get(keys.get(i - 1)).getName()).append("\n");
        }

        sb.append("\n");

        return sb.toString();
    }

    public ArrayList<Long> getKeys() {
        return new ArrayList<>(cars.keySet());
    }

    public void put(Racecar newRacecar) {
        cars.put(newRacecar.getId(), newRacecar);
    }

    public Racecar getCarById(long id) {
        return cars.get(id);
    }

    public int getCarsNumber() {
        return cars.size();
    }

    public void deleteCar(long carId) {
        cars.remove(carId);
    }
}
