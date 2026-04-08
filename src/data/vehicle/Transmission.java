package data.vehicle;

import data.catalogs.CatalogOfParts;
import data.crew.Pilot;
import data.race.map.RaceTrack;
import data.race.map.terrains.MapTerrain;
import data.vehicle.enums.PartType;

import java.util.List;

public class Transmission extends Part{
    private final Integer maxSpeed; //Максимальная скорость болида
    private final Integer gears; //Количество передач. Чем больше передач, тем быстрее разгон
                            // и тем более опытный должен быть пилот
    public Transmission(long id, String article, String name, int stockPrice, int quality, int mass, double damage,
                        int reputationLevel, List<String> connectivity, int maxSpeed, int gears){
        super(id, PartType.TRANSMISSION, article, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.maxSpeed = maxSpeed;
        this.gears = gears;
    }

    @Override
    public int getRealPrice(){
        return (int)(getStockPrice() * ((100 - getDamage()) + getQuality()) / 100d);
    }

    @Override
    public String getBaseCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Передаточное число: ").append(gears).append("\n");
        sb.append("Максимальная скорость: ").append(maxSpeed).append(" км/ч").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(getConnectivity())){
            sb.append("* ").append(i.getName()).append("\n");;
        }
        return sb.toString();
    }

    public int getGears(){
        return gears;
    }
    public int getMaxSpeed(){
        return maxSpeed;
    }

    @Override
    public Part getCopy(Long idNew) {
        return new Transmission(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                maxSpeed,
                gears);
    }

    /**
     * Δ_коробка_база = (Мощность × Обороты / 2500000) × K_участок_кпп
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
