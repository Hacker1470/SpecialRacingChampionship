package data.crew;

public class Pilot extends Employee {
    private final Integer offroadDriving;
    private final Integer pedaling;
    private final Integer steering;

    public int getPedaling() {
        return pedaling;
    }

    public int getSteering() {
        return steering;
    }

    public int getOffroadDriving() {
        return offroadDriving;
    }

    public Pilot(long id, String article, String name,
                 int stockFee, int experience, int reputationLevel,
                 int offroadDriving, int pedaling, int steering) {
        this(id, article, name, "", stockFee, experience,
                reputationLevel, offroadDriving, pedaling, steering);
    }

    public Pilot(long id, String article, String name, String postfix,
                 int stockFee, int experience, int reputationLevel,
                 int offroadDriving, int pedaling, int steering) {
        super(id, JobType.PILOT, article, name, postfix,
                stockFee, experience, reputationLevel);

        this.offroadDriving = offroadDriving;
        this.pedaling = pedaling;
        this.steering = steering;
    }

    @Override
    public String getBaseCharacteristics() {
        StringBuilder sb = new StringBuilder(1000);

        sb.append("Имя: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Опыт работы: ").append(getExperience()).append("\n");
        sb.append("Руление: ").append(steering).append(" %").append("\n");
        sb.append("Педалирование: ").append(pedaling).append(" %").append("\n");
        sb.append("Мин ЗПшка: ").append(getStockFee()).append(" грошей\n");

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
                offroadDriving,
                pedaling,
                steering
        );
    }

    @Override
    public int getHiringCost() {
        return (int) (getStockFee() * (1 + getExperience() / 10d + offroadDriving * pedaling * steering / 1000000d));
    }

    @Override
    public int getSalary(int awardTotal) {
        return (int) (awardTotal * 0.25d);
    }
}
