package Assignments;

import java.util.Scanner;

public class Hangman {

    public static final int MAX_BODY_PARTS = 7;
    public static Scanner in = new Scanner(System.in);
    public static final String VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LETTER = 1;
    public static void main(String[] args) {
        String phrase = getPhrase();
        int numWrong = 0;
        for (int i = 0; i < 30; i++) {
            System.out.println(" ");
        }

       String chosenLetters = "";
       
       while(numWrong<MAX_BODY_PARTS) {
            int option = getPlayerOption();
            if (option == LETTER) {
                displayEncodedPhrase(phrase, chosenLetters);

                String letter = getLetter(chosenLetters);
                chosenLetters += letter;

                if (!isInPhrase(letter, phrase)) {
                        numWrong++;
                        drawHangman(numWrong);
                } 
            } else {
                // get solution
            }

                if (numWrong == MAX_BODY_PARTS) {
                        System.out.println("Mission failed, we'll get em next time");
                }
        
        }
    }
    
    
    private static int getPlayerOption() {
        // 1 for letter
        // 2 for solve
        return 1;
    }


    private static boolean isInPhrase(String letter, String phrase) {
        return phrase.indexOf(letter) >= 0;
    }


    private static String getLetter(String chosenLetters) {
        boolean validLetter = false;
        while (true) {
            System.out.println("Please enter a letter. Chosen Letters are: ");
            getLettersString(chosenLetters);
            String letter = in.nextLine().toUpperCase();

            if(letter.length() > 1) 
                System.out.println("Please only enter 1 character");
            else if (VALID_CHARS.indexOf(letter) < 0)
                System.out.println("Please only enter letters A-Z");
            else if (chosenLetters.indexOf(letter)>=0)
                System.out.println("You allready chose that letter");
            else
                return letter;

        }
    }


    private static void getLettersString(String chosenLetters) {
        displayEncodedPhrase(VALID_CHARS, chosenLetters);
    }


    private static void displayEncodedPhrase(String phrase, String chosenLetters) {
        String result = "";
        for (int i = 0; i < phrase.length(); i++) {
            String letter = phrase.substring(i, i+1);
            if (letter.equals(" "))
                result += "/ ";
            else if (chosenLetters.indexOf(letter) >= 0)
                result += (letter + " ");
            else  
                result += "_ ";         
        }
        
        System.out.println(result);
    }


    private static String getPhrase() {
        System.out.println("Please enter a valid Phrase/Word with a-z and [space]:");
        String result = in.nextLine().toUpperCase();
        for (int i = 0; i < result.length(); i++) {
            if ((VALID_CHARS + " ").indexOf(result.substring(i, i+1)) < 0) {
                System.out.println("Invalid Phrase, Please use only a-z and [space]: ");
                result = in.nextLine().toUpperCase();
                i = 0;
            }
        }
        return result;
    }

    public static void drawHangman(int numBodyParts) {
      if(numBodyParts == 0) {
        System.out.println(" _____ ");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("L_    ");
      }  else if (numBodyParts == 1) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("L_    ");
      } else if (numBodyParts == 2) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|     ");
        System.out.println("|     ");
        System.out.println("L_    ");
      } else if (numBodyParts == 3) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|    /|");
        System.out.println("|     ");
        System.out.println("L_    ");
      } else if (numBodyParts == 4) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|    /|\\");
        System.out.println("|     ");
        System.out.println("L_    ");
      } else if (numBodyParts == 5) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|    /|\\");
        System.out.println("|     |");
        System.out.println("L_    ");
      } else if (numBodyParts == 6) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|    /|\\");
        System.out.println("|     |");
        System.out.println("L_  _/");
      } else if (numBodyParts == 7) {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println("|     o");
        System.out.println("|    /|\\");
        System.out.println("|     |");
        System.out.println("L_  _/ \\_");
      }
    }
}
