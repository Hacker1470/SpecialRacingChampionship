package vehicle;

import data.partslists.CatalogOfParts;
import data.partslists.PartType;

import java.util.List;

public class Transmission extends Part {
    private Integer maxSpeed; //Максимальная скорость болида
    private Integer gears; //Количество передач. Чем больше передач, тем быстрее разгон
                            // и тем более опытный должен быть пилот
    public Transmission(String id, String name, int stockPrice, int quality, int mass, int damage,
                        int reputationLevel, List<String> connectivity, int maxSpeed, int gears){
        super(PartType.TRANSMISSION, id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.maxSpeed = maxSpeed;
        this.gears = gears;
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
        sb.append("Передаточное число: ").append(gears).append("\n");
        sb.append("Максимальная скорость: ").append(maxSpeed).append(" км/ч").append("\n");
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
        return new Transmission(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                maxSpeed,
                gears);
    }
}
