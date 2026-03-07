package vehicle;

import data.partslists.CatalogOfParts;
import data.partslists.PartType;

import java.util.List;

public class DownforcePart extends Part {
    private Integer downforce; //Прижимная сила. Улучшает управляемость и сцепление с дорогой

    public DownforcePart(String id, String name, int stockPrice, int quality, int mass, int damage,
                         int reputationLevel, List<String> connectivity, int downforce){
        super(PartType.DOWNFORCE, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.downforce = downforce;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
    }

    @Override
    public String getStringOfCharacteristics(){
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Название: ").append(name).append("\n");
        sb.append("Качество: ").append(quality).append("\n");
        sb.append("Масса: ").append(mass).append(" кг").append("\n");
        sb.append("Износ: ").append(damage).append(" %").append("\n");
        sb.append("Сила прижима: ").append(downforce).append(" попугаев").append("\n");
        sb.append("\n");
        sb.append("Совместимость:\n");

        for (Part i : CatalogOfParts.getAvailableByConnectivity(connectivity)){
            sb.append("* ").append(i.getName()).append("\n");;
        }
        sb.append("\n");

        sb.append("Стоимость: ").append(getRealPrice());

        return sb.toString();
    }

    @Override
    public Part getCopy() {
        return new DownforcePart(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                downforce);
    }
}
