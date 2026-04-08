package data.race.map.enums;

public enum TerrainType {
    STRAIGHT(
            0.01d,
            0.5d,
            0.6d,
            0.5d,
            0.2d,
            0.3d
    ),
    TURN(
            0.03d,
            1.0d,
            1.0d,
            1.2d,
            0.7d,
            0.9d
    );

    TerrainType(double chassisCoef, double engineCoef, double transmissionCoef,
                double wheelsCoef, double suspensionCoef, double downforceCoef){
        this.chassisCoef = chassisCoef;
        this.engineCoef = engineCoef;
        this.transmissionCoef = transmissionCoef;
        this.wheelsCoef = wheelsCoef;
        this.suspensionCoef = suspensionCoef;
        this.downforceCoef = downforceCoef;
    }
    private final double chassisCoef;
    private final double engineCoef;
    private final double transmissionCoef;
    private final double wheelsCoef;
    private final double suspensionCoef;
    private final double downforceCoef;

    public double getChassisCoef() {
        return chassisCoef;
    }
    public double getEngineCoef() {
        return engineCoef;
    }
    public double getTransmissionCoef() {
        return transmissionCoef;
    }
    public double getWheelsCoef() {
        return wheelsCoef;
    }
    public double getSuspensionCoef() {
        return suspensionCoef;
    }
    public double getDownforceCoef() {
        return downforceCoef;
    }
}
