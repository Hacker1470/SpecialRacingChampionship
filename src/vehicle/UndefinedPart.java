package vehicle;

import data.partslists.PartType;

import java.util.List;

public class UndefinedPart extends Part{
    public UndefinedPart() {
        super(PartType.UNDEF, "????", "???", 0, 0, 0, 0, 0, List.of(""));
    }

    @Override
    public String getStringOfCharacteristics() {
        return "???";
    }

    @Override
    public Part getCopy() {
        return null;
    }

    @Override
    public int getRealPrice() {
        return 0;
    }
}
