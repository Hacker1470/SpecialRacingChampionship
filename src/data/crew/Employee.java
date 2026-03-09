package data.crew;

public abstract class Employee {
    private JobType function;
    private String id;
    private String name;
    private Integer stockFee;
    private Integer experience;
    private Integer reputationLevel;

    public Employee(JobType type, String id, String name, int fee, int experience, int reputationLevel){
        function = type;
        this.id = id;
        this.name = name;
        stockFee = fee;
        this.experience = experience;
        this.reputationLevel = reputationLevel;
    }

    public JobType getType(){
        return function;
    };
    public String getId() {
        return id;
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
    public abstract Employee getCopy();
    public abstract int getHiringCost();
}
