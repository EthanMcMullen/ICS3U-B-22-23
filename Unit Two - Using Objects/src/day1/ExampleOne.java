package day1;

public class ExampleOne {
    public static void main(String[] args) {
        String superStudent = new String("Callum");
        String baseballPitcher = new String("Nolan Ryan");


        System.out.println(superStudent);
        System.out.println("length of superStudent is " + superStudent.length());
        System.out.println("Substring of superStudent 2 - 4: " + superStudent.substring(2, 4));

        System.out.println(baseballPitcher);
        System.out.println("length of baseballPitcher is " + baseballPitcher.length());
        System.out.println("Substring of baseballPitcher 4 - 8: " + baseballPitcher.substring(4, 8));

        // middle of a string in length / 2
        // first half of a string is substring(0 - length / 2)
        
        System.out.println(baseballPitcher.substring(0, baseballPitcher.length()/2));
        System.out.println(baseballPitcher.substring(baseballPitcher.length()/2, baseballPitcher.length()));
        System.out.println(baseballPitcher.substring(baseballPitcher.length()/2));

        System.out.println("Where is the first n in 'Nolan ryan'? ");
        System.out.println(baseballPitcher.indexOf("n"));
        
    
    }

}