package data.partslists;

import vehicle.Engine;

import java.util.HashMap;
import java.util.List;

/*
* Список движков
* 1 - педальный. Уровень репутации 0. Потребление топлива - 0. Мощность - 0
* 2 - жигулёвский
* 3 - ламбарджамбер
* */

public class EnginesList {

    public static Engine pedal = new Engine(
            "engi_1_0",
            "Велосипедные педали",
            0,
            5,
            50,
            10,
            0,
            List.of("tran_1_0"),
            1,
            60,
            0);

    public static Engine lada = new Engine(
            "engi2_0",
            "Двигатель ZovAuto",
            20,
            300,
            10,
            500,
            10,
            List.of("tran_1_1", "tran_2_2"),
            50,
            2000,
            12);

    public static Engine sigma = new Engine(
            "engi3_0",
            "Двигатель Sigma",
            50,
            400,
            5,
            1000,
            28,
            List.of("tran_1_1", "tran_2_2", "chas2_2", "chas3_1"),
            80,
            3000,
            9);

    public static HashMap<String, Engine> listOfEngines = new HashMap<>();

    public static void initializeEngines(){
        listOfEngines.put(pedal.getId(), pedal);
        listOfEngines.put(lada.getId(), lada);
        listOfEngines.put(sigma.getId(), sigma);
    }

    public static HashMap<Integer, Engine> getAvailableEnginesByReputation(int rep){
        HashMap<Integer, Engine> availableEngines = new HashMap<>();
        int counter = 0;
        for (Engine i : EnginesList.listOfEngines.values()){
            if(i.getReputationLevel() <= rep){
                availableEngines.put(++counter, i);
            }
        }
        return availableEngines;
    }
}
