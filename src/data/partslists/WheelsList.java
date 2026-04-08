package data.partslists;

import data.vehicle.Wheels;

import java.util.List;

public class WheelsList {
    public static Wheels bicycleWheels = new Wheels(
            Long.MIN_VALUE,
            "whel_1_0",
            "Колёса от велосипеда",
            0,
            50,
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
            50,
            20,
            35,
            2,
            List.of(),
            40);

    public static Wheels niceWheels = new Wheels(
            Long.MIN_VALUE,
            "whel_3_0",
            "Цилиндрические колёса в каучуке",
            15,
            70,
            1,
            20,
            9,
            List.of(),
            65);
}
