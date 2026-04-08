package game;

import data.crew.*;
import data.employeeslists.*;
import data.partslists.*;
import data.race.ArchiveRecord;
import data.vehicle.*;
import ui.base.MainTab;
import ui.handling.TabsHandler;

import java.util.ArrayList;

public class GameSession {
    private int money;
    private int rep;
    private Warehouse warehouse;
    private Dorm dorm;
    private Garage garage;
    private final String sponsor = "ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА";
    private ArrayList<ArchiveRecord> archive;
    private GameMode gameMode;
    private Hospital hospital = null;

    public GameMode getGameMode() {
        return gameMode;
    }
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

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

    public Hospital getHospital() {
        return hospital;
    }
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void addRecordToArchive(ArchiveRecord ar){
        archive.add(ar);
    }
    public ArrayList<ArchiveRecord> getArchive(){
        return new ArrayList<>(archive);
    }

    public void changeRep(int delta){
        if(rep + delta < 0){
            delta = 0;
        }
        rep += delta;
    }
    public void addMoney(int cash){
        money += Math.abs(cash);
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
        archive = new ArrayList<>();
        gameMode = GameMode.NORMAL;
    }

    public GameSession(int money, int rep){
        this();
        this.money = money;
        this.rep = rep;

        Pilot p = (Pilot)PilotList.cheboks.getCopy(1L);
        Engineer er = (Engineer)EngineerList.maslyonok.getCopy(2L);
        Chassis c = (Chassis) ChassisList.woodenBox.getCopy(1L);
        Engine ee = (Engine) EnginesList.pedal.getCopy(2L);
        Transmission t = (Transmission) TransmissionList.chain.getCopy(3L);
        Wheels w = (Wheels) WheelsList.bicycleWheels.getCopy(4L);
        c.setConnectionReliability(55);
        ee.setConnectionReliability(55);
        t.setConnectionReliability(55);
        w.setConnectionReliability(55);
        dorm.put(p);
        dorm.put(er);
        garage.put(new Racecar(
                1, "42",
                c, ee, t, null, null, w
        ));
    }

    public void launch(){
        TabsHandler.scheduling(new MainTab(this));
    }

    public void exit(){
        TabsHandler.AbortScheduling();
        System.exit(0);
    }
}
