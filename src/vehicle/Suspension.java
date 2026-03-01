package vehicle;

import java.util.List;

public class Suspension extends Part {
    public Integer hardness; //Твёрдость подвески. Чем больше твёрдость, тем лучше характеристики на трассах с ровным
    // и твёрдым покрытием
    public Integer stability; //Стабильность. Чем больше, тем лучше преодолеваются повороты

    public Suspension(String id, String name, int stockPrice, int quality, int mass, int damage,
                      int reputationLevel, List<String> connectivity, int hardness, int stability){
        super(id, name, stockPrice, quality, mass, damage,reputationLevel, connectivity);

        this.hardness = hardness;
        this.stability = stability;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
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
