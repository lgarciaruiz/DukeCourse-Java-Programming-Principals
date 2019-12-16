
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    //protected fields
    protected String myText;
    protected Random myRandom;
    
    //constructor initializing random
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    //sets training text
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    //signature for abstract method indicates length of randomly generated text
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
}
