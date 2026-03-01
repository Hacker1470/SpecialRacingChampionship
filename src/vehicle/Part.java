package vehicle;

import data.partslists.PartType;

import java.util.ArrayList;
import java.util.List;

public abstract class Part {
    /*
    Я бы заменил damage, connectionReliability и quality на тип byte, а mass на тип short
    */
    protected String id;
    protected String name;
    protected Integer stockPrice;
    protected Integer quality;
    protected Integer mass;
    protected Integer damage;
    protected Integer connectionReliability;
    protected Integer reputationLevel;
    List<String> connectivity;
    protected PartType TYPE;

    public Part(PartType type, String id, String name, int stockPrice, int quality, int mass, int damage,
                int reputationLevel, List<String> connectivity){
        TYPE = type;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public PartType getType(){
        return TYPE;
    };
    public abstract String getStringOfCharacteristics();
    public abstract Part getCopy();
    public abstract int getRealPrice();
}
