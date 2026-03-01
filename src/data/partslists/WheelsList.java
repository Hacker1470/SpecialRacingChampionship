package data.partslists;

import vehicle.Wheels;

import java.util.List;

public class WheelsList {
    public static Wheels bicycleWheels = new Wheels(
            "whel_1_0",
            "Колёса от велосипеда",
            0,
            10,
            50,
            30,
            1,
            List.of(),
            15);

    public static Wheels normWheels = new Wheels(
            "whel_2_0",
            "Колёса базовые КАМА",
            5,
            30,
            20,
            90,
            2,
            List.of(),
            36);

    public static Wheels niceWheels = new Wheels(
            "whel_3_0",
            "Цилиндрические колёса в каучуке",
            15,
            50,
            1,
            150,
            9,
            List.of(),
            72);
}
