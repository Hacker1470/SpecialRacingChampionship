package vehicle;

import java.util.ArrayList;
import java.util.List;

public class Engine extends Part {
    private Integer power;
    private Integer maxRpm;
    private Integer fuelConsumption;

    public Engine(String id, String name, int stockPrice, int quality, int mass, int damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm, int fuelConsumption){
        super(id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.power = power;
        this.maxRpm = maxRpm;
        this.fuelConsumption = fuelConsumption;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getMaxRpm() {
        return maxRpm;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
    }

    @Override
    public Part getCopy() {
        return new Engine(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                power,
                maxRpm,
                fuelConsumption);
    }
}
