package day1;

public class BooleanExpressions {
   public static void main(String[] args) {
        // mathematical expression evaluate to a number
        // logical and boolean expressions evaluate to true and/or false

        boolean exp1 = 17>5; //true
        boolean exp2 = 17 == 17; //true
        boolean isRed = true;
        boolean isHard = false;

        boolean exp3 = isHard == isRed; //false because isHard and isRed arent the same

        int x = 7;
        boolean exp4 = (x < 3) != (x > 5); //True because false is not equal to true

        int y = 3;
        boolean exp5 = (x < y) == (y > 7); //True (false == false) yeilds true
  

        // Compound Boolean Expressions
        // AND = &&
        // OR = ||
        // NOT !

        // not operator

        boolean isGreen = true;
        boolean exp6 = !isGreen;

        boolean exp7 = !true; // aka false
        boolean exp8 = !false; // aka true

        boolean isBlue = false;
        isHard = true;

        boolean isHardBlue = isBlue && isHard; //one is false and one is true, therefore they arent both true and it returns falst

    
        boolean isBlueOrHard = isBlue || isHard; // false or true returns true

        x = 7;
        y = 17;

        boolean exp9 = (x > 7) && (y < 25);

    }
}