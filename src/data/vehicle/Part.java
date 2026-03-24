package data.vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class Part {
    /*
    Я бы заменил damage, connectionReliability и quality на тип byte, а mass на тип short
    */

    /**
     * Уникальный идентификатор, используемый в логике игры для дифференцирования деталей
     */
    private final Long id;

    /**
     * Тип детали
     */
    private final PartType type;

    /**
     * Уникальное короткое имя, обозначающее принадлежность детали к своему виду
     */
    private final String article;

    /**
     * Собственное имя детали
     */
    private final String name;

    /**
     * Приписка после основного имени, применяющаяся для дифференцирования детали
     * на уровне восприятия интерфейса игроком
     */
    private String postfix;

    /**
     * Базовая цена, от которой с помощи множителей формируется реальная цена
     */
    private final int stockPrice;

    private final int quality;
    private final int mass;
    private int damage;

    /**
     * Качество соединения. Зависит от мастерства инженера
     */
    private int connectionReliability;

    /**
     * Уровень репутации, начиная с которого деталь может появиться в магазине
     */
    private final int reputationLevel;

    /**
     * Список из строк формата article для выявления совместимости с другими деталями
     */
    private final List<String> connectivity;

    public Part(Long id, PartType type, String article, String name, int stockPrice, int quality, int mass, int damage,
                int reputationLevel, List<String> connectivity){
        this.id = id;
        this.type = type;
        this.article = article;
        this.name = name;
        this.postfix = "";
        this.stockPrice = stockPrice;
        this.quality = quality;
        this.mass = mass;
        this.damage = damage;
        this.connectionReliability = 0;
        this.reputationLevel = reputationLevel;
        this.connectivity = new ArrayList<>();
        this.connectivity.addAll(connectivity);
    }

    public Part(Long id, PartType type, String article, String name, String postfix, int stockPrice, int quality, int mass, int damage,
                int reputationLevel, List<String> connectivity){
        this(id, type, article, name, stockPrice, quality, mass, damage, reputationLevel,connectivity);
        this.postfix = postfix;
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
    public String getPostfix(){
        return postfix;
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

    public void setPostfix(String newValue){
        postfix = newValue;
    }
    public void setDamage(int newValue){
        damage = newValue;
    }
    public void setConnectionReliability(int newValue){
        connectionReliability = newValue;
    }

    public abstract String getStringOfCharacteristics();
    /**
     * Применяется для копирования детали из каталога на склад игрока
     * @param idNew
     * @return Копия детали с новым id = idNew
     */
    public abstract Part getCopy(Long idNew);
    public abstract int getRealPrice();
}
