package data.partslists;

import vehicle.Chassis;

import java.util.List;

public class ChassisList {

    public static Chassis woodenBox = new Chassis(
            "chas_1_0",
            "Деревянный ящик",
            0,
            100,
            25,
            100,
            0,
            List.of("engi_1_0", "tran_1_0"),
            2,
            300,
            0);

    public static Chassis ladaTazik = new Chassis(
            "chas_2_0",
            "База ZovAuto",
            15,
            400,
            20,
            300,
            7,
            List.of("engi_1_0", "engi_2_0", "tran_1_0", "tran_2_0"),
            15,
            1200,
            40);

    public static Chassis straus = new Chassis(
            "chas_3_0",
            "Базис Straus",
            50,
            100,
            0,
            2000,
            50,
            List.of("engi_3_0", "tran_3_0"),
            42,
            2300,
            105);
}
