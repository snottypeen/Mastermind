import java.util.Scanner;

public class Gameplay {
    public static void main(String[] args) {
        Introduction();
    }

    public static void Introduction(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Mastermind! Enter 1 to play against a bot, 2 for two-player, and 3 for instructions: ");
        String choice = scan.nextLine();
        int gameMode = 0;
        while(gameMode == 0) {
            try{
                gameMode = Integer.parseInt(choice);
            }
            catch(NumberFormatException e){
                System.out.println("ACTION FAILED: Enter 1, 2, or 3:");
                choice = scan.nextLine();
            }
        }
        if(gameMode == 1) {
            System.out.println("Single-Player selected");
            singlePlayer(scan);
        }
        if(gameMode == 2) {
            System.out.println("Two-Player selected");
            twoPlayer(scan);
        }
        if(gameMode == 3){
            System.out.println("INSTRUCTIONS \n" +
                    "A four letter sequence will be randomly generated out of the\n" +
                    "letters A, B, C, D, E, and F. Duplicates are allowed. The goal\n" +
                    "is to guess the sequence correctly in 10 or less turns. After every \n" +
                    "guess two stats are given, one for how many letters have been put in\n" +
                    "the right place and one for the amount of letters guessed correctly but\n" +
                    "put in the wrong spot. However the letters correctly guessed or placed are\n" +
                    "not given directly. Therefore it is up to you to decipher where they belong!\n");
            Introduction();
        }
    }

    private static boolean checkSequenceValidity(String sequence){
        if(sequence.length() == 4) {
            int numSatisfied = 0;
            for (int x = 0; x < sequence.length(); x++) {
                if (sequence.substring(x, x + 1).equals("A"))
                    numSatisfied++;
                else if (sequence.substring(x, x + 1).equals("B"))
                    numSatisfied++;
                else if (sequence.substring(x, x + 1).equals("C"))
                    numSatisfied++;
                else if (sequence.substring(x, x + 1).equals("D"))
                    numSatisfied++;
                else if (sequence.substring(x, x + 1).equals("E"))
                    numSatisfied++;
                else if (sequence.substring(x, x + 1).equals("F"))
                    numSatisfied++;
            }

            if (numSatisfied == 4)
                return true;
        }
        return false;
    }

    public static void singlePlayer(Scanner scan){
        Mastermind game = new Mastermind();
        System.out.println("The sequence is set! Begin!");
        playGame(game, scan);
    }

    public static void twoPlayer(Scanner scan){
        String sequence = "";
        boolean sequenceSatisfies = false;
        while(!sequenceSatisfies){
            System.out.println("Player One please enter a 4 letter-sequence using the letters A, B, C, D, and E");
            sequence = scan.nextLine();
            sequence.toUpperCase();
            sequenceSatisfies = checkSequenceValidity(sequence);
            if(!sequenceSatisfies)
                System.out.println("Sequence Rules Violated: Try Again");
        }
        System.out.println("\n\n\n\n\n\n" + "DON'T SCROLL UP!");
        Mastermind game = new Mastermind(sequence);
        System.out.println("The sequence is set! Begin Player Two!");
        playGame(game, scan);
    }

    public static void playGame(Mastermind game, Scanner scan){
        int guessCount = 1;
        boolean hasWon = false;
        while(guessCount <= 10 && !hasWon){
            System.out.println("Guess number " + guessCount + ": " );
            String guess = scan.nextLine();
            while(!checkSequenceValidity(guess)){
                System.out.println("INVALID GUESS: Try again");
                guess = scan.nextLine();
            }
            hasWon = game.compareGuess(guess);
            guessCount++;
        }

        if(hasWon){
            System.out.println("You have solved the sequence! \n" +
                    game.getSequence());
        }
        else{
            System.out.println("YOU FAILED, now to reveal the sequence\n" +
                    game.getSequence());
        }
        System.out.println("Would you like to play again? Enter yes or no: ");
        String choice = scan.nextLine();
        while(!choice.toLowerCase().equals("yes") && !choice.toLowerCase().equals("no")) {
            System.out.println("TRY AGAIN: yes or no?");
            choice = scan.nextLine();
        }
        if(choice.toLowerCase().equals("yes"))
            Introduction();
        else if(choice.toLowerCase().equals("no")){
            System.out.println("Thanks for Playing!");
            scan.close();
        }
    }

}
