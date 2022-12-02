package day5;

public class HomeworkFive {
    public static void main(String[] args) {
        double wins = 110;
        double losses = 44;
        double winRate = wins / losses;
        winRate = Math.round(winRate * 1000) / 1000.0;
        System.out.println("The New York Yankees win rate in 1927 is " + winRate + " wins for each loss");
    }
}
