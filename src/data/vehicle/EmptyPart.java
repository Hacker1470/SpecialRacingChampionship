package data.vehicle;

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
}
