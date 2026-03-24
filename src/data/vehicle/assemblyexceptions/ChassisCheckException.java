package data.vehicle.assemblyexceptions;

public class ChassisCheckException extends RuntimeException {
    public ChassisCheckException(String message) {
        super("Выбранное шасси не позволяет собрать автомобиль\n" + message);
    }
}
