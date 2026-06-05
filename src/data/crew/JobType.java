package data.crew;

public enum JobType {
    EMPTY(
            "====== ??? ======\nВам доступно:",
            "====== ИНФОРМАЦИЯ ======"
    ),
    ENGINEER(
            "====== ИНЖЕНЕРЫ ======\nДля найма доступны следующие инженеры:",
            "====== ИНФОРМАЦИЯ ОБ ИНЖЕНЕРЕ ======"
    ),
    PILOT(
            "====== ПИЛОТЫ ======\nДля найма доступны следующие пилоты:",
            "====== ИНФОРМАЦИЯ О ПИЛОТЕ ======"
    );

    JobType(String employGroupTitle, String employInfoTitle) {
        this.employGroupTitle = employGroupTitle;
        this.employInfoTitle = employInfoTitle;
    }

    /**
     * Надпись формата "=== РАБОТНИКИ === \n Вам доступны следующие работники"
     */
    private final String employGroupTitle;
    /**
     * Надпись формата "=== ПОДРОБНЕЕ О РАБОТНИКЕ ==="
     */
    private final String employInfoTitle;

    public String getEmployGroupTitle() {
        return employGroupTitle;
    }

    public String getEmployInfoTitle() {
        return employInfoTitle;
    }
}
