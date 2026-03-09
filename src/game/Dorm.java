package game;

import data.crew.Employee;
import data.crew.JobType;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dorm {
    LinkedList<Employee> obshchaga;

    public Dorm(){
        obshchaga = new LinkedList<>();
    }

    public void put(Employee newEmployee){
        obshchaga.push(newEmployee);
    }

    public ArrayList<Employee> getParts(JobType type){
        return new ArrayList<>(obshchaga.stream()
                .filter(part -> part.getType() == type)
                .toList());
    }

    public ArrayList<Employee> getAll(){
        return new ArrayList<>(obshchaga);
    }

    public void remove(Employee employee){
        obshchaga.remove(employee);
    }
}
