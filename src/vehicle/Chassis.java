package vehicle;

import java.util.List;

public class Chassis extends Part{
    private Integer aerodynamics;
    private Integer maxWeight;
    private Integer fuel;

    public Chassis(String id, String name, int stockPrice, int quality, int mass, int damage,
                   int reputationLevel, List<String> connectivity, int aerodynamics, int maxWeight, int fuel){
        super(id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.aerodynamics = aerodynamics;
        this.maxWeight = maxWeight;
        this.fuel = fuel;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
    }

    @Override
    public Part getCopy(){
        return new Chassis(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                aerodynamics,
                maxWeight,
                fuel);
    }
}
