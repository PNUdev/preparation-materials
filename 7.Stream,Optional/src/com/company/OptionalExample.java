package com.company;

import java.util.Objects;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(new Employee(1, "Alex"));
        employeeRepository.add(new Employee(2, "Tony"));

        /**
         Some code
         */

        Employee employee1 = employeeRepository.getById(1);
        System.out.println(employee1.getName() + " is on board!");

        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.getById(3));
        employeeOptional.ifPresent((o) -> System.out.println(o.getName() + " is on board!"));
        Employee employee = employeeOptional.orElse(new Employee(4, "Vasya"));
        employeeOptional.orElseGet(() -> new Employee(4, "Vasya"));
        Employee employee2 = employeeOptional.orElseThrow(() -> new RuntimeException("Not found"));
        Employee employeeWithoutName = new Employee(5, null);
        Optional<Employee> employeeWithoutNameOptional = Optional.of(employeeWithoutName);
        String stringOptional = employeeWithoutNameOptional
                .map(Employee::getName)
                .orElseThrow(() -> new RuntimeException("Name not found"));
        employeeOptional.filter(e -> employee.getName().length() > 3);
        employeeOptional.flatMap(e -> Optional.of(new Dog("Sharik")));
        employeeRepository.getById(3);
    }
}
