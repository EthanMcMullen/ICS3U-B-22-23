package day3;
        /*
         * ExampleOne: Primitive Data Types
         * Variables
         */
public class ExampleOne {
    
    public static void main(String[] args) {
        // Variables are meant to store and eventually recall data/information
        // The data could be anything (Number, String, True/False, List of accounts, etc)

        // Before we use a variable we must DECLARE it
        // When we declare a variable we must state the type of data it will hold

        // Primitive types store the value (They are primitive)
        // int -> integer
        // double -> decimal numbers
        // boolean -> true or false

        int numberOfStudents;        // numberOfStudents has been declared as an INTEGER
        numberOfStudents = 14;
        double testAverage = 63.7;        // testAverage has been declared and initialized on the same line gave it an initial value as a double
        final double PI = 3.14159;        // If the variable cannot be modified after it has been initialised, then make it final
        //PI = 3;                Syntax error (won't compile) Because PI is 
        // Naming convention for constants (final) is ALL_UPPER_CASE_WITH_UNDERSCORES_BETWEEN_WORDS

        boolean isHeads = false;
        boolean hasToes = true;

        System.out.println("There are " + numberOfStudents + " students in the class.");
        System.out.println("5 + 3 = " + 5 + 3); // 5 + 3 = 53
        System.out.println("5 + 3 = " + (5 + 3)); // 5 + 3 = 8
    }
}

