package Assignments;

import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.event.AncestorEvent;

public class GoFish {

    static Scanner in = new Scanner(System.in);
    private static final int NUM_STARTING_CARDS = 8;
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

        System.out.println("\n\n\nAll hands X means Faced Down \n_______________________________________________________________\n");
        printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3);

        // For player turn 

        String resultCard = playerTurn(playerHand);

        if(resultCard.equals(" ")) {
            playerHand += addOrStartCards(1);
        } else {
            String whoStolen = stealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand);
            //int howMuchStolen = stealNum(resultCard, whoStolen, cpuHand1, cpuHand2, cpuHand3);
            playerHand += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, true);

            if (whoStolen.equals("cpu1")) {
                cpuHand1 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            } else if (whoStolen.equals("cpu2")) {
                cpuHand2 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            } else if (whoStolen.equals("cpu3")) {
                cpuHand3 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3, false);
            }
        }
        printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3);

        // For cpu turns

        System.out.println(playerHand);
    }

    private static String changeStolenHands(String resultCard, String playerHand, String whoStolen, String cpuHand1, String cpuHand2, String cpuHand3, boolean isPlayer) {
        if (isPlayer) {
            return goFishCheckVictem(playerHand, resultCard, whoStolen);
        } else {
            if (whoStolen.equals("cpu1")) {
                return goFishCheckVictem(cpuHand1, resultCard, whoStolen);
            } else if (whoStolen.equals("cpu2")) {
                return goFishCheckVictem(cpuHand2, resultCard, whoStolen);
            } else {
                return goFishCheckVictem(cpuHand3, resultCard, whoStolen);
            }
        }
            
    }


    private static String goFishCheckVictem(String hand, String resultCard, String whoStolen) {
        String ans = hand;
        while(true){
            if(ans.indexOf(resultCard) >= 0) {
                    int i = ans.indexOf(resultCard);
                    if (ans.substring(i, i+2).equals("10")) 
                        ans = ans.substring(0, i) + ans.substring(i+4);
                    else
                        ans = ans.substring(0, i) + ans.substring(i+3);
            } else {
                return ans;
            }
        }
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

    private static String stealHands(String resultCard, String playerHand, String whoStolen, String cpuHand1, String cpuHand2, String cpuHand3, boolean isPlayer) {
        if (isPlayer) {
            if (whoStolen.equals("cpu1")) {
                return checkGoFishTheif(cpuHand1, resultCard, whoStolen);
            } else if (whoStolen.equals("cpu2")) {
                return checkGoFishTheif(cpuHand2, resultCard, whoStolen);
            } else { //equals cpu3
                return checkGoFishTheif(cpuHand3, resultCard, whoStolen);
            }
        } else {
            return checkGoFishTheif(playerHand, resultCard, whoStolen);
        }   
    }

    
    private static String checkGoFishTheif(String hand, String resultCard, String name) {
        String ans = "";
        int numHas = 0;
        for (int i = 0; i < hand.length()-1; i++) {
            if (hand.substring(i, i+1).equals(resultCard)) {
                ans += hand.substring(i,i+3);
                numHas++;
            } else if (hand.substring(i, i+2).equals(resultCard)) {
                ans += hand.substring(i, i+4);
                numHas++;
            }
        }
        if (numHas > 0) {
        System.out.println("Congratulations! , " + name + " has " + numHas + " " + resultCard + "(s) in their deck! ");
        }
        return ans;
    }
    

    private static void printAllCards(String playerHand, String cpuHand1, String cpuHand2, String cpuHand3) {
        System.out.println("Player: " + playerHand);
        System.out.println("CPU1: " + cpuHand1);    
        System.out.println("CPU2: " + cpuHand2);
        System.out.println("CPU3: " + cpuHand3);



        /*
        System.out.println("CPU1: " + cpuHandPrint(cpuHand1));    
        System.out.println("CPU2: " + cpuHandPrint(cpuHand2));
        System.out.println("CPU3: " + cpuHandPrint(cpuHand3));
        */
    
    }

    private static String cpuHandPrint(String cpuHand) { // To show XX instead of Card Names for CPUs / also to bypass for 10s
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
