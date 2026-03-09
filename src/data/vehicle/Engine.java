package data.vehicle;

import data.CatalogOfParts;

import java.util.List;

public class Engine extends Part {
    private int power;
    private int maxRpm;
    private int fuelConsumption;

    public Engine(String id, String name, int stockPrice, int quality, int mass, int damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm, int fuelConsumption){
        super(PartType.ENGINE, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.power = power;
        this.maxRpm = maxRpm;
        this.fuelConsumption = fuelConsumption;
    }

    public int getPower() {
        return power;
    }
    public int getMaxRpm() {
        return maxRpm;
    }
    public int getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public int getRealPrice(){
        return getStockPrice() * (getDamage() + getQuality()) / 100;
    }

    @Override
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Мощность: ").append(power).append(" л/с").append("\n");
        sb.append("Максимальные обороты: ").append(maxRpm).append(" об/мин").append("\n");
        sb.append("Потребление топлива: ").append(fuelConsumption).append(" литров/с").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(getConnectivity())){
            sb.append("* ").append(i.getName()).append("\n");;
        }
        sb.append("\n");

        sb.append("Стоимость: ").append(getRealPrice());

        return sb.toString();
    }

    @Override
    public Part getCopy() {
        return new Engine(
                getId(),
                getName(),
                getReputationLevel(),
                getMass(),
                getDamage(),
                getStockPrice(),
                getQuality(),
                List.copyOf(getConnectivity()),
                power,
                maxRpm,
                fuelConsumption);
    }
}
