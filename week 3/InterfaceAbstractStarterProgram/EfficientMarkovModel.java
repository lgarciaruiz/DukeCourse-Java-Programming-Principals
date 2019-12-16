import java.util.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int numberOfChars;
    private HashMap<String,ArrayList<String>> map;
    //constructor initializes myRandom to new random object
    public EfficientMarkovModel(int numChars) {
        map = new HashMap<String,ArrayList<String>>();
        myRandom = new Random();
        numberOfChars = numChars;
    }
    
    private void buildMap() {
        int pos = 0; 
        String key = myText.substring(pos,numberOfChars);
        while(pos < myText.length()) {
            if (! map.containsKey(key)){
                ArrayList<String> list = new ArrayList<String>();
                map.put(key,list);
            }
            //System.out.println("start " + start);
            if(numberOfChars + pos >= myText.length()-1){
               break; 
            }
            else {
                String next = myText.substring(pos+key.length(), pos+key.length()+1);
                map.get(key).add(next);
            }
            pos += 1;
            //System.out.println("position is " + pos);
            key = myText.substring(pos,pos+numberOfChars);
            //System.out.println("key " + key);
        }
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> list = new ArrayList<String>();
        if (map.containsKey(key)){
            list = map.get(key);
        }
        return list;
    }
    
    public void printHashMapInfo() {
        System.out.println("/////////Print map info/////////\n" + map + "\nAmount of keys " + map.size());
        int counter = 0;
        for (String s : map.keySet()) {
            int curr = map.get(s).size();
            if (curr > counter){
                counter = curr;
            }
        }
        System.out.println("Size of largest arrayList " + counter + "\n" + "keys with this size are");
        for (String s : map.keySet()) {
            int curr = map.get(s).size();
            if (curr == counter){
                System.out.println(s);
            }
        }
        System.out.println("////////END Print map inf////////");
    } 
    
    //this helps generat the same random text each time; helps in testing
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    //initializes the training text; DO NOT MODIFY
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int Chars){
        map.clear();
        if (myText == null){
            return "";
        }
        //start an empty string
        StringBuilder sb = new StringBuilder();
        //get the index to a random character in training text
        int index = myRandom.nextInt(myText.length()-numberOfChars);
        //set the key as a string equal to the index and only the amount of chars needed
        String key = myText.substring(index,index+numberOfChars);
        //add the first key to the string since this will be the first character that will determine the next characters
        sb.append(key);
        buildMap();
        printHashMapInfo();
        int count = 0;
        //creates random text that is numChars long - 1 because the second to the last char will deteremine what the last char is
        for(int k=0; k < Chars-numberOfChars; k++){
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
            key = key.substring(1) + next;
        }
        //returns StringBuilder as a string
        return sb.toString();
    }

    public String toString() {
        return "EfficientMarkovModel of order " + numberOfChars;
    }

}
