package vehicle;

import data.partslists.CatalogOfParts;
import data.partslists.PartType;

import java.util.List;

public class Engine extends Part {
    private Integer power;
    private Integer maxRpm;
    private Integer fuelConsumption;

    public Engine(String id, String name, int stockPrice, int quality, int mass, int damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm, int fuelConsumption){
        super(PartType.ENGINE, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

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
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(name).append("\n");
        sb.append("Качество: ").append(quality).append("\n");
        sb.append("Масса: ").append(mass).append(" кг").append("\n");
        sb.append("Износ: ").append(damage).append(" %").append("\n");
        sb.append("Мощность: ").append(power).append(" л/с").append("\n");
        sb.append("Максимальные обороты: ").append(maxRpm).append(" об/мин").append("\n");
        sb.append("Потребление топлива: ").append(fuelConsumption).append(" литров/с").append("\n");
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
