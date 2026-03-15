package data.crew;

public abstract class Employee {
    private long id;
    private JobType function;
    private String article;
    private String name;
    private Integer stockFee;
    private Integer experience;
    private Integer reputationLevel;

    public Employee(long id, JobType type, String article, String name, int fee, int experience, int reputationLevel){
        this.id = id;
        function = type;
        this.article = article;
        this.name = name;
        stockFee = fee;
        this.experience = experience;
        this.reputationLevel = reputationLevel;
    }

    public Long getId(){
        return id;
    }
    public JobType getType(){
        return function;
    };
    public String getArticle() {
        return article;
    }
    public String getName(){
        return name;
    }
    public Integer getStockFee(){
        return stockFee;
    }
    public Integer getExperience(){
        return experience;
    }
    public int getReputationLevel(){
        return reputationLevel;
    }

    public abstract String getStringOfCharacteristics();
    public abstract Employee getCopy(Long idNew);
    public abstract int getHiringCost();
}
