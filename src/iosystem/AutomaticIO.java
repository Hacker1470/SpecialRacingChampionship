package iosystem;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс, реализующий IPrints и IReads, предназначенный для автоматического ввода команд
 * в игру и считывания результатов. Ввод списка команд осуществляется через конструктор,
 * полученный вывод (с самого начала работы, а не только от последней команды)
 * возвращается через метод getOutput.
 */
public class AutomaticIO implements IPrints, IReads {

    private LinkedList<String> input;
    private LinkedList<String> output;

    public AutomaticIO(ArrayList<String> commands) {
        input = new LinkedList<>(commands);
        output = new LinkedList<>();
    }

    @Override
    public void println(String message) {
        output.add(message + '\n');
    }

    @Override
    public String readln() {
        String result;
        if (!input.isEmpty()) {
            result = input.removeFirst();
        } else {
            result = "0";
        }
        output.add(result + '\n');
        return result;
    }

    public ArrayList<String> getOutput() {
        return new ArrayList<>(output);
    }
}
