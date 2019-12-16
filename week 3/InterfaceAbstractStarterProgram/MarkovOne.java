import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class MarkovOne extends AbstractMarkovModel{
    //constructor initializes myRandom to new random object
    public MarkovOne() {
        myRandom = new Random();
    }
    
    //this helps generate the same random text each time; helps in testing
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
        //start an empty string
        StringBuilder sb = new StringBuilder();
        //get the index to a random character in training text
        int index = myRandom.nextInt(myText.length()-1);
        //set the key as a string equal to the index and only the amount of chars needed
        String key = myText.substring(index,index+1);
        //add the first key to the string since this will be the first character that will determine the next characters
        sb.append(key);
        //creates random text that is numChars long - 1 because the second to the last char will deteremine what the last char is
        for(int k=0; k < numChars-1; k++){
            //get all the chars the follow the key
            ArrayList<String> follows = getFollows(key);
            //if no char follow then break loop
            if(follows.size() == 0){
                break;
            }
            //reset index to to the index from a random char in follows 
            index = myRandom.nextInt(follows.size());
            //get the next string to be added based on the random index
            String next = follows.get(index);
            //add new char as a string to the string
            sb.append(next);
            //reset the key to the string (char) last added to the string
            key = next;
        }
        //returns StringBuilder as a string
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1";
    }
}
