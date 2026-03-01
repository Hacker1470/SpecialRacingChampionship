package vehicle;

import data.partslists.ConnectivitySearcher;
import data.partslists.PartType;
import ui.ConsoleControl;

import java.util.ArrayList;
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

        sb.append("Название: ").append(getName()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getName()).append(" %").append("\n");
        sb.append("Мощность: ").append(getPower()).append(" л/с").append("\n");
        sb.append("Максимальные обороты: ").append(getMaxRpm()).append(" об/мин").append("\n");
        sb.append("Потребление топлива: ").append(getName()).append(" литров/с").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        //ЗАКОНЧИЛ ЗДЕСЬ 01 03 2026
        //надо как-то приконнектить новые методы из CatalogOfParts для поиска совместимостей вот сюда
        for (Part i : ConnectivitySearcher.findAllConnectivities(chosenEngine)){
            ConsoleControl.printlnString("* " + i.getName());

        }

        ConsoleControl.printlnString("Стоимость: " + chosenEngine.getRealPrice());
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
