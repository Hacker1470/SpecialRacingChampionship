package data.race.map.enums;

public enum Surface {
    E(
        2
    );

    Surface(int koef){
        this.koef = koef;
    }
    private int koef;

    /**
     * К_поверхность
     * @return
     */
    public double getKoef(){
        return 1 + (koef/100d);
    }
}
