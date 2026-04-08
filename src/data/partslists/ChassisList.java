package data.partslists;

import data.vehicle.Chassis;

import java.util.List;

public class ChassisList {

    public static Chassis woodenBox = new Chassis(
            Long.MIN_VALUE,
            "chas_1_0",
            "Деревянный ящик",
            0,
            25,
            25,
            40,
            0,
            List.of("engi_1_0", "tran_1_0"),
            1,
            300);

    public static Chassis ladaTazik = new Chassis(
            Long.MIN_VALUE,
            "chas_2_0",
            "База ZovAuto",
            15,
            40,
            300,
            30,
            7,
            List.of("engi_1_0", "engi_2_0", "tran_1_0", "tran_2_0"),
            20,
            1200);

    public static Chassis straus = new Chassis(
            Long.MIN_VALUE,
            "chas_3_0",
            "Базис Straus",
            50,
            60,
            250,
            2,
            50,
            List.of("engi_3_0", "tran_3_0"),
            42,
            2300);
}
