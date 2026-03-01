package data.partslists;

import vehicle.Suspension;

import java.util.List;

public class SuspensionList {
    public static Suspension baseSpring = new Suspension(
            "susp_1",
            "Базовый набор подвески",
            0,
            8,
            25,
            30,
            2,
            List.of("whel_1_0", "whel_2_0"),
            80,
            30
    );

    public static Suspension niceSpring = new Suspension(
            "susp_2",
            "Продвинутый набор подвески",
            40,
            80,
            10,
            150,
            15,
            List.of("whel_2_0", "whel_3_0"),
            40,
            75);
}
