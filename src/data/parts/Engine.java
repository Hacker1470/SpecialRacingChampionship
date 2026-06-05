package data.parts;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.racecar.Racecar;
import data.parts.enums.PartType;

import java.util.List;

public class Engine extends Part{
    private final int power;
    private final int maxRpm;

    public Engine(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm) {
        this(id, article, name, "", stockPrice, quality, mass, damage,
                reputationLevel, connectivity, power, maxRpm);
    }

    public Engine(long id, String article, String name, String postfix, int stockPrice, int quality, int mass, double damage,
                  int reputationLevel, List<String> connectivity, int power, int maxRpm) {
        super(id, PartType.ENGINE, article, name, postfix, stockPrice, quality, mass, damage, reputationLevel, connectivity);

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
    public int getRealPrice() {
        return (int) (getStockPrice() * ((100 - getDamage()) + getQuality()) / 100d);
    }

    @Override
    public String getBaseCharacteristics() {
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Мощность: ").append(power).append(" л/с").append("\n");
        sb.append("Максимальные обороты: ").append(maxRpm).append(" об/мин").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(getConnectivity())) {
            sb.append("* ").append(i.getName()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Part getCopy(Long idNew) {
        return new Engine(
                idNew,
                getArticle(),
                getName(),
                getPostfix(),
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
     *
     * @param coefficient
     * @param terrain
     * @param racecar
     * @param pilot
     * @return
     */
    @Override
    public double getBaseDamage(double coefficient, RaceTrack rt, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return power * maxRpm * coefficient / 2500000;
    }
}
