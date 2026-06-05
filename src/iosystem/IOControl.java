package iosystem;

/**
 * Система ввода/вывода игры. Она предоставляет возможность получать строку и
 * выводить в строку оттуда и туда, что было заявлено в конструкторе.
 * Всё что надо конструктору - нечто, что реализует интерфейсы
 * IPrints и IReads, которые содержат void print(string) и string read()
 */
public class IOControl {

    private final IPrints output;
    private final IReads input;

    public IOControl(IPrints printer, IReads reader) {
        output = printer;
        input = reader;
    }

    public void clear() {
        output.println("");
        output.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        output.println("");
    }

    public String getString() {
        return input.readln();
    }

    public void printlnString(String s) {
        output.println(s);
    }
}
