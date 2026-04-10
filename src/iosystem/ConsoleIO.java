package iosystem;

public class ConsoleIO implements IPrints, IReads{
    public ConsoleIO(){};

    @Override
    public void println(String message) {
        IO.println(message);
    }

    @Override
    public String readln() {
        return IO.readln();
    }
}
