package data.vehicle;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.map.MapTerrain;
import data.vehicle.enums.PartType;

import java.util.List;

public class DownforcePart extends Part{
    private final int downforce; //Прижимная сила. Улучшает управляемость и сцепление с дорогой

    public DownforcePart(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                         int reputationLevel, List<String> connectivity, int downforce){
        super(id, PartType.DOWNFORCE, article, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.downforce = downforce;
    }

    public int getDownforce(){
        return downforce;
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
        sb.append("Сила прижима: ").append(downforce).append(" попугаев").append("\n");
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
        return new DownforcePart(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                downforce);
    }

    /**
     * Δ_спойлер_база = (V_участка / V_max_потенциал) × K_участок_спойлер
     * @param terrain
     * @param racecar
     * @param pilot
     * @return
     */
    @Override
    public double getBaseDamage(double coefficient, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return terrain.getAverageSpeed(racecar, pilot) * coefficient / racecar.getMaxPotentialSpeed();
    }
}
