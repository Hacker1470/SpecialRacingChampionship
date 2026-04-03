package data.vehicle;

public class PartBrokeException extends Exception {
    public PartBrokeException(String message, Part part) {
        super(message);
    }
}
