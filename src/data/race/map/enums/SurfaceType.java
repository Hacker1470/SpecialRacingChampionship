package data.race.map.enums;

public enum SurfaceType {
    ASPHALT(
            100,
            "Асфальт"
    ),
    GRAVEL(
            50,
            "Гравий"
    ),
    FIELD(
            20,
            "Мокрая грунтовка"
    );

    SurfaceType(int koef, String name) {
        this.koef = koef;
        this.name = name;
    }

    private final int koef;
    private final String name;

    /**
     * К_поверхность
     *
     * @return
     */
    public double getCoefficient() {
        return 0.5 + (koef / 200d);
    }

    public String getName() {
        return name;
    }
}
