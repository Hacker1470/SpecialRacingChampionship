package vehicle;

import java.util.List;

public class DownforcePart extends Part {
    private Integer downforce; //прижимная сила. Улучшает управляемость и сцепление с дорогой

    public DownforcePart(String id, String name, int stockPrice, int quality, int mass, int damage,
                         int reputationLevel, List<String> connectivity, int downforce){
        super(id, name, stockPrice, quality, mass, damage, reputationLevel, connectivity);

        this.downforce = downforce;
    }

    @Override
    public int getRealPrice(){
        return stockPrice * (damage + quality) / 100;
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
