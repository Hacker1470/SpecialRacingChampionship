package ui;

public class MenuHandler {
    private static MenuTab current;
    private static boolean abort = false;

    public static void AbortScheduling(){
        abort = true;
    }

    public static void scheduling(MenuTab menuTab){
        current = menuTab;
        while (!abort){
            current = current.show();
            if(current == null){
                throw new IllegalStateException("Вы там с дуба рухнули? Какое ещё меню НАЛЛ???????????");
            }
        }
    }
}
