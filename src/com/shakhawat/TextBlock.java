package com.shakhawat;

public class TextBlock {

    public static void main(String[] args) {
        String text = """
                Hello, World!
                Goodbye, World!
                """;
        System.out.println(text);

        String name = "Shakhawat";
        int age = 40;

        String formattedString = """
                Hello, my name is %s. I am %d.
                """.formatted(name, age);

        System.out.println(formattedString);

        String multilineString = """
                My,
                World!
                """;
        System.out.println(multilineString);

        String htmlCode = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Example</title>
                </head>
                <body>
                    <h1>Hello, World!</h1>
                </body>
                </html>
                """;

        System.out.println(htmlCode);

        // Simple multi-line text using text block
        String json = """
            {
                "name": "Alice",
                "age": 30,
                "department": "HR"
            }
            """;
        System.out.println("JSON Text Block:\n" + json);

        // Multi-line SQL query using text block
        String sqlQuery = """
            SELECT id, name, salary
            FROM employees
            WHERE department = 'IT'
            ORDER BY salary DESC;
            """;
        System.out.println("SQL Query Text Block:\n" + sqlQuery);

        // Combining text blocks with string interpolation (Java 15+)
        String department = "Sales";
        int employeeCount = 25;
        String report = """
            Department: %s
            Total Employees: %d
            """.formatted(department, employeeCount);
        System.out.println("Formatted Report Text Block:\n" + report);

        // Text block with internal quotes and special characters
        String dialogue = """
            "Hello," she said.
            "How are you?"
            He replied, "I'm fine, thank you!"
            """;
        System.out.println("Dialogue Text Block:\n" + dialogue);


    }
    
}
