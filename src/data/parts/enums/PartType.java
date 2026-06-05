package data.parts.enums;

public enum PartType {
    UNDEF(
            "Не назначено",
            "====== ??? ======\nВам доступно:",
            "====== ПОДРОБНЕЕ ======"
    ),
    ENGINE(
            "Двигатель",
            "====== ДВИГАТЕЛИ ======\nВам доступны следующие двигатели:",
            "====== ПОДРОБНЕЕ О ДВИГАТЕЛЕ ======"
    ),
    TRANSMISSION(
            "Трансмиссия",
            "====== ТРАНСМИССИЯ ======\nВам доступны следующие КПП:",
            "====== ПОДРОБНЕЕ О ТРАНСМИСИИ ======"
    ),
    SUSPENSION(
            "Подвеска",
            "====== ПОДВЕСКА ======\nВам доступны следующие подвески:",
            "====== ПОДРОБНЕЕ О ПОДВЕСКЕ ======"
    ),
    CHASSIS(
            "Шасси",
            "====== ШАССИ ======\nВам доступны следующие шасси:",
            "====== ПОДРОБНЕЕ О ШАССИ ======"
    ),
    DOWNFORCE(
            "Прижимная деталь",
            "====== ПРИЖИМНЫЕ ДЕТАЛИ ======\nВам доступны следующие усилители прижимной силы:",
            "====== ПОДРОБНЕЕ О ПРИЖИМНЫХ ДЕТАЛЯХ ======"
    ),
    WHEELS(
            "Колёса",
            "====== КОЛЁСА ======\nВам доступны следующие колёса:",
            "====== ПОДРОБНЕЕ О КОЛЁСАХ ======"
    );

    PartType(String simpleName, String marketGroupTitle, String marketInfoTitle) {
        this.simpleName = simpleName;
        this.marketGroupTitle = marketGroupTitle;
        this.marketInfoTitle = marketInfoTitle;
    }

    /**
     * Вывод формата "Деталь"
     */
    private final String simpleName;

    /**
     * Вывод формата "==== ДЕТАЛЬ ==== \n Вам доступны следующие детали"
     */
    private final String marketGroupTitle;

    /**
     * Вывод формата "==== ПОДРОБНЕЕ О ДЕТАЛИ ===="
     */
    private final String marketInfoTitle;

    public String getSimpleName() {
        return simpleName;
    }

    public String getMarketGroupTitle() {
        return marketGroupTitle;
    }

    public String getMarketInfoTitle() {
        return marketInfoTitle;
    }
}
