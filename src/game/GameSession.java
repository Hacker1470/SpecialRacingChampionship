package game;

import ui.base.MainMenu;
import ui.service.MenuHandler;
import ui.base.MenuTab;
import data.vehicle.Racecar;

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

    public Warehouse warehouse(){
        return warehouse;
    }
    public Dorm dorm(){
        return dorm;
    }

    public int getRep(){
        return rep;
    }

    public int getMoney(){
        return money;
    }

    public void addMoney(int cash){
        money += cash;
    }

    public void takeMoney(int cash){
        money -= cash;
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
