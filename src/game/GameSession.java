package game;

import ui.base.MainTab;
import ui.handling.TabsHandler;
import ui.base.Tab;

public class GameSession {
    private int money;
    private int rep;
    private Warehouse warehouse;
    private Dorm dorm;
    private Garage garage;
    private final String sponsor = "ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА";

    public Warehouse warehouse(){
        return warehouse;
    }
    public Dorm dorm(){
        return dorm;
    }
    public Garage garage(){
        return garage;
    }
    public int getRep(){
        return rep;
    }
    public int getMoney(){
        return money;
    }
    public String getSponsor(){
        return sponsor;
    }

    public void addMoney(int cash){
        money += cash;
    }

    /**
     * Попытка взять деньги
     * @param cash запрос денег
     * @return true, если денег хватило. Соотв сумма изымается
     */
    public boolean takeMoney(int cash){
        if(money - cash >= 0){
            money -= cash;
            return true;
        }
        else {
            return false;
        }
    }

    public GameSession(){
        warehouse = new Warehouse();
        dorm = new Dorm();
        garage = new Garage();
    }

    public GameSession(int money, int rep){
        this();
        this.money = money;
        this.rep = rep;
    }

    public void launch(){
        TabsHandler.scheduling(new MainTab(this));
    }

    public void exit(){
        TabsHandler.AbortScheduling();
        System.exit(0);
    }
}
