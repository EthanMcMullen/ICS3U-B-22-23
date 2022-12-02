package day1;

public class UsingIfStatements {
   public static void main(String[] args) {
    /*    
    if (boolean expression){
            //do this block of code when the boolean expression is true
        }

        // if () are missing then it does i line of code
        */

        int x = 7;
        if(x % 2 == 0) 
             System.out.println("The number" + x + "is even");
        else
            System.out.println("The number" + x + "is odd");

        int y = 10;
        if(y > 10) // no curly braces then we executed 1 line after if it is true
            y++;
            System.out.println(y);
            
        if(y > 10) {
            y++;
            System.out.println(y);
        }
        

   }
}