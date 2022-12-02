package day4;

public class PractiveSix {
    public static void main(String[] args) {
        int pennies = 12;
        int nickels = 4;
        int dimes = 35;
        int quarters = 5;
        int loonies = 24;
        int toonies = 3;

        double totalMoney = (pennies * 0.01) + (nickels * 0.05) + (dimes * 0.10) + (quarters * 0.25) + (loonies * 1.0) + (toonies * 2.0);
        System.out.println(totalMoney);
    }
}
