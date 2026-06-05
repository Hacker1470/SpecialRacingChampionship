package data.crew;

public class Engineer extends Employee {
    private Integer wavyHands;
    private Integer screwing;

    public Engineer(long id, String article, String name,
                    int stockFee, int experience, int reputationLevel,
                    int wavyHands, int screwing) {
        this(id, article, name, "", stockFee, experience,
                reputationLevel, wavyHands, screwing);
    }

    public Engineer(long id, String article, String name, String postfix,
                    int stockFee, int experience, int reputationLevel,
                    int wavyHands, int screwing) {
        super(id, JobType.ENGINEER, article, name, postfix,
                stockFee, experience, reputationLevel);

        this.wavyHands = wavyHands;
        this.screwing = screwing;
    }

    @Override
    public String getBaseCharacteristics() {
        StringBuilder sb = new StringBuilder(1000);

        sb.append("Имя: ").append(getName()).append(" ").append(getPostfix()).append("\n");
        sb.append("Опыт работы: ").append(getExperience()).append("\n");
        sb.append("Криворукость: ").append(wavyHands).append(" %").append("\n");
        sb.append("БОЛТология: ").append(screwing).append(" %").append("\n");
        sb.append("Мин ЗПшка: ").append(getStockFee()).append(" грошей\n");

        return sb.toString();
    }

    public int getWavyHands() {
        return wavyHands;
    }

    public int getScrewing() {
        return screwing;
    }

    @Override
    public Employee getCopy(Long idNew) {
        return new Engineer(
                idNew,
                getArticle(),
                getName(),
                getPostfix(),
                getStockFee(),
                getExperience(),
                getReputationLevel(),
                wavyHands,
                screwing
        );
    }

    @Override
    public int getHiringCost() {
        return (int) (getStockFee() * (1 + getExperience() / 100d + wavyHands * screwing / 10000d));
    }

    @Override
    public int getSalary(int carsAssembled) {
        return (int) (getStockFee() * carsAssembled * (1 + getExperience() / 20d));
    }
}
