package data.vehicle;

import data.catalogs.CatalogOfParts;

import java.util.List;

public class Chassis extends Part {
    private int aerodynamics;
    private int maxWeight;

    public Chassis(long id, String article, String name, int stockPrice, int quality, int mass, int damage,
                   int reputationLevel, List<String> connectivity, int aerodynamics, int maxWeight){
        super(id, PartType.CHASSIS, article, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.aerodynamics = aerodynamics;
        this.maxWeight = maxWeight;
    }

    public int getAerodynamics(){
        return aerodynamics;
    }
    public int getMaxWeight(){
        return maxWeight;
    }

    @Override
    public int getRealPrice(){
        return getStockPrice() * (getDamage() + getQuality()) / 100;
    }

    @Override
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Качество: ").append(getQuality()).append("\n");
        sb.append("Масса: ").append(getMass()).append(" кг").append("\n");
        sb.append("Износ: ").append(getDamage()).append(" %").append("\n");
        sb.append("Обтекаемость: ").append(aerodynamics).append("\n");
        sb.append("Макс. суммарная масса оборудования: ").append(maxWeight).append(" кг").append("\n");
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
    public Part getCopy(Long idNew){
        return new Chassis(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                aerodynamics,
                maxWeight);
    }
}
