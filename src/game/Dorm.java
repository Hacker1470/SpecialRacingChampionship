package game;

import data.crew.Employee;
import data.crew.JobType;

import java.util.*;

public class Dorm {
    HashMap<Long, Employee> obshchaga;

    public Dorm() {
        obshchaga = new HashMap<>();
    }

    public List<Long> getKeysAscending() {
        return obshchaga.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    public String generateStringCatalog() {
        StringBuilder sb = new StringBuilder(obshchaga.size() * 40);
        int counter = 1;
        for (Employee emp : obshchaga.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList()) {
            sb.append(counter).append(") ").append(emp.getName()).append(" ").append(emp.getPostfix()).append("\n");
            counter++;
        }

        sb.append("\n");

        return sb.toString();
    }

    public void put(Employee newEmployee) {
        obshchaga.put(newEmployee.getId(), newEmployee);
    }

    public ArrayList<Employee> getEmployeesByJob(JobType type) {
        return new ArrayList<>(obshchaga.values().stream()
                .filter(employee -> employee.getType() == type)
                .toList());
    }

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(obshchaga.values());
    }

    public Employee getEmployeeById(long id) {
        return obshchaga.get(id);
    }

    public void remove(Employee employee) {
        remove(employee.getId());
    }

    public void remove(long id) {
        obshchaga.remove(id);
    }

    public long getQuantityOfEmployees() {
        return obshchaga.size();
    }
}
