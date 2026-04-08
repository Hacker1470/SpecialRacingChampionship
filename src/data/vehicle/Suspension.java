package data.vehicle;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.Race;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.vehicle.enums.PartType;

import java.util.List;

public class Suspension extends Part {
    private final Integer hardness; //Твёрдость подвески. Чем больше твёрдость, тем лучше характеристики на трассах с ровным
    // и твёрдым покрытием
    private final Integer stability; //Стабильность. Чем больше, тем лучше преодолеваются повороты

    public Suspension(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                      int reputationLevel, List<String> connectivity, int hardness, int stability){
        super(id, PartType.SUSPENSION, article, name, stockPrice, quality, mass, damage,reputationLevel, connectivity);

        this.hardness = hardness;
        this.stability = stability;
    }

    @Override
    public int getRealPrice(){
        return (int)(getStockPrice() * ((100 - getDamage()) + getQuality()) / 100d);
    }

    public int getStability(){
        return stability;
    }

    @Override
    public String getBaseCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Стабильность: ").append(stability).append(" %").append("\n");
        sb.append("Жёсткость: ").append(hardness).append(" %").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(getConnectivity())){
            sb.append("* ").append(i.getName()).append("\n");;
        }
        return sb.toString();
    }

    @Override
    public Part getCopy(Long idNew) {
        return new Suspension(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                hardness,
                stability);
    }

    /**
     * Δ_подвеска_база = (Градус / 180) × (V_участка / 100) × K_поверхность
     * @param coefficient
     * @param terrain
     * @param racecar
     * @param pilot
     * @return
     */
    @Override
    public double getBaseDamage(double coefficient, RaceTrack rt, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return racecar.getEngine().getPower() * racecar.getEngine().getMaxRpm() * coefficient / 2500000;
    }
}
