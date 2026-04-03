package data.vehicle;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.map.MapTerrain;
import data.vehicle.enums.PartType;

import java.util.List;

public class Engine extends Part implements IDamageble{
    private final int power;
    private final int maxRpm;

    public Engine(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm){
        super(id, PartType.ENGINE, article, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.power = power;
        this.maxRpm = maxRpm;
    }

    public int getPower() {
        return power;
    }
    public int getMaxRpm() {
        return maxRpm;
    }

    @Override
    public int getRealPrice(){
        return getStockPrice() * ((int)getDamage() + getQuality()) / 100;
    }

    @Override
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Мощность: ").append(power).append(" л/с").append("\n");
        sb.append("Максимальные обороты: ").append(maxRpm).append(" об/мин").append("\n");
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
    public Part getCopy(Long idNew) {
        return new Engine(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                power,
                maxRpm);
    }

    /**
     * Δ_двигатель_база = (Мощность × Обороты / 2500000) × K_участок
     * @param coefficient
     * @param terrain
     * @param racecar
     * @param pilot
     * @return
     */
    @Override
    public double getBaseDamage(double coefficient, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return power * maxRpm * coefficient / 2500000;
    }
}
