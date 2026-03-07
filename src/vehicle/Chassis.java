package vehicle;

import data.partslists.CatalogOfParts;
import data.partslists.PartType;

import java.util.List;

public class Chassis extends Part {
    private Integer aerodynamics;
    private Integer maxWeight;
    private Integer fuel;

    public Chassis(String id, String name, int stockPrice, int quality, int mass, int damage,
                   int reputationLevel, List<String> connectivity, int aerodynamics, int maxWeight, int fuel){
        super(PartType.CHASSIS, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.aerodynamics = aerodynamics;
        this.maxWeight = maxWeight;
        this.fuel = fuel;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
    }

    @Override
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(name).append("\n");
        sb.append("Качество: ").append(quality).append("\n");
        sb.append("Масса: ").append(mass).append(" кг").append("\n");
        sb.append("Износ: ").append(damage).append(" %").append("\n");
        sb.append("Обтекаемость: ").append(aerodynamics).append("\n");
        sb.append("Макс. суммарная масса оборудования: ").append(maxWeight).append(" кг").append("\n");
        sb.append("Количество топлива: ").append(fuel).append(" литров").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(connectivity)){
            sb.append("* ").append(i.getName()).append("\n");;
        }
        sb.append("\n");

        sb.append("Стоимость: ").append(getRealPrice());

        return sb.toString();
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
