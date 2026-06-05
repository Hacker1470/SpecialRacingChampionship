package ui;

import ui.base.Tab;

public class TabsHandler {

    /**
     * Бесконечная обработка смены вкладок
     * @param tab
     */
    public static void scheduling(Tab tab) {
        Tab current = tab;
        while (true) {
            current = current.show();
            // раньше тут была логика, которая вызывала ошибку при передаче налл,
            // но она не работала + нельзя бросать ошибку в пустоту
        }
    }

    /**
     * Ограниченная обработка смены вкладок
     * @param tab
     * @param iterations = числу команд, вводимых автоматом
     */
    public static void scheduling(Tab tab, int iterations) {
        Tab current = tab;
        for(int i = 0; i < iterations; i++){
            current = current.show();
        }
    }
}
