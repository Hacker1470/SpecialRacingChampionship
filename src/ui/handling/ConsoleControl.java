package ui.handling;

public class ConsoleControl {
    public static void clear(){
        IO.println("");
        IO.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        IO.println("");
    }

    public static String getString(){
        return IO.readln();
    }

    public static void printString(String s){
        IO.print(s);
    }
    public static void printlnString(String s){
        IO.println(s);
    }

    //на удаление 22 03 2026
    //public static int getInteger(){
    //    return Integer.parseInt(IO.readln());
    //}
}
