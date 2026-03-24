package data.vehicle;

public enum PartType {
    UNDEF(
        "====== ??? ======\nВам доступно:",
        "====== ПОДРОБНЕЕ ======"
    ),
    ENGINE(
        "====== ДВИГАТЕЛИ ======\nВам доступны следующие двигатели:",
        "====== ПОДРОБНЕЕ О ДВИГАТЕЛЕ ======"
    ),
    TRANSMISSION(
        "====== ТРАНСМИССИЯ ======\nВам доступны следующие КПП:",
        "====== ПОДРОБНЕЕ О ТРАНСМИСИИ ======"
    ),
    SUSPENSION(
        "====== ПОДВЕСКА ======\nВам доступны следующие подвески:",
        "====== ПОДРОБНЕЕ О ПОДВЕСКЕ ======"
    ),
    CHASSIS(
        "====== ШАССИ ======\nВам доступны следующие шасси:",
        "====== ПОДРОБНЕЕ О ШАССИ ======"
    ),
    DOWNFORCE(
        "====== ПРИЖИМНЫЕ ДЕТАЛИ ======\nВам доступны следующие усилители прижимной силы:",
        "====== ПОДРОБНЕЕ О ПРИЖИМНЫХ ДЕТАЛЯХ ======"
    ),
    WHEELS(
        "====== КОЛЁСА ======\nВам доступны следующие колёса:",
        "====== ПОДРОБНЕЕ О КОЛЁСАХ ======"
    );

    PartType(String marketGroupTitle, String marketInfoTitle){
        this.marketGroupTitle = marketGroupTitle;
        this.marketInfoTitle = marketInfoTitle;
    }

    /**
     * Вывод формата "==== ДЕТАЛЬ ==== \n Вам доступны следующие детали"
     */
    private final String marketGroupTitle;
    /**
     * Вывод формата "==== ПОДРОБНЕЕ О ДЕТАЛИ ===="
     */
    private final String marketInfoTitle;

    public String getMarketGroupTitle(){
        return marketGroupTitle;
    }
    public String getMarketInfoTitle(){
        return marketInfoTitle;
    }
}
