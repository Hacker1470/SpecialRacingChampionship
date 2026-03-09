package data.crew;

public class Engineer extends Employee{
    private Integer wawyHands;
    private Integer screwing;
    private Integer maintance;

    public Engineer(String id, String name, int fee, int experience, int reputationLevel, int wawyHands,
                    int screwing, int maintance) {
        super(JobType.ENGINEER, id, name, fee, experience, reputationLevel);

        this.wawyHands = wawyHands;
        this.screwing = screwing;
        this.maintance = maintance;
    }

    @Override
    public String getStringOfCharacteristics() {
        StringBuilder sb = new StringBuilder(1000);

        sb.append("Имя: ").append(getName()).append("\n");
        sb.append("Опыт работы: ").append(getExperience()).append("\n");
        sb.append("Криворукость: ").append(wawyHands).append(" %").append("\n");
        sb.append("БОЛТология: ").append(screwing).append(" %").append("\n");
        sb.append("Качество обслуживания: ").append(maintance).append(" %").append("\n");
        sb.append("Мин ЗПшка: ").append(getStockFee()).append(" грошей").append("\n");
        sb.append("\n");
        sb.append("Стоимость найма: ").append(getHiringCost());

        return sb.toString();
    }

    @Override
    public Employee getCopy() {
        return new Engineer(
                getId(),
                getName(),
                getStockFee(),
                getExperience(),
                getReputationLevel(),
                wawyHands,
                screwing,
                maintance
        );
    }

    @Override
    public int getHiringCost() {
        return getStockFee() * (1 + getExperience()/10 + wawyHands * screwing * maintance / 1000);
    }
}
