package assignment;

import java.util.Scanner;
    
public class BayviewGlenPools {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the Width of the Pool in m: ");
        int width = in.nextInt();
        System.out.println(width);

        System.out.print("Please enter the Length of the Pool in m: ");
        int length = in.nextInt();
        System.out.println(length);

        System.out.print("Please enter the Height of the Shallow End in m: ");
        int shallowHeight = in.nextInt();
        System.out.println(shallowHeight);

        System.out.print("Please enter the Height of the Deep End in m: ");
        int deepHeight = in.nextInt();
        System.out.println(deepHeight);

        System.out.print("Please enter the Length of the Shallow End in m: ");
        int shallowLength = in.nextInt();
        System.out.println(shallowLength);

        System.out.print("Please enter the Length of the transition in m: ");
        int transition = in.nextInt();
        System.out.println(transition);

        System.out.print("Please enter the Price per Square Metre of Pool Liner: ");
        int price = in.nextInt();
        System.out.println(price);

        double transitionHeight = deepHeight - shallowHeight; // Hight of the Transition slope.
        double transitionLength = Math.sqrt((transition*transition) - (transitionHeight*transitionHeight)); //Length of the Transition slope

        double deepLength = length - (shallowLength + transitionLength); // Length of the deep end of the pool
        double areaShallow = shallowLength * shallowHeight; // Area of the Shallow end of the pool
        double areaMiddle = (transitionHeight * transitionLength) / 2 + transitionLength * shallowHeight; // Area of the transition area of the pool
        double areaDeep = deepLength * deepHeight; // Area of the Deep end of the pool

        int volume = (int) ((areaShallow + areaMiddle + areaDeep) * (width * 0.9)); // Calculates the total volume

        System.out.println("The amount of water needed to fill this pool 90% of the way is ~" + volume + "m^3");
        
        // Finding the surface area

        double saSide = (areaShallow + areaMiddle + areaDeep); // Surface area of the large side
        double saShallow = width * shallowHeight; // Surface area of the shallow end wall
        double saDeep = width * deepHeight; // Surface area of the deep end wall
        double saBottem = (shallowLength * width) + (transition * width) + (deepLength * width); // Surface area of the bottem of the pool

        int totalSA = (int)(saSide*2 + saShallow + saDeep + saBottem)+1; // Calculates the total Surface area
        
        System.out.println("The amount of liner you will need to create the pool is " + totalSA + "m^2"); 


        int priceLiner = (int) totalSA * price; // Calculate the price of the liner
        System.out.println("The price of the liner is $" + priceLiner);
    }
}
