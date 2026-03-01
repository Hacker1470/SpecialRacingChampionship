package ui;

public class ConsoleControl {
    public static void clear(){
        IO.println("");
        IO.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        IO.println("");
    }

    public static String getString(){
        return IO.readln();
    }

    public static int getInteger(){
        return Integer.parseInt(IO.readln());
    }

    public static void printlnString(String s){
        IO.println(s);
    }
}
