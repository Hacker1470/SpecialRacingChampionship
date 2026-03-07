package data;

import crew.Engineer;
import crew.Racer;
import ui.MainMenu;
import ui.MenuHandler;
import ui.MenuTab;
import vehicle.Racecar;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private Integer money;
    private List<Racecar> cars;
    private Warehouse warehouse;
    private Dorm dorm;
    private int rep;
    private MainMenu mm;

    public MenuTab getMainMenu(){
        return mm;
    }

    public int getRep(){
        return rep;
    }

    public GameSession(){
        cars = new ArrayList<Racecar>();
        warehouse = new Warehouse();
        dorm = new Dorm();
    }

    public GameSession(int money, int rep){
        this();
        this.money = money;
        this.rep = rep;
    }

    public void launch(){
        mm = new MainMenu(this);
        MenuHandler.scheduling(mm);
    }

    public void exit(){
        MenuHandler.AbortScheduling();
        System.exit(0);
    }
}
