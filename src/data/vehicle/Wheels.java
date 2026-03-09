package data.vehicle;

import data.CatalogOfParts;

import java.util.List;

public class Wheels extends Part{
    private Integer adhesion; //Адгезия. Чем больше, тем лучше сцепление с дорогой при влажном или рыхлом покрытии

    public Wheels(String id, String name, int stockPrice, int quality, int mass, int damage,
                  int reputationLevel, List<String> connectivity, int adhesion){
        super(PartType.WHEELS, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.adhesion = adhesion;
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
        sb.append("Адгезия: ").append(adhesion).append(" %").append("\n");
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
        return new Wheels(
                getId(),
                getName(),
                getReputationLevel(),
                getMass(),
                getDamage(),
                getStockPrice(),
                getQuality(),
                List.copyOf(getConnectivity()),
                adhesion);
    }
}