package vehicle;

import data.partslists.CatalogOfParts;
import data.partslists.PartType;

import java.util.List;

public class Suspension extends Part {
    public Integer hardness; //Твёрдость подвески. Чем больше твёрдость, тем лучше характеристики на трассах с ровным
    // и твёрдым покрытием
    public Integer stability; //Стабильность. Чем больше, тем лучше преодолеваются повороты

    public Suspension(String id, String name, int stockPrice, int quality, int mass, int damage,
                      int reputationLevel, List<String> connectivity, int hardness, int stability){
        super(PartType.SUSPENSION, id, name, stockPrice, quality, mass, damage,reputationLevel, connectivity);

        this.hardness = hardness;
        this.stability = stability;
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
        sb.append("Стабильность: ").append(stability).append(" %").append("\n");
        sb.append("Жёсткость: ").append(hardness).append(" %").append("\n");
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
        return new Suspension(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                hardness,
                stability);
    }
}
