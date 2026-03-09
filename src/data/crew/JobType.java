package data.crew;

public enum JobType {
    UNDEF(
            "????",
            "====== ??? ======\nВам доступно:",
            "====== ИНФОРМАЦИЯ ======"
    ),
    ENGINEER(
            "engineer",
            "====== ИНЖЕНЕРЫ ======\nДля найма доступны следующие инженеры:",
            "====== ИНФОРМАЦИЯ ОБ ИНЖЕНЕРЕ ======"
    ),
    PILOT(
            "pilot",
            "====== ПИЛОТЫ ======\nДля найма доступны следующие пилоты:",
            "====== ИНФОРМАЦИЯ О ПИЛОТЕ ======"
    );

    JobType(String id, String employGroupTitle, String employInfoTitle){
        this.id = id;
        this.employGroupTitle = employGroupTitle;
        this.employInfoTitle = employInfoTitle;
    }

    private final String id;
    private final String employGroupTitle;
    private final String employInfoTitle;

    public String getId(){
        return id;
    }
    public String getEmployGroupTitle(){
        return employGroupTitle;
    }
    public String getEmployInfoTitle(){
        return employInfoTitle;
    }
}
