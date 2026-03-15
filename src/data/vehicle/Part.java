package data.vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class Part {
    /*
    Я бы заменил damage, connectionReliability и quality на тип byte, а mass на тип short
    */
    private Long id;
    private PartType type;
    private String article;
    private String name;
    private int stockPrice;
    private int quality;
    private int mass;
    private int damage;
    private int connectionReliability;
    private int reputationLevel;
    private List<String> connectivity;

    public Part(Long id, PartType type, String article, String name, int stockPrice, int quality, int mass, int damage,
                int reputationLevel, List<String> connectivity){
        this.id = id;
        this.type = type;
        this.article = article;
        this.name = name;
        this.stockPrice = stockPrice;
        this.quality = quality;
        this.mass = mass;
        this.damage = damage;
        this.connectionReliability = 0;
        this.reputationLevel = reputationLevel;
        this.connectivity = new ArrayList<>();
        this.connectivity.addAll(connectivity);
    }

    public Long getId() {
        return id;
    }
    public PartType getType(){
        return type;
    }
    public String getArticle(){
        return article;
    }
    public String getName(){
        return name;
    }
    public int getStockPrice(){
        return stockPrice;
    }
    public int getQuality(){
        return quality;
    }
    public int getMass(){
        return mass;
    }
    public int getDamage(){
        return damage;
    }
    public int getConnectionReliability(){
        return connectionReliability;
    }
    public int getReputationLevel(){
        return reputationLevel;
    }
    public List<String> getConnectivity(){
        return connectivity;
    }


    public abstract String getStringOfCharacteristics();
    /**
     * только для маркет
     * @param idNew
     * @return Copy of part with new ID
     */
    public abstract Part getCopy(Long idNew);
    public abstract int getRealPrice();
}
