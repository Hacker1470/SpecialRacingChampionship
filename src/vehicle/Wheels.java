package vehicle;

import java.util.List;

public class Wheels extends Part{
    private Integer adhesion; //Адгезия. Чем больше, тем лучше сцепление с дорогой при влажном или рыхлом покрытии

    public Wheels(String id, String name, int stockPrice, int quality, int mass, int damage,
                  int reputationLevel, List<String> connectivity, int adhesion){
        super(id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.adhesion = adhesion;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
    }

    @Override
    public Part getCopy() {
        return new Wheels(
                id,
                name,
                reputationLevel,
                mass,
                damage,
                stockPrice,
                quality,
                List.copyOf(connectivity),
                adhesion);
    }
}
