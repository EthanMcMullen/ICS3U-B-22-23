package Assignments;

import java.util.Scanner;

import javax.crypto.spec.IvParameterSpec;

import day2.ElseIfStatements;

public class Blackjack {

    static Scanner in = new Scanner(System.in);
    static final int MIN_BET = 5;
    static int startingWallet = 500;
    private static final int WIN = 1;
    private static final int LOST = -1;
    private static final int TIE = 0;
    private static final int NUM_SUITS = 4;
    private static final String HEARTS = "H";
    private static final String SPADES = "S";
    private static final String CLUBS = "C";
    private static final String DIAMONDS = "D";
    private static final int NUM_VALUES = 13;
    private static final String KING = "K";
    private static final String QUEEN = "Q";
    private static final String JACK = "J";
    private static final String ACE = "A";
    private static final int BLACK_JACK = 21;

    public static void main(String[] args) {


        int wallet = startingWallet;
        boolean stillPlaying = true;
        
        while(stillPlaying) {
            System.out.println("Grandparents Life Savings: " + wallet);
            int bet = getBet(wallet);
            String playerHand = "2S" + " " + "2C";
            String dealerHand = getCard();

            
            displayHand(dealerHand, true, "Cameron Hand: ");

            // return who won
            int result = playHand(playerHand, dealerHand);

            if (result == WIN) {
                wallet += bet;
                System.out.println("You beat cameron! now time to stop gambling, the first step is understanding you have a problem");
            } else if (result == LOST) {
                wallet -= bet;
                System.out.println("You lost, the dealer beat you, and now you have to tell marco that you \"lost\" his rent payment for the month (he wont be happy)");
            } else {
                System.out.println("You have tied, cameron lets out a big sigh as the entire game was for nothing");
            } if (wallet < MIN_BET) {
                stillPlaying = false;
                System.out.println("You do not have enough $$ to play again, do you see what you did. you gambled your grandparents life savings away. and now your entire family hates you, what a shame.");
            } else
                stillPlaying = playAgain();
        }
    }

    private static boolean playAgain() {
        while (true) {
            System.out.println("Cameron Gestures you to Play Again as he wants your money([Y]es/[N]o");
            String result = in.nextLine().toLowerCase();

            if (result.equals("y") || result.equals("yes"))
                return true;
            else if (result.equals("n") || result.equals("no"))
                return false;
        }
    }

    // return WIN if player wins LOST if play lost and TIE if player tie
    private static int playHand(String playerHand, String dealerHand) {
        playerHand = playerTurn(playerHand);
        dealerHand = dealerTurn(dealerHand); 

        int playerScore = getCardsValue(playerHand);
        int dealerScore = getCardsValue(dealerHand);

        if((playerScore <= BLACK_JACK) && ((playerScore > dealerScore) || (playerScore <= BLACK_JACK))) {
            return WIN;
        } else if ((playerScore > BLACK_JACK) || dealerScore > playerScore) {
            return LOST;
        } else 
            return TIE;
    }

    private static int getCardsValue(String cards) {
        int numAces = 0;

        int scoreBeforeAces = 0;
        for (int i = 0; i < cards.length(); i++) {
            String s = cards.substring(i, i+1);
            if("JQK1".indexOf(s) >= 0) {
                scoreBeforeAces += 10;
            } else if ("23456789".indexOf(s) >= 0) {
                scoreBeforeAces += Integer.parseInt(s);
            } else if ("A".equals(s)) {
                numAces++;
            }
        }

        if (numAces > 0 && (scoreBeforeAces + 11 + numAces - 1) <= BLACK_JACK) {
            scoreBeforeAces += (11 + numAces - 1);
        } else 
            scoreBeforeAces += numAces;

        return scoreBeforeAces;

    }

    

    private static String dealerTurn(String dealerHand) {
        dealerHand += " " + getCard();
        displayHand(dealerHand, false, "Cameron's hand: ");
        while(getCardsValue(dealerHand) < 17) {
            dealerHand += " " + getCard();
            displayHand(dealerHand, false, "Cameron's hand: ");
        }
        return dealerHand;
    }

    private static String playerTurn(String playerHand) {
        displayHand(playerHand, false, "Player Hand:");

        while(true){
            if (takeCard()) {
                playerHand += " " + getCard();
                displayHand(playerHand, false, "Player Hand:");
                if(getCardsValue(playerHand)>BLACK_JACK)
                    return playerHand;
            } else 
                return playerHand;
        }
    }

    private static boolean takeCard() {
        while(true){
            System.out.println("Hit (risky omg) [1] or Stand (safe ahh) [2]: ");
            String result = in.nextLine().toLowerCase();

            if (result.equals("1"))
                return true;
            else if (result.equals("2"))
                return false;
            else 
                System.out.println("Invalid Imput bozo | Hit [1] or Stand [2]: ");
        }
    }

    private static void displayHand(String cards, boolean isHidden, String label) {
        String ans = label;
        if(isHidden)
            ans += "XX " + cards;
        else 
            ans += cards;
            System.out.println(ans);
    }

    private static String getCard() {
        return getValue() + getSuit();
    }

    private static String getValue() {
        int iValue = (int)(Math.random() * NUM_VALUES + 1);

        if (iValue == 1) {
            return ACE;
        } else if (iValue == 11) {
            return JACK;
        } else if (iValue == 12) {
            return QUEEN;
        } else if (iValue == 13) {
            return KING;
        } else
            return "" + iValue; 

    }

    private static String getSuit() {
        int iSuit = (int)(Math.random() * NUM_SUITS + 1);

        if (iSuit == 1)
            return HEARTS;
        else if(iSuit == 2)
            return SPADES;
        else if(iSuit == 3)
            return CLUBS;
        else 
            return DIAMONDS;
    }

    private static int getBet(int maxBet) {
        boolean validBet = false;
        int bet = 0;
        System.out.print("Please enter bet, Cameron wants at least " + MIN_BET + " bucks, or else it aint worth it: ");
        while(!validBet) {
            try {
            bet = Integer.parseInt(in.nextLine());
            
            if (bet > maxBet) 
                System.out.print("As you reach into your Grandparents Savings you realize that you just betting more then you have, you let out an awkward chuckle as Cameron tells you to change your bet: ");
            else if (bet < MIN_BET) 
                System.out.print("Cameron slowly wispers into your ear, That ain't enough idiot: ");
            else
                validBet = true;
            } catch(NumberFormatException ex) {
                System.out.print("Cameron says that you cant bet $" + bet + ". man its a shame you didnt pass grade 1");
            }
        }

        return bet;
    }
}