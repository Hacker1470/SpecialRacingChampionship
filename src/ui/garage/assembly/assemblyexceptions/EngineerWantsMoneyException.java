package ui.garage.assembly.assemblyexceptions;

public class EngineerWantsMoneyException extends Exception {
    public EngineerWantsMoneyException(int money) {
        super("Не хватает денег на оплату труда инженера." +
                "\nОн хочет " + money + " грошей");
    }
}
