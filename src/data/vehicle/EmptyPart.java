package data.vehicle;

import data.crew.Pilot;
import data.race.map.MapTerrain;
import data.vehicle.enums.PartType;

import java.util.List;

public class EmptyPart extends Part{
    public EmptyPart() {
        super(Long.MIN_VALUE, PartType.UNDEF, "????", "???", 0, 0, 0, 0, 0, List.of(""));
    }

    @Override
    public String getStringOfCharacteristics() {
        return "???";
    }

    @Override
    public Part getCopy(Long idNew) {
        return new EmptyPart();
    }

    @Override
    public int getRealPrice() {
        return 0;
    }

    @Override
    public double getBaseDamage(double coefficient, MapTerrain terrain, Racecar racecar, Pilot pilot) {
        return 0;
    }
}
