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

        double transitionHeight = deepHeight - shallowHeight;
        double transitionLength = Math.sqrt((transition*transition) - (transitionHeight*transitionHeight));

        double deepLength = length - (shallowLength + transitionLength);

        double areaShallow = shallowLength * shallowHeight;
        double areaMiddle = (transitionHeight * transitionLength) / 2 + transitionLength * shallowHeight;
        double areaDeep = deepLength * deepHeight;

        int volume = (int) ((areaShallow + areaMiddle + areaDeep) * (width * 0.9));

        System.out.println("The amount of water needed to fill this pool 90% of the way is ~" + volume + "m^3");



        double saSide = (areaShallow + areaMiddle + areaDeep);
        double saShallow = width * shallowHeight;
        double saDeep = width * deepHeight;
        double saBottem = (shallowLength * width) + (transition * width) + (deepLength * width);

        int totalSA = (int)(saSide*2 + saShallow + saDeep + saBottem)+1;
        
        System.out.println("The amount of liner you will need to create the pool is " + totalSA + "m^2"); 

        int priceLiner = (int) totalSA * price;        
        System.out.println("The price of the liner is $" + priceLiner);
    }
}
