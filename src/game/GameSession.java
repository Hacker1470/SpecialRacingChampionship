package game;

import data.crew.*;
import data.employeeslists.*;
import data.parts.Chassis;
import data.parts.Engine;
import data.parts.Transmission;
import data.parts.Wheels;
import data.partslists.*;
import data.special.ArchiveRecord;
import data.racecar.*;
import iosystem.IOControl;
import ui.base.MainTab;
import ui.TabsHandler;

import java.util.ArrayList;

public class GameSession {
    private final String sponsor = "ЗДЕСЬ МОГЛА БЫТЬ ВАША РЕКЛАМА";

    private int money;
    private int rep;
    private GameModeNorris gameModeNorris;

    private Warehouse warehouse;
    private Dorm dorm;
    private Garage garage;
    private Hospital hospital = null;
    private ArrayList<ArchiveRecord> archive;

    private IOControl io;

    public GameSession(IOControl io) {
        this.io = io;
        warehouse = new Warehouse();
        dorm = new Dorm();
        garage = new Garage();
        archive = new ArrayList<>();
        gameModeNorris = GameModeNorris.NORMAL;
    }

    public GameSession(IOControl io, int money, int rep) {
        this(io);
        this.money = money;
        this.rep = rep;

        Pilot p = (Pilot) PilotList.cheboks.getCopy(1L);
        Engineer er = (Engineer) EngineerList.maslyonok.getCopy(2L);
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
                c, ee, t, w, null, null
        ));
    }

    // Геттеры ===================================================

    public String getSponsor() {
        return sponsor;
    }

    public int getRep() {
        return rep;
    }

    public int getMoney() {
        return money;
    }

    public GameModeNorris getGameModeNorris() {
        return gameModeNorris;
    }


    public Warehouse warehouse() {
        return warehouse;
    }

    public Dorm dorm() {
        return dorm;
    }

    public Garage garage() {
        return garage;
    }

    public Hospital hospital() {
        return hospital;
    }

    public ArrayList<ArchiveRecord> archive() {
        return new ArrayList<>(archive);
    }


    public IOControl io() {
        return io;
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



    public void setGameMode(GameModeNorris gameModeNorris) {
        this.gameModeNorris = gameModeNorris;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void addRecordToArchive(ArchiveRecord ar) {
        archive.add(ar);
    }

    public void changeRep(int delta) {
        if (rep + delta < 0) {
            delta = 0;
        }
        rep += delta;
    }

    public void addMoney(int cash) {
        money += Math.abs(cash);
    }

    /**
     * Попытка взять деньги
     *
     * @param cash запрос денег
     * @return true, если денег хватило. Соотв сумма изымается
     */
    public boolean takeMoney(int cash) {
        if (money - cash >= 0) {
            money -= cash;
            return true;
        } else {
            return false;
        }
    }

    public void launch() {
        TabsHandler.scheduling(new MainTab(this));
    }

    public void exit() {
        System.exit(0);
    }
}
