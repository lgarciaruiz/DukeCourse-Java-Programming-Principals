
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{    
    //constructor initializes myRandom to new random object
    public MarkovZero() {
        myRandom = new Random();
    }
    
    //this helps generat the same random text each time; helps in testing
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    //initializes the training text; DO NOT MODIFY
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        //creates random text that is numChars long
        for(int k=0; k < numChars; k++){
            //sets index to a random number from 0 to length of training text -1
            int index = myRandom.nextInt(myText.length());
            //adds a char (including none letters, eg. space ?.,) 
            //from the training text that exist at a random index spot 
            //generated above
            sb.append(myText.charAt(index));
        }
        //returns StringBuilder as a string
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 0";
    }
}
