package data.crew;

public abstract class Employee {
    private final long id;
    private final JobType type;
    private final String article;
    private final String name;
    private String postfix;
    private final Integer stockFee;
    private Integer experience;
    private final Integer reputationLevel;

    public Employee(long id, JobType type, String article, String name, int stockFee,
                    int experience, int reputationLevel) {
        this(id, type, article, name, "", stockFee, experience, reputationLevel);
    }

    public Employee(long id, JobType type, String article, String name, String postfix,
                    int stockFee, int experience, int reputationLevel) {
        this.id = id;
        this.type = type;
        this.article = article;
        this.name = name;
        this.postfix = postfix;
        this.stockFee = stockFee;
        this.experience = experience;
        this.reputationLevel = reputationLevel;
    }

    public Long getId() {
        return id;
    }
    public JobType getType() {
        return type;
    }
    public String getArticle() {
        return article;
    }
    public String getName() {
        return name;
    }
    public String getPostfix() {
        return postfix;
    }
    public Integer getStockFee() {
        return stockFee;
    }
    public Integer getExperience() {
        return experience;
    }
    public int getReputationLevel() {
        return reputationLevel;
    }

    public void setPostfix(String newValue){
        if(newValue != null){
            postfix = newValue;
        }
        else{
            postfix = "";
        }
    }
    public void setExperience(int newValue){
        if(newValue < 0){
            newValue = 0;
        }
        experience = newValue;
    }

    protected abstract String getBaseCharacteristics();

    public String getEmploymentCharacteristics(){
        return getBaseCharacteristics() + "\nСтоимость найма: " + getHiringCost();
    }
    public String getDormCharacteristics(){
        return getBaseCharacteristics();
    }
    public abstract Employee getCopy(Long idNew);
    public abstract int getHiringCost();
    public abstract int getSalary(int workAmount);
}
