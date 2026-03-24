package data.vehicle;

import data.catalogs.CatalogOfParts;

import java.util.List;

public class Suspension extends Part {
    public Integer hardness; //Твёрдость подвески. Чем больше твёрдость, тем лучше характеристики на трассах с ровным
    // и твёрдым покрытием
    public Integer stability; //Стабильность. Чем больше, тем лучше преодолеваются повороты

    public Suspension(long id, String article, String name, int stockPrice, int quality, int mass, int damage,
                      int reputationLevel, List<String> connectivity, int hardness, int stability){
        super(id, PartType.SUSPENSION, article, name, stockPrice, quality, mass, damage,reputationLevel, connectivity);

        this.hardness = hardness;
        this.stability = stability;
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
        sb.append("Стабильность: ").append(stability).append(" %").append("\n");
        sb.append("Жёсткость: ").append(hardness).append(" %").append("\n");
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
    public Part getCopy(Long idNew) {
        return new Suspension(
                idNew,
                getArticle(),
                getName(),
                getStockPrice(),
                getQuality(),
                getMass(),
                getDamage(),
                getReputationLevel(),
                List.copyOf(getConnectivity()),
                hardness,
                stability);
    }
}
