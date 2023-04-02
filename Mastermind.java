import java.util.Scanner;

public class Mastermind {

    private final String[] beads = new String[]{"A", "B", "C", "D", "E", "F"};
    private String sequence;
    private final int sequenceLength  = 4;
    private final int attempts = 10;

    public Mastermind(){
        sequence = "";
        while(sequence.length() < sequenceLength){
            sequence += beads[(int)(Math.random() * sequenceLength)];
        }
    }

    public Mastermind(String sequence){
        this.sequence = sequence;
    }

    public String getSequence(){
        return sequence;
    }

    public boolean compareGuess(String guess){

        if(guess.equals(sequence)){
            return true;
        }

        String copy = sequence;
        int completelyRight = 0;
        int rightWrongSpot = 0;
        for(int g = 0; g < guess.length(); g++){

            for(int a = 0 ; a < copy.length(); a++){
                if(g == a && guess.substring(g, g+1).equals(copy.substring(a, a+1))){
                    completelyRight++;

                    if(a == 0)
                        copy = "X" + copy.substring(1);
                    else if(a == copy.length()-1)
                        copy = copy.substring(0, a) + "X";
                    else
                        copy = copy.substring(0, a) + "X" + copy.substring(a+1);

                    break;
                }
                else if(guess.substring(g, g+1).equals(copy.substring(a, a+1))){
                    rightWrongSpot++;

                    if(a == 0)
                        copy = "X" + copy.substring(1);
                    else if(a == copy.length()-1)
                        copy = copy.substring(0, a) + "X";
                    else
                        copy = copy.substring(0, a) + "X" + copy.substring(a+1);

                    break;
                }
            }
        }
        System.out.println("Completely Right: " + completelyRight + "\n" +
                "Right Key Wrong Spot: " + rightWrongSpot);
        return false;
    }

    public void winGame(){

    }



}
