package data.partslists;

import vehicle.Engine;
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
            "engi_2_0",
            "Двигатель ZovAuto",
            20,
            300,
            10,
            50,
            10,
            List.of("tran_2_0"),
            50,
            2000,
            12);

    public static Engine sigma = new Engine(
            "engi_3_0",
            "Двигатель Sigma",
            50,
            400,
            5,
            1000,
            28,
            List.of("tran_2_0", "tran_3_0", "chas_2_0", "chas_3_0"),
            80,
            3000,
            9);
}
