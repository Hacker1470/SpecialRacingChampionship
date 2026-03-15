package game;

import data.crew.Employee;
import data.crew.JobType;

import java.util.ArrayList;
import java.util.HashMap;

public class Dorm {
    HashMap<Long, Employee> obshchaga;

    public Dorm(){
        obshchaga = new HashMap<>();
    }

    public void put(Employee newEmployee){
        obshchaga.put(newEmployee.getId(), newEmployee);
    }

    public ArrayList<Employee> getEmployees(JobType type){
        return new ArrayList<>(obshchaga.values().stream()
                .filter(employee -> employee.getType() == type)
                .toList());
    }

    public ArrayList<Employee> getAll(){
        return new ArrayList<>(obshchaga.values());
    }

    public void remove(Employee employee){
        remove(employee.getId());
    }

    public void remove(long id){
        obshchaga.remove(id);
    }
}
