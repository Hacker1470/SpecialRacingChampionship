package data.vehicle;

import java.util.List;

public class UndefinedPart extends Part{
    public UndefinedPart() {
        super(Long.MIN_VALUE, PartType.UNDEF, "????", "???", 0, 0, 0, 0, 0, List.of(""));
    }

    @Override
    public String getStringOfCharacteristics() {
        return "???";
    }

    @Override
    public Part getCopy(Long idNew) {
        return null;
    }

    @Override
    public int getRealPrice() {
        return 0;
    }
}
