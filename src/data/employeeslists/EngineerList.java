package data.employeeslists;

import data.crew.Engineer;
import data.vehicle.DownforcePart;

import java.util.List;

public class EngineerList {
    public static Engineer maslyonok = new Engineer(
        Long.MIN_VALUE,
        "engineer_1_0",
        "Чел с путяги",
        10,
        0,
        0,
        90,
        30,
        30
    );

    public static Engineer pazhiloypauk = new Engineer(
        Long.MIN_VALUE,
        "engineer_2_0",
        "Пожилой дедок",
        40,
        10,
        10,
        30,
        80,
        50
    );
}
