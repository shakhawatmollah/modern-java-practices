package com.shakhawat;

import java.util.List;
import java.util.Optional;

// Sealed interfaces: Define a sealed interface
sealed interface Employee permits Manager, Developer {

    String name();

    default double salary() {
        return 0.0;
    }
}

// Record: Define an immutable data class
record Manager(String name, double salary, int teamSize) implements Employee {}
record Developer(String name, double salary, String programmingLanguage) implements Employee {}

public class ModernJavaExample {

    public static void main(String[] args) {

        var employees = List.of(new Manager("John", 5000.0, 5),
                new Developer("Alex", 6000.0, "Java"),
                new Developer("Tawfiq", 7000.0, "Python"),
                new Developer("Sarah", 8000.0, "Java"),
                new Manager("Hapy", 9000.0, 18)
        );

        var highEarners = employees.stream()
                .filter(employee -> employee.salary() > 7000.0)
                .map(Employee::name)
                .toList();

        System.out.println("High earners: " + highEarners);

        var totalSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .sum();

        System.out.println("Total salary: " + totalSalary);

        var programmingLanguage = employees.stream()
                .filter(employee -> employee instanceof Developer)
                .map(employee -> ((Developer) employee).programmingLanguage())
                .findFirst()
                .orElse("Unknown");

        System.out.println("Most popular programming language: " + programmingLanguage);

        // Pattern matching with instanceof

        if (employees.stream().allMatch(employee -> employee instanceof Developer)) {
            System.out.println("All employees are developers");
        }

        if (employees.stream().anyMatch(employee -> employee instanceof Manager)) {
            System.out.println("At least one employee is a manager");
        }

        for (Employee employee : employees) {
            if (employee instanceof Manager manager) {
                System.out.printf("%s manages a team of %d people%n", manager.name(), manager.teamSize());
            } else if (employee instanceof Developer developer) {
                System.out.printf("%s codes in %s%n", developer.name(), developer.programmingLanguage());
            }
        }

        List<Employee> employeeList = List.of(new Manager("John", 5000.0, 5),
                new Developer("Alex", 6000.0, "Java"),
                new Developer("Tawfiq", 7000.0, "Python")
        );

        // Optional example to handle null safely
        Optional<Employee> optionalEmployee = findEmployeeByName(employeeList, "Alex");

        optionalEmployee.ifPresentOrElse(
                employee -> System.out.println("Found employee: " + employee.name()),
                () -> System.out.println("Employee not found")
        );

        // Optional chaining
        optionalEmployee.flatMap(employee -> findEmployeeByName(employeeList, employee.name()))
                .ifPresentOrElse(
                        employee -> System.out.println("Found employee: " + employee.name()),
                        () -> System.out.println("Employee not found")
                );

    }

    private static Optional<Employee> findEmployeeByName(List<Employee> employees, String name) {
        return employees.stream()
                .filter(employee -> employee.name().equals(name))
                .findFirst();
    }

}
