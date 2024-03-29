package Assignments;

import java.util.Scanner;


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
    private static final String ACCEPTABLE_CARDS_NO_10 = "23456789JQKA";
    
    public static void main(String[] args) throws InterruptedException {
        boolean restart = true;
        while (restart) {
            int playerScore = 0;
            int cpuScore1 = 0;
            int cpuScore2 = 0;
            int cpuScore3 = 0;
            boolean isPlaying = true;
            String playerHand = addOrStartCards(NUM_STARTING_CARDS);
            String cpuHand1 = addOrStartCards(NUM_STARTING_CARDS);
            String cpuHand2 = addOrStartCards(NUM_STARTING_CARDS);
            String cpuHand3 = addOrStartCards(NUM_STARTING_CARDS);
            

            System.out.println("\n\n\nAll hands X means Faced Down \n_______________________________________________________________\n");
            printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);

            // Checks the amount of pairs in the hand, stores and int for the score and a string for the new hand (checkScore must always be BEFORE checkHand)
            playerScore += checkScore(playerHand);
            cpuScore1 += checkScore(cpuHand1);
            cpuScore2 += checkScore(cpuHand2);
            cpuScore3 += checkScore(cpuHand3);
            playerHand = checkHand(playerHand, true);
            cpuHand1 = checkHand(cpuHand1, true);
            cpuHand2 = checkHand(cpuHand2, true);
            cpuHand3 = checkHand(cpuHand3, true);

            System.out.println("\nHands after Pairs \n_______________________________________________________________\n");
            printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);

            // For player turn
            
            
            while(isPlaying) {

                String resultCard = playerTurn(playerHand);

                if(resultCard.equals(" ")) {
                    playerHand += addOrStartCards(1);
                } else {
                    String whoStolen = stealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand);
                    playerHand += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3); // Changes the player hand by stealing the card they asked for

                    if (whoStolen.equals("cpu1")) {
                        cpuHand1 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                    } else if (whoStolen.equals("cpu2")) {
                        cpuHand2 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                    } else if (whoStolen.equals("cpu3")) {
                        cpuHand3 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                    }
                }
                
                System.out.println("\nNew Hands\n_______________________________________________________________");
                playerScore += checkScore(playerHand);
                playerHand = checkHand(playerHand, true);
                printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);
                boolean didWin = checkWin(playerScore);
                if(didWin) {
                    isPlaying = false;
                    System.out.println("██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗██╗██╗");
                    System.out.println("██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝██║██║");
                    System.out.println("██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░██║██║");
                    System.out.println("██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗╚═╝╚═╝");
                    System.out.println("██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝██╗██╗");
                    System.out.println("╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═╝╚═╝");
                }



                // Player turn Finished Following will be cpu1, cpu2 and then cpu3

                // For cpu1 
                if (isPlaying) {
                    Thread.sleep(3000);
                    resultCard = cpuTurn(cpuHand1, 1);
                    if(resultCard.equals(" ")) {
                        cpuHand1 += addOrStartCards(1);
                    } else {
                        String whoStolen = cpuStealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand, "cpu2", "cpu3");
                        cpuHand1 += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);

                        if (whoStolen.equals("cpu2")) {
                            cpuHand2 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("cpu3")) {
                            cpuHand3 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("player")) {
                            playerHand = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } 
                    }

                    System.out.println("\nNew Hands\n _______________________________________________________________");
                    cpuScore1 += checkScore(cpuHand1);
                    cpuHand1 = checkHand(cpuHand1, true);
                    printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);
                    didWin = checkWin(cpuScore1);
                    if(didWin) {
                        isPlaying = false;
                        System.out.println("░█████╗░██████╗░██╗░░░██╗  ░░███╗░░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗██╗██╗");
                        System.out.println("██╔══██╗██╔══██╗██║░░░██║  ░████║░░  ░██║░░██╗░░██║██║████╗░██║██╔════╝██║██║");
                        System.out.println("██║░░╚═╝██████╔╝██║░░░██║  ██╔██║░░  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░██║██║");
                        System.out.println("██║░░██╗██╔═══╝░██║░░░██║  ╚═╝██║░░  ░░████╔═████║░██║██║╚████║░╚═══██╗╚═╝╚═╝");
                        System.out.println("╚█████╔╝██║░░░░░╚██████╔╝  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝██╗██╗");
                        System.out.println("░╚════╝░╚═╝░░░░░░╚═════╝░  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═╝╚═╝");
                    }
                }

    

                
                // CPU2


                if (isPlaying) {
                    Thread.sleep(3000);
                    resultCard = cpuTurn(cpuHand2, 2);
                    if(resultCard.equals(" ")) {
                        cpuHand2 += addOrStartCards(1); // "Go fish" if hand is empty
                    } else {
                        String whoStolen = cpuStealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand, "cpu1", "cpu3"); // Finds the person that cpu2 wants to steal from
                        cpuHand2 += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);

                        if (whoStolen.equals("cpu1")) {
                            cpuHand1 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("cpu3")) {
                            cpuHand3 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("player")) {
                            playerHand = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } 
                    }

                    System.out.println("\nNew Hands\n _______________________________________________________________");
                    cpuScore2 += checkScore(cpuHand2);
                    cpuHand2 = checkHand(cpuHand2, true);
                    printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);
                    didWin = checkWin(cpuScore2);
                    if(didWin) {
                    isPlaying = false;
                    System.out.println("░█████╗░██████╗░██╗░░░██╗  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗██╗██╗");
                    System.out.println("██╔══██╗██╔══██╗██║░░░██║  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝██║██║");
                    System.out.println("██║░░╚═╝██████╔╝██║░░░██║  ░░███╔═╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░██║██║");
                    System.out.println("██║░░██╗██╔═══╝░██║░░░██║  ██╔══╝░░  ░░████╔═████║░██║██║╚████║░╚═══██╗╚═╝╚═╝");
                    System.out.println("╚█████╔╝██║░░░░░╚██████╔╝  ███████╗  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝██╗██╗");
                    System.out.println("░╚════╝░╚═╝░░░░░░╚═════╝░  ╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═╝╚═╝");
                    }
                }

                // CPU3


                if (isPlaying) {
                    Thread.sleep(3000);
                    resultCard = cpuTurn(cpuHand3, 3);
                    if(resultCard.equals(" ")) {
                        cpuHand3 += addOrStartCards(1);
                    } else {
                        String whoStolen = cpuStealCard(resultCard, cpuHand1, cpuHand2, cpuHand3, playerHand, "cpu1", "cpu2");
                        cpuHand3 += stealHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);

                        if (whoStolen.equals("cpu1")) {
                            cpuHand1 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("cpu2")) {
                            cpuHand2 = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } else if (whoStolen.equals("player")) {
                            playerHand = changeStolenHands(resultCard, playerHand, whoStolen, cpuHand1, cpuHand2, cpuHand3);
                        } 
                    }

                    System.out.println("\nNew Hands\n _______________________________________________________________");
                    cpuScore3 += checkScore(cpuHand3);
                    cpuHand3 = checkHand(cpuHand3, true);
                    printAllCards(playerHand, cpuHand1, cpuHand2, cpuHand3, playerScore, cpuScore1, cpuScore2, cpuScore3);
                    didWin = checkWin(cpuScore3);
                    if(didWin) {
                        isPlaying = false;
                        System.out.println("░█████╗░██████╗░██╗░░░██╗  ██████╗░  ░██╗░░░░░░░██╗██╗███╗░░██╗░██████╗██╗██╗");
                        System.out.println("██╔══██╗██╔══██╗██║░░░██║  ╚════██╗  ░██║░░██╗░░██║██║████╗░██║██╔════╝██║██║");
                        System.out.println("██║░░╚═╝██████╔╝██║░░░██║  ░█████╔╝  ░╚██╗████╗██╔╝██║██╔██╗██║╚█████╗░██║██║");
                        System.out.println("██║░░██╗██╔═══╝░██║░░░██║  ░╚═══██╗  ░░████╔═████║░██║██║╚████║░╚═══██╗╚═╝╚═╝");
                        System.out.println("╚█████╔╝██║░░░░░╚██████╔╝  ██████╔╝  ░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝██╗██╗");
                        System.out.println("░╚════╝░╚═╝░░░░░░╚═════╝░  ╚═════╝░  ░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░╚═╝╚═╝");
                    }
                }
                    
            }
            boolean gotAnswer = false;
            while(!gotAnswer) {
                System.out.println("would you like to play again? (y)/(n)");
                String result = in.nextLine().toLowerCase();
                if (result.equals("y")) {
                    playerScore = 0;
                    cpuScore1 = 0;
                    cpuScore2 = 0;
                    cpuScore3 = 0;
                    isPlaying = true;
                    playerHand = addOrStartCards(NUM_STARTING_CARDS);
                    cpuHand1 = addOrStartCards(NUM_STARTING_CARDS);
                    cpuHand2 = addOrStartCards(NUM_STARTING_CARDS);
                    cpuHand3 = addOrStartCards(NUM_STARTING_CARDS);
                    gotAnswer = true;
                } else if (result.equals("n")) {
                    restart = false;
                    gotAnswer = true;
                }
            }
        }
}



    private static boolean checkWin(int playerScore) {
        if (playerScore >= 10) {
            return true;
        } else {
            return false;
        }
    }



    private static int checkScore(String hand) {
        String ans = checkHand(hand, false);
        return ans.length()/2;
    }



    private static String checkHand(String hand, boolean isString) { // MAKE BOOLEAN GET INT FOR SCORE
        String k = "";
        int numHas = 0;
        String ans = "";
        for (int j = 0; j < ACCEPTABLE_CARDS_NO_10.length(); j++) {
            numHas = 0;
            k = ACCEPTABLE_CARDS_NO_10.substring(j, j+1);
            for (int i = 0; i < hand.length()-1; i++) {
                    if (hand.substring(i, i+1).equals(k)) {
                        numHas++;
                }
            }
            while (numHas >= 2) {
                ans += k + " ";
                numHas -= 2;
            }
        }
        numHas = 0;
        for (int i = 0; i < hand.length()-1; i++) {
            if (hand.substring(i,i+2).equals("10")) {
                numHas++;
            }  
        }
        while (numHas >= 2) {
            ans += "10 ";
            numHas -= 2;
        }
        if (isString) {
            if (ans.length()>0) {
                return removeCards(hand, ans);
            } else {
                return hand;
            }
        } else {
            return ans;
        }
    }
