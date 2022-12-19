package Assignments;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;
import javax.naming.spi.DirStateFactory.Result;

public class GoFish {

    static Scanner in = new Scanner(System.in);
    private static final int NUM_STARTING_CARDS = 5;
    private static final String HEARTS = "H";
    private static final String SPADES = "S";
    private static final String CLUBS = "C";
    private static final String DIAMONDS = "D";
    private static final int NUM_VALUES = 13;
    private static final String KING = "K";
    private static final String QUEEN = "Q";
    private static final String JACK = "J";
    private static final String ACE = "A";
    private static final int NUM_SUITS = 4;
    private static final String ACCEPTABLE_CARDS = "2345678910jqka";
    
    public static void main(String[] args) {
        int playerScore = 0;
        int cpuScore1 = 0;
        int cpuScore2 = 0;
        int cpuScore3 = 0;
        String playerHand = addOrStartCards(NUM_STARTING_CARDS);
        String cpuHand1 = addOrStartCards(NUM_STARTING_CARDS);
        String cpuHand2 = addOrStartCards(NUM_STARTING_CARDS);
        String cpuHand3 = addOrStartCards(NUM_STARTING_CARDS);

        printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3);
        System.out.println("\n\n\n");

        String resultCard = playerTurn(playerHand);

        if(resultCard.equals(" ")) {
            playerHand += addOrStartCards(1);
        } else {
            String whoStolen = stealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand);
            //int howMuchStolen = stealNum(resultCard, cpuStolen, cpuHand1, cpuHand2, cpuHand3);
            playerHand += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, true);

            if (whoStolen.equals("cpu1")) {
                cpuHand1 = stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            } else if (whoStolen.equals("cpu2")) {
                cpuHand2 = stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            } else if (whoStolen.equals("cpu3")) {
                cpuHand3 = stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            }
        }

        System.out.println(playerHand);
    }

    

    



    private static String playerTurn(String playerHand) { //Begins the player's turn, it returns the card the player chooses if it is acceptable and in their hand
        if(playerHand.length() > 1) {
            boolean turn = true;
            System.out.println("                          PLAYER TURN \n_______________________________________________________________\n");
            while(turn) {
                System.out.println("What card would you like to ask for Please type 2-10 J Q K or A: ");
                String result = in.nextLine().toLowerCase();
                if (ACCEPTABLE_CARDS.contains(result) && (result.length() == 1 || result.equals("10")) && result != "0") { 
                        if (playerHand.toLowerCase().contains(result)) {
                            return result;
                        } else {
                            System.out.println("You do not have this card in your hand");
                        }
                } else {
                    System.out.println("Please type only the acceptable cards");
                }
            }
        } else {}
        return " ";
        
    }


    

    private static String stealCard(String resultCard, String cpuHand1, String cpuHand2, String cpuHand3, String playerHand) {
            while(true) {
                System.out.println("Who would you like to take from? CPU1 CPU2 or CPU3: ");
                String cpuResult = in.nextLine().toLowerCase();
                    if (cpuResult.equals("cpu1") || cpuResult.equals("cpu2") || cpuResult.equals("cpu3")) {
                        return cpuResult; //returns for the first cpu
                        //numTaken = checkGoFish(cpuHand1, resultCard, "cpu1");
                    } else {
                        System.out.println("Invalid player");
                    }
            }            
    }

    private static String stealHands(String resultCard, String playerHand, String cpuStolen, String cpuHand1, String cpuHand2, String cpuHand3, boolean playerStealing) {
        if (playerStealing) {
            if (cpuStolen.equals("cpu1")) {
                return checkGoFish(cpuHand1, resultCard, cpuStolen);
            } else if (cpuStolen.equals("cpu2")) {
                return checkGoFish(cpuHand2, resultCard, cpuStolen);
            } else { //equals cpu3
                return checkGoFish(cpuHand3, resultCard, cpuStolen);
            }
        } else     
            return IM TYPING LIKE THIS SO ITS RED, COMPLETE LATER. its supposed to call the function which doesnt exist yet to get rid of the cards we just added.
    }

    
    private static String checkGoFish(String hand, String resultCard, String name) {
        String ans = "";
        int numHas = 0;
        for (int i = 0; i < hand.length(); i++) {
            if (hand.substring(i, i+1).equals(resultCard)) {
                ans += hand.substring(i,i+3);
                numHas++;
            }
        }
        System.out.println(name + " has " + numHas + resultCard + "s ");
        return ans;
    }
    

    private static void printAllCards(String playerHand, String cpuHand1, String cpuHand2, String cpuHand3) {
        System.out.println("Player: " + playerHand);
        System.out.println("CPU1: " + cpuHandPrint(cpuHand1));    
        System.out.println("CPU2: " + cpuHandPrint(cpuHand2));
        System.out.println("CPU3: " + cpuHandPrint(cpuHand3));
    
    }

    private static String cpuHandPrint(String cpuHand) { // To show XX instead of Card Names for CPUs (also to bypass for 10s)
        int spacecount = 0;
        for (int i = 0; i < cpuHand.length(); i++) {
            if (cpuHand.substring(i, i+1).equals(" ")) {
                spacecount++;
            } 
        }
        String printCpu = "";
        for (int i = 0; i < spacecount; i++)  {
            printCpu += "XX ";
        }
        return printCpu;
    }
    

    private static String addOrStartCards(int numCardsAdded) {
        String ans = "";
        for (int i = 0; i < NUM_STARTING_CARDS; i++) {
            ans = ans + getCard() + " ";
        }
        return ans;
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
}
