package iosystem;

/*
* Тупой класс, что реализует IPrints и IReads. Всё что выводится - идёт в консоль.
* Всё что читается - берётся с консоли.
* */
public class ConsoleIO implements IPrints, IReads {
    public ConsoleIO() {
    }

    @Override
    public void println(String message) {
        IO.println(message);
    }

    @Override
    public String readln() {
        return IO.readln();
    }
}
