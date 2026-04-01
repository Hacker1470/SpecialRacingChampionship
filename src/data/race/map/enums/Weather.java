package data.race.map.enums;

public enum Weather {
    E(
            2
    );

    Weather(int koef){
        this.koef = koef;
    }
    private int koef;

    /**
     * K_погода
     * @return
     */
    public double getKoef(){
        return 1 + (koef / 100d);
    }
}
