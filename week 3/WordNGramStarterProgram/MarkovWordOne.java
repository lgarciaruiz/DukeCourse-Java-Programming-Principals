/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 1); // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for (int k = 0; k < numWords - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key word is "+ key);
            //System.out.println("Words that follow are "+ follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target, int start) {
        for(int i = start; i < words.length;i++){
            if (words[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList < String > ();
        int pos = 0;
        while (pos < myText.length){
            int start = indexOf(myText,key,pos);
            if(start == -1) {
                break;
            }
            if(start >= myText.length-1) {
                break;
            } 
            String next = myText[start + 1];
            follows.add(next);
            pos = start + 1;            
        }
        return follows;
    }
    
    public void testIndexOf() {
        setTraining("this is just a test yes this is a simple test");
        System.out.println("This 0 " + indexOf(myText,"this",0));
        System.out.println("This 3 " + indexOf(myText,"this",3));
        System.out.println("Frog 0 " + indexOf(myText,"frog",0));
        System.out.println("Frog 5 " + indexOf(myText,"frog",5));
        System.out.println("Simple 2 " + indexOf(myText,"simple",2));
        System.out.println("Test 5 " + indexOf(myText,"test",5));
    }

}