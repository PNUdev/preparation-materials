package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    public Employee getById(Integer id) {
        Optional<Employee> result = Optional.empty();
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                result = Optional.of(employee);
            }
        }
        return result.orElseThrow(RuntimeException::new);
    }
}
