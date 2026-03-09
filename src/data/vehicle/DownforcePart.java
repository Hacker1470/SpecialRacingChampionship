package data.vehicle;

import data.CatalogOfParts;

import java.util.List;

public class DownforcePart extends Part {
    private int downforce; //Прижимная сила. Улучшает управляемость и сцепление с дорогой

    public DownforcePart(String id, String name, int stockPrice, int quality, int mass, int damage,
                         int reputationLevel, List<String> connectivity, int downforce){
        super(PartType.DOWNFORCE, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.downforce = downforce;
    }

    public int getDownforce(){
        return downforce;
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
    public Part getCopy() {
        return new DownforcePart(
                getId(),
                getName(),
                getReputationLevel(),
                getMass(),
                getDamage(),
                getStockPrice(),
                getQuality(),
                List.copyOf(getConnectivity()),
                downforce);
    }
}
