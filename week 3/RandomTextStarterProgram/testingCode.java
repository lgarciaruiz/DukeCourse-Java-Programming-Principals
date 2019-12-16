import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class testingCode {
    private String myText;
    private Random myRandom;
    
    //constructor initializes myRandom to new random object
    public testingCode() {
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
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String>follows=new ArrayList<>();
        int pos = 0;
        while(pos < myText.length()-key.length()){
            int currentIdx = myText.indexOf(key,pos);

            if (currentIdx !=-1 && 
            currentIdx < myText.length()-key.length())
            {
                String followChar = myText.substring(currentIdx+key.length(), currentIdx+key.length()+1);
                follows.add(followChar);
                pos = currentIdx + key.length();
            }
            else {
                break;
            }
        }
        return follows;
    }

     public String getRandomText(int numChars){
        if (myText == null)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String currentChar=myText.substring(index,index+1);
        System.out.println(currentChar);
        sb.append(currentChar);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows=getFollows(currentChar);
            if (follows.size()==0)
            {
                break;
            }
            index=myRandom.nextInt(follows.size());
            String next=follows.get(index);
            sb.append(next);
            currentChar = currentChar.substring(1) + next;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    /*
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> list = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()){
            int start = myText.indexOf(key,pos);
            if(start == -1) {
                break;
            }
            if(start + key.length() > myText.length()-1) {
                break;
            } 
            String found = myText.substring(start+key.length(),start+key.length() + 1);
            list.add(found);
            pos = start + key.length();            
        }
        return list;
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
     */
}
