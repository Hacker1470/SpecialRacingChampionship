package ui;

import data.GameSession;

public abstract class MenuTab {
    protected GameSession gm;

    public MenuTab(GameSession gm){
        this.gm = gm;
    }
    public abstract MenuTab show();
}
