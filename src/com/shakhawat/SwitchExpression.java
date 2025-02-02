package com.shakhawat;

import java.util.List;

record User(String name, int age, String country) {}

public class SwitchExpression {

    public static void main(String[] args) {
        User user = new User("John", 25, "USA");
        String country = switch (user.country()) {
            case "USA" -> "United States";
            case "UK" -> "United Kingdom";
            default -> "Unknown";
        };
        System.out.println(country);

        // Local variable type inference using 'var'
        var users = List.of(
                new User("Shakhawat", 40, "BD"),
                new User("John", 28, "USA"),
                new User("Jane", 30, "UK"),
                new User("Bob", 40, "Canada"),
                new User("Alice", 35, "USA"),
                new User("Eve", 20, "UK")
        );

        // Use streams to manipulate collections
        var averageAge = users.stream()
                .mapToInt(User::age)
                .average()
                .orElse(0);

        System.out.println("Average age: " + averageAge);

        // Use switch expression to filter and transform collections
        var filteredUsers = users.stream()
                .filter(user1 -> user1.age() > 30)
                .map(User::name)
                .toList();  // Convert stream to list

        System.out.println(filteredUsers);

        // Process data with enhanced switch statements
        for (var usr : users) {
            System.out.println(processUser(usr));
        }

    }

    private static String processUser(User user) {

        return switch (user.country()) {
            case "USA" -> String.format("%s lives in the Big Apple!", user.name());
            case "Canada" -> String.format("%s lives in sunny LA!", user.name());
            case "BD" -> String.format("%s lives in the Green!", user.name());
            default -> "Unknown city!";
        };

    }
}
