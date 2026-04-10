package iosystem;

import java.util.ArrayList;
import java.util.LinkedList;

public class AutomaticIO implements IPrints, IReads{

    private LinkedList<String> input;
    private LinkedList<String> output;

    public AutomaticIO(ArrayList<String> commands){
        input = new LinkedList<>(commands);
        output = new LinkedList<>();
    }

    @Override
    public void println(String message) {
        output.add(message);
    }

    @Override
    public String readln() {
        return input.removeFirst();
    }

    public ArrayList<String> getOutput(){
        return new ArrayList<>(output);
    }
}
