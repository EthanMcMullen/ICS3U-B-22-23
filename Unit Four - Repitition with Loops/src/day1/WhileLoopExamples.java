package day1;

import java.util.Scanner;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

public class WhileLoopExamples {
   public static void main(String[] args) {
      //exampleOne();
      //exampleTwo();
      //exampleThree();
      //exampleFour();
      exampleFive();
   }

    private static void exampleFive() {
        int numEven = 0;
        int numRandomNumbers = 24;
        int i = 0;
        int x = 0;

        while (x < 10) {
            x++;

            while (i < numRandomNumbers) {
                i++;
                int num = (int)(Math.random()*100) + 1;
                if (num % 2 == 0) {
                    numEven++;
                }
            }
            System.out.println("%" + (double)numEven / numRandomNumbers * 100);
        }
    }

/*
    * Introduction to While Loops - Keep asking for a number until the number is
    * even.
    */
   private static void exampleOne() {
    /*    
    //Syntax for While
        while(condition) {
            // do this code
        }

        // syntax of if statment
        if(condition) {
            // do this code
        }
        */

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a number (even to quit): ");
        int number = in.nextInt();
        while(number %2 != 0) {
            System.out.println("Please enter a number (even to quit): ");
            number = in.nextInt();
        }
   }

   /* Sum the numbers from 1 to 1000 using a while loop */
   private static void exampleTwo() {
        int curNum = 0;
        int sum = 0;
        while (curNum <= 1000) {
            sum += curNum;
            curNum += 1;
        }
        System.out.println("The sum of all digits from 1-1000 is " + sum);


   }

   /*
    * Prompt the user for a number and add it to a sum. Continue as long as the
    * number is positive
    */
   private static void exampleThree() {
        int sum = 0;
        Scanner in = new Scanner(System.in);
            System.out.println("Please enter a number ");
            int number = in.nextInt();

            while(number >= 0) {
                sum += number;
                System.out.println("Sum: " + sum);
                System.out.println("Please enter a number ");
                number = in.nextInt();       
            }
   }

   /* Prompt the user for a String and count the number of vowels in the String. */
   private static void exampleFour() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Please enter a String: ");
    String text = keyboard.nextLine();

    int index = 0;
    int countVowels = 0;

    while (index < text.length()) {
        String nextLetter = text.substring(index, index+1);
        if("aeiou".indexOf(nextLetter.toLowerCase()) >= 0) {
            countVowels ++;
        }
        index++;
    }

    System.out.println("num of vowels: " + countVowels);

   }

}