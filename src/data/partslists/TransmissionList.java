package data.partslists;

import vehicle.Engine;
import vehicle.Transmission;

import java.util.HashMap;
import java.util.List;

public class TransmissionList {
    public static Transmission chain = new Transmission(
            "tran_1_0",
            "Цепная передача",
            0,
            10,
            50,
            30,
            0,
            List.of("engi_1_0"),
            15,
            1
            );

    public static Transmission ladabox = new Transmission(
            "tran_2_0",
            "КПП ZovAuto",
            20,
            200,
            10,
            700,
            10,
            List.of("engi_2_0", "engi_3_0"),
            90,
            5);

    public static Transmission magnum = new Transmission(
            "tran_3_0",
            "КПП Magnum",
            50,
            320,
            5,
            14,
            27,
            List.of("engi_3_0"),
            125,
            8);
}
