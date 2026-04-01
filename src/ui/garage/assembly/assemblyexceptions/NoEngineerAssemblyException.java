package ui.garage.assembly.assemblyexceptions;

public class NoEngineerAssemblyException extends Exception {
    public NoEngineerAssemblyException() {
        super("Не назначен инженер");
    }
}
