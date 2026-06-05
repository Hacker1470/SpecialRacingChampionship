package data.parts;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.racecar.Racecar;
import data.parts.enums.PartType;

import java.util.List;

public class Wheels extends Part {
    private final Integer adhesion; //Адгезия. Чем больше, тем лучше сцепление с дорогой при влажном или рыхлом покрытии

    public Wheels(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                  int reputationLevel, List<String> connectivity, int adhesion) {
        super(id, PartType.WHEELS, article, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.adhesion = adhesion;
    }

    @Override
    public int getRealPrice() {
        return (int) (getStockPrice() * ((100 - getDamage()) + getQuality()) / 100d);
    }

    public int getAdhesion() {
        return adhesion;
    }

    @Override
    public String getBaseCharacteristics() {
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Адгезия: ").append(adhesion).append(" %").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(getConnectivity())) {
            sb.append("* ").append(i.getName()).append("\n");
            ;
        }
        return sb.toString();
    }

    @Override
    public Part getCopy(Long idNew) {
        return new Wheels(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                adhesion);
    }

    /**
     * Δ_колёса_база = (1 + (100 - Сцепление) / 100) × K_поверхность × K_участок_колеса
     *
     * @param coefficient
     * @param terrain
     * @param racecar
     * @param pilot
     * @return
     */
    @Override
    public double getBaseDamage(double coefficient, RaceTrack rt, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return 1 + terrain.getSurface().getCoefficient() * coefficient * (100 - adhesion) / 100;
    }
}