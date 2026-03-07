package data.partslists;

public enum PartType {
    UNDEF(
        "????",
        "====== ??? ======\nВам доступно:",
        "====== ПОДРОБНЕЕ ======"
    ),
    ENGINE(
        "engi",
        "====== ДВИГАТЕЛИ ======\nВам доступны следующие двигатели:",
        "====== ПОДРОБНЕЕ О ДВИГАТЕЛЕ ======"
    ),
    TRANSMISSION(
        "tran",
        "====== ТРАНСМИССИЯ ======\nВам доступны следующие КПП:",
        "====== ПОДРОБНЕЕ О ТРАНСМИСИИ ======"
    ),
    SUSPENSION(
        "susp",
        "====== ПОДВЕСКА ======\nВам доступны следующие подвески:",
        "====== ПОДРОБНЕЕ О ПОДВЕСКЕ ======"
    ),
    CHASSIS(
        "chas",
        "====== ШАССИ ======\nВам доступны следующие шасси:",
        "====== ПОДРОБНЕЕ О ШАССИ ======"
    ),
    DOWNFORCE(
        "dwfr",
        "====== ПРИЖИМНЫЕ ДЕТАЛИ ======\nВам доступны следующие усилители прижимной силы:",
        "====== ПОДРОБНЕЕ О ПРИЖИМНЫХ ДЕТАЛЯХ ======"
    ),
    WHEELS(
        "whel",
        "====== КОЛЁСА ======\nВам доступны следующие колёса:",
        "====== ПОДРОБНЕЕ О КОЛЁСАХ ======"
    );

    PartType(String id, String marketGroupTitle, String marketInfoTitle){
        this.id = id;
        this.marketGroupTitle = marketGroupTitle;
        this.marketInfoTitle = marketInfoTitle;
    }

    private final String id;
    private final String marketGroupTitle;
    private final String marketInfoTitle;

    public String getId(){
        return id;
    }
    public String getMarketGroupTitle(){
        return marketGroupTitle;
    }
    public String getMarketInfoTitle(){
        return marketInfoTitle;
    }
}
