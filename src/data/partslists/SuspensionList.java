package data.partslists;

import data.vehicle.Suspension;

import java.util.List;

public class SuspensionList {
    public static Suspension baseSpring = new Suspension(
            Long.MIN_VALUE,
            "susp_1",
            "Базовый набор подвески",
            0,
            25,
            25,
            30,
            2,
            List.of("whel_1_0", "whel_2_0"),
            80,
            15
    );

    public static Suspension niceSpring = new Suspension(
            Long.MIN_VALUE,
            "susp_2",
            "Продвинутый набор подвески",
            40,
            60,
            10,
            150,
            15,
            List.of("whel_2_0", "whel_3_0"),
            40,
            40);
}
