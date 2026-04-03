package data.partslists;

import data.vehicle.DownforcePart;

import java.util.List;

public class DownforcePartList {
    public static DownforcePart bricks = new DownforcePart(
            Long.MIN_VALUE,
            "dwfr_1",
            "Три кирпича",
            0,
            20,
            20,
            50,
            1,
            List.of(),
            20);

    public static DownforcePart spoiler = new DownforcePart(
            Long.MIN_VALUE,
            "dwfr_2",
            "Спойлер",
            8,
            50,
            2,
            250,
            10,
            List.of(),
            40);
}
