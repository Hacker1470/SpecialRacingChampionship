package data.partslists;

public enum PartType {
    ENGINE("engi"),
    TRANSMISSION("tran"),
    ANY("");


    PartType(String id){
        this.id = id;
    };
    private final String id;

    public String getId(){
        return id;
    }
}
