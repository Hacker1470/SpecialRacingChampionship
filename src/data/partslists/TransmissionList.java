package data.partslists;

import data.vehicle.Transmission;

import java.util.List;

public class TransmissionList {
    public static Transmission chain = new Transmission(
            Long.MIN_VALUE,
            "tran_1_0",
            "Цепная передача",
            0,
            30,
            50,
            30,
            0,
            List.of("engi_1_0"),
            30,
            1
            );

    public static Transmission ladabox = new Transmission(
            Long.MIN_VALUE,
            "tran_2_0",
            "КПП ZovAuto",
            20,
            90,
            10,
            700,
            10,
            List.of("engi_2_0", "engi_3_0"),
            200,
            5);

    public static Transmission magnum = new Transmission(
            Long.MIN_VALUE,
            "tran_3_0",
            "КПП Magnum",
            50,
            27,
            5,
            14,
            27,
            List.of("engi_3_0"),
            260,
            8);
}