// FIX TENN i think i fixed it anyway but who cares

    private static String removeCards(String hand, String dupeChars) {
        int numHas = 0;
        String k;
        int increaseBy = 2;
        for (int i = 0; i < dupeChars.length(); i += increaseBy) { 
            increaseBy = 2;
            numHas = 2;
            k = dupeChars;
            while(numHas > 0) {
            for (int j = 0; j < hand.length()-1; j++) {
                    if(hand.substring(j, j+1).equals(k.substring(i,i+1)) && numHas > 0 && !hand.substring(j, j+2).equals("10")) {
                        numHas--;
                        hand = hand.substring(0, j) + hand.substring(j+3);
                        j = 0;
                    } else if (hand.substring(j, j+2).equals(k.substring(i, i+2)) && numHas > 0) {
                        numHas--;
                        hand = hand.substring(0, j) + hand.substring(j+4);
                        j = 0;
                        increaseBy = 3;
                    }
                }
            }
        }
        return hand;
    }

    private static String changeStolenHands(String resultCard, String playerHand, String whoStolen, String cpuHand1, String cpuHand2, String cpuHand3) {
        if (whoStolen.equals("cpu1")) {
            return goFishCheckVictim(cpuHand1, resultCard, whoStolen);
        } else if (whoStolen.equals("cpu2")) {
            return goFishCheckVictim(cpuHand2, resultCard, whoStolen);
        } else if (whoStolen.equals("cpu3")){
            return goFishCheckVictim(cpuHand3, resultCard, whoStolen);
        } else {
            return goFishCheckVictim(playerHand, resultCard, whoStolen);
        }
                   
    }


    private static String goFishCheckVictim(String hand, String resultCard, String whoStolen) {
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

    private static String checkGoFishThief(String hand, String resultCard, String name) {
        String ans = "";
        resultCard = resultCard.toUpperCase();
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
        System.out.println("\nCongratulations! , " + name + " has " + "a " + resultCard + " in their deck! ");
        } else if (numHas == 0) {
            // Not Obvious but this prints GO FISH in large text.
            System.out.println("\n░██████╗░░█████╗░  ███████╗██╗░██████╗██╗░░██╗\n██╔════╝░██╔══██╗  ██╔════╝██║██╔════╝██║░░██\n██║░░██╗░██║░░██║  █████╗░░██║╚█████╗░███████\n██║░░╚██╗██║░░██║  ██╔══╝░░██║░╚═══██╗██╔══██║\n╚██████╔╝╚█████╔╝  ██║░░░░░██║██████╔╝██║░░██\n░╚═════╝░░╚════╝░  ╚═╝░░░░░╚═╝╚═════╝░╚═╝░░╚═╝");
            ans += addOrStartCards(1); 
        }
        return ans;
    }

    private static String cpuTurn(String hand, int cpuNum) throws InterruptedException { //Begins the player's turn, it returns the card the player chooses if it is acceptable and in their hand
        if(hand.length() > 1) {
            System.out.println("\n\n\n\n\n\n\n\n                          CPU" + cpuNum + " TURN \n_______________________________________________________________\n");
            System.out.println("What card would you like to ask for Please type 2-10 J Q K or A: ");
            Thread.sleep(3000);
            for (int i = 0; i < hand.length()-3; i++) {
                if (hand.substring(i,i+2).equals("10")) {
                    hand = hand.substring(0, i) + hand.substring(i+4);
                }
            }
            int numCards = hand.length()/3;
            int i = (int)(Math.random()*numCards);
            String ans = hand.substring(i*3, (i*3)+1);
            if (ans.equals("1")) 
                ans = "10";
            System.out.println(ans);
            return ans;
        } else {
            System.out.println("You have no cards, Go Fish");
            return " ";
        }
        
    }

    private static String cpuStealCard(String resultCard, String cpuHand1, String cpuHand2, String cpuHand3, String playerHand, String Enemy1, String Enemy2) throws InterruptedException {
        System.out.println("Who would you like to take from? Player, " + Enemy1 + ", or " + Enemy2);
        Thread.sleep(3000);
        int randomChoose = (int)(Math.random()*3)+1;
        if (randomChoose == 1) {
            System.out.println("player");
            return "player";
        } else if (randomChoose == 2) {
            System.out.println(Enemy1);
            return Enemy1;
        } else {
            System.out.println(Enemy2);
            return Enemy2;
        }
    }

    private static String playerTurn(String playerHand) { //Begins the player's turn, it returns the card the player chooses if it is acceptable and in their hand
        if(playerHand.length() > 1) {
            boolean turn = true;
            System.out.println("\n\n\n\n\n\n\n\n                          PLAYER TURN \n_______________________________________________________________\n");
            System.out.println("Your Hand: " + playerHand);
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
        } else {
            return " ";
        }
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

    private static String stealHands(String resultCard, String playerHand, String whoStolen, String cpuHand1, String cpuHand2, String cpuHand3) {
        
        if (whoStolen.equals("cpu1")) {
            return checkGoFishThief(cpuHand1, resultCard, whoStolen);
        } else if (whoStolen.equals("cpu2")) {
            return checkGoFishThief(cpuHand2, resultCard, whoStolen);
        } else if (whoStolen.equals("cpu3")) { 
            return checkGoFishThief(cpuHand3, resultCard, whoStolen);
        } else {
            return checkGoFishThief(playerHand, resultCard, whoStolen);
        }
    } 
    

    private static void printAllCards(String playerHand, String cpuHand1, String cpuHand2, String cpuHand3, int playerScore, int cpu1Score, int cpu2Score, int cpu3Score) {
        System.out.println("Player: " + playerHand + "Score: " + playerScore);
        System.out.println("CPU1: " + cpuHand1 + "Score: " + cpu1Score);    
        System.out.println("CPU2: " + cpuHand2 + "Score: " + cpu2Score);
        System.out.println("CPU3: " + cpuHand3 + "Score: " + cpu3Score);



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
        for (int i = 0; i < numCardsAdded; i++) {
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

/* Checklist to be complete
 * Add more Delays to make the game run a little smoother
 * Add more text pick up card when you have none ext.
 * Fix cpu2 asking cpu1 for 6 and recieving one even though cpu1 doesnt have a 6 bug
 * Show GOFISH card regardless of if it becomes a pair or not
 * Nevermind you need to fix CPU2 logic is broken somewhere ;-;, nevermind i fixed it its another capital thing so the yest is just QoL changes which is a really good spot to be in
 * 
 */
