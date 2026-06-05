package data.race.map.enums;

public enum WeatherType {
    SUNNY(
            100,
            "Солнечно"
    ),
    CLOUDY(
            80,
            "Пасмурно"
    ),
    RAINING(
            40,
            "Дождь"
    );

    WeatherType(int koef, String name) {
        this.koef = koef;
        this.name = name;
    }

    private final int koef;
    private final String name;

    /**
     * K_погода
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
