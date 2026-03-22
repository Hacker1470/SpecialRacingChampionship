package ui.handling;

import ui.base.Tab;

public class TabsHandler {
    private static boolean abort = false;

    public static void AbortScheduling(){
        abort = true;
    }

    public static void scheduling(Tab tab){
        Tab current = tab;
        while (!abort){
            current = current.show();
            // раньше тут была логика, которая вызывала ошибку при передаче налл,
            // но она не работала + нельзя бросать ошибку в пустоту
        }
    }
}
