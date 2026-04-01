package ui.garage.assembly;

import data.crew.Engineer;
import data.vehicle.*;
import ui.garage.assembly.assemblyexceptions.NoEngineerAssemblyException;
import ui.garage.assembly.assemblyexceptions.NoPartAssemblyException;
import ui.garage.assembly.assemblyexceptions.UnmatchingPartsAssemblyException;

import java.util.ArrayList;
import java.util.List;

public class RacecarSample extends Racecar {

    private Engineer engineer;

    public RacecarSample() {
        super(Long.MIN_VALUE, "none", null, null, null,
                null, null, null);
        engineer = null;
    }

    public void setChassis(Chassis chassis){
        this.chassis = chassis;
    }
    public void setEngine(Engine engine){
        this.engine = engine;
    }
    public void setTransmission(Transmission transmission){
        this.transmission = transmission;
    }
    public void setDownforcePart(DownforcePart downforcePart){
        this.downforcePart = downforcePart;
    }
    public void setSuspension(Suspension suspension){
        this.suspension = suspension;
    }
    public void setWheels(Wheels wheels){
        this.wheels = wheels;
    }

    public Engineer getEngineer(){
        return engineer;
    }
    public void setEngineer(Engineer engineer){
        this.engineer = engineer;
    }
}
