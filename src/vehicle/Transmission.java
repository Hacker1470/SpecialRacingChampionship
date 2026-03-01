package vehicle;

import java.util.List;

public class Transmission extends Part {
    private Integer maxSpeed; //Максимальная скорость болида
    private Integer gears; //Количество передач. Чем больше передач, тем быстрее разгон
                            // и тем более опытный должен быть пилот
    public Transmission(String id, String name, int stockPrice, int quality, int mass, int damage,
                        int reputationLevel, List<String> connectivity, int maxSpeed, int gears){
        super(id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.maxSpeed = maxSpeed;
        this.gears = gears;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
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
