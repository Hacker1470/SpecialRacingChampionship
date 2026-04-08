package data.vehicle;

public class PartBrokeException extends Exception {
    public PartBrokeException(Part part, double oldVal, double newVal) {
        String message = "Сломалос " + part.getName() + " " + part.getPostfix() +
                "\n\tdamage " + String.format("%." + 2 + "f", oldVal) + " -> " +
                String.format("%." + 2 + "f", newVal);
        super(message);
    }
}
