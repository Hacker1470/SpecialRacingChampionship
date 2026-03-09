package ui.base;

import game.GameSession;

public abstract class MenuTab {
    protected GameSession gm;

    public MenuTab(GameSession gm){
        this.gm = gm;
    }
    public abstract MenuTab show();
}
