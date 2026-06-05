package data.partslists;

import data.parts.Wheels;

import java.util.List;

public class WheelsList {
    public static Wheels bicycleWheels = new Wheels(
            Long.MIN_VALUE,
            "whel_1_0",
            "Колёса от велосипеда",
            0,
            35,
            50,
            30,
            0,
            List.of(),
            20);

    public static Wheels normWheels = new Wheels(
            Long.MIN_VALUE,
            "whel_2_0",
            "Колёса базовые КАМА",
            5,
            40,
            20,
            25,
            2,
            List.of(),
            50);

    public static Wheels niceWheels = new Wheels(
            Long.MIN_VALUE,
            "whel_3_0",
            "Цилиндрические колёса в каучуке",
            15,
            50,
            1,
            10,
            9,
            List.of(),
            85);
}
