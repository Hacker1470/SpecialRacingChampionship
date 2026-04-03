package data.partslists;

import data.vehicle.Engine;
import java.util.List;

/*
* Список движков
* 1 - педальный. Уровень репутации 0. Потребление топлива - 0. Мощность - 0
* 2 - жигулёвский
* 3 - ламбарджамбер
* */

public class EnginesList {

    public static Engine pedal = new Engine(
            Long.MIN_VALUE,
            "engi_1_0",
            "Велосипедные педали",
            0,
            10,
            1,
            10,
            0,
            List.of("tran_1_0"),
            5,
            500);

    public static Engine lada = new Engine(
            Long.MIN_VALUE,
            "engi_2_0",
            "Двигатель ZovAuto",
            20,
            50,
            50,
            50,
            10,
            List.of("tran_2_0"),
            300,
            2000);

    public static Engine sigma = new Engine(
            Long.MIN_VALUE,
            "engi_3_0",
            "Двигатель Sigma",
            50,
            80,
            80,
            1000,
            28,
            List.of("tran_2_0", "tran_3_0", "chas_2_0", "chas_3_0"),
            400,
            5000);
}
