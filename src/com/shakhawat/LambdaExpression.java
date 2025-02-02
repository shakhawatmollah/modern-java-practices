package com.shakhawat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExpression {

    // A lambda expression is a concise way to define a function without explicitly passing behavior as a parameter or returning behavior as a result.

    // Define an immutable data class using 'record' (introduced in Java 14)
    public record Employee(int id, String name, double salary, String department) {}

    public static void main(String[] args) {

        List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");

        // Using a lambda expression as a Predicate to filter elements
        Predicate<String> startsWithA = fruit -> fruit.startsWith("a");
        List<String> filteredFruits = fruits.stream()
                .filter(startsWithA)
                .toList();

        System.out.println("Fruits starting with 'a': " + filteredFruits);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Using a lambda expression as a Predicate
        Predicate<Integer> isEven = (n) -> n % 2 == 0;
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .toList();

        // Printing the even numbers
        System.out.println("Even numbers: " + evenNumbers);

        int totalEvenNumbers = numbers.stream()
                .filter(isEven)
                        .mapToInt(Integer::intValue)
                                .sum();

        // Printing the sum of even numbers
        System.out.println("Sum of even numbers: " + totalEvenNumbers);

        // List of employees
        var employees = List.of(
                new Employee(1, "Alice", 1200.0, "HR"),
                new Employee(2, "Shakhawat", 1090.0, "IT"),
                new Employee(3, "Tawfiq", 5500.0, "Sales"),
                new Employee(4, "Liyana", 2100.0, "IT"),
                new Employee(5, "Hapy", 2500.0, "Sales")
        );

        Predicate<Employee> condition = (emp) -> emp.salary() >= 2000; // Condition to filter employees with salary greater than or equal to 2000

        var employeeNames = employees.stream()
                .filter(condition)
                .map(Employee::name)
                .toList();

        System.out.println("Employees with salary greater than or equal to 2000: " + employeeNames);

        // Find the first employee in the IT department using Optional and pattern matching for instanceof (Java 16+)
        Optional<Employee> firstITEmployee = employees.stream()
                .filter(emp -> emp.department().equals("IT"))
                .findFirst();


        // Using Optional and pattern matching
        firstITEmployee.ifPresentOrElse(
                emp -> {
                    if(emp instanceof Employee itEmployee) {
                        System.out.println("First employee in the IT department: " + itEmployee.name());
                    }
                },
                () -> {
                    System.out.println("No employee found in the IT department.");
                }
        );

        // Grouping employees by department using streams and collectors
        Map<String, List<Employee>> groupedEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        System.out.println("Grouped employees by department: " + groupedEmployees);

        //Calculating the sum of salary for each department using streams and collectors
        Map<String, Double> departmentSalaries = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.summingDouble(Employee::salary)));

        System.out.println("Department salaries: " + departmentSalaries);

        // Calculate total salary expense
        double totalSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .sum();

        System.out.println("Total Salary Expense: " + totalSalary);

        // Average salary
        double averageSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);

        System.out.println("Average Salary: " + averageSalary);

        // Use 'var' for local type inference (Java 10+)
        var itEmployees = employees.stream()
                .filter(emp -> "IT".equals(emp.department()))
                .toList();

        System.out.println("IT Employees: " + itEmployees);

    }

}
