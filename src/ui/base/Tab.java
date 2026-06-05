package ui.base;

import game.GameSession;

public abstract class Tab {
    protected GameSession gm;

    public Tab(GameSession gm) {
        this.gm = gm;
    }

    /**
     * Активирует отображение текущего экземпляра меню. Используется в обработчике TabsHandler
     *
     * @return Меню, которое должно быть открыто следующим
     */
    public abstract Tab show();

    protected void outputMain() {
        gm.io().clear();
        printListOfMenus();
    }

    protected void outputWithWarn(String warn) {
        gm.io().clear();
        gm.io().printlnString("СООБЩЕНИЕ:");
        gm.io().printlnString(warn);
        gm.io().printlnString("=============================================");
        printListOfMenus();
    }

    protected abstract void printListOfMenus();
}
