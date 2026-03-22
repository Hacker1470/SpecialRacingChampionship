package ui.base;

import game.GameSession;
import ui.handling.ConsoleControl;

public abstract class Tab {
    protected GameSession gm;

    public Tab(GameSession gm){
        this.gm = gm;
    }

    /**
     * Активирует отображение текущего экземпляра меню. Используется в обработчике TabsHandler
     * @return Меню, которое должно быть открыто следующим
     */
    public abstract Tab show();

    protected void outputMain(){
        ConsoleControl.clear();
        printListOfMenus();
    }

    protected void outputWithWarn(String warn){
        ConsoleControl.clear();
        ConsoleControl.printlnString("СООБЩЕНИЕ:");
        ConsoleControl.printlnString(warn);
        ConsoleControl.printlnString("=============================================");
        printListOfMenus();
    }

    protected abstract void printListOfMenus();
}
