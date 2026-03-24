package data.crew;

public class Pilot extends Employee{
    private Integer accuracy;
    private Integer offroadDriving;
    private Integer pedaling;
    private Integer steering;


    public Pilot(long id, String article, String name, int fee, int experience, int reputationLevel, int accuracy,
                 int offroadDriving, int pedaling, int steering) {
        super(id, JobType.PILOT, article, name, fee, experience, reputationLevel);

        this.accuracy = accuracy;
        this.offroadDriving = offroadDriving;
        this.pedaling = pedaling;
        this.steering = steering;
    }

    @Override
    public String getStringOfCharacteristics() {
        StringBuilder sb = new StringBuilder(2000);

        sb.append("Имя: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Опыт работы: ").append(getExperience()).append("\n");
        sb.append("Аккуратность: ").append(accuracy).append(" %").append("\n");
        sb.append("Руление: ").append(steering).append(" %").append("\n");
        sb.append("Педалирование: ").append(pedaling).append(" %").append("\n");
        sb.append("Мин ЗПшка: ").append(getStockFee()).append(" грошей").append("\n");
        sb.append("\n");
        sb.append("Стоимость найма: ").append(getHiringCost());

        return sb.toString();
    }

    @Override
    public Employee getCopy(Long idNew) {
        return new Pilot(
                idNew,
                getArticle(),
                getName(),
                getStockFee(),
                getExperience(),
                getReputationLevel(),
                accuracy,
                offroadDriving,
                pedaling,
                steering
        );
    }

    @Override
    public int getHiringCost() {
        return getStockFee() * (1 + getExperience()/10 + accuracy * offroadDriving * pedaling * steering / 1000);
    }
}
