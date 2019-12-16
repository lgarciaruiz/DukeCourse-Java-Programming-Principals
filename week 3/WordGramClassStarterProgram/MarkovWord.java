import java.util.*;
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    MarkovWord(int numWords) {
        myRandom = new Random();
        myOrder = numWords;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length; i++) {
            if (i + myOrder - 1 > words.length-1) {
                return -1;
            }
            WordGram currWords = new WordGram(words,i,myOrder);
            if (currWords.equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> words = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText,kGram,pos);
            //System.out.println("this is the start index " + start);
            if (start == -1) {
                break;
            }
            //System.out.println("start and order combined " + (start + myOrder));
            if (start + myOrder - 1 >= myText.length - 1) {
                break;
            }
            String next = myText[start + myOrder];
            words.add(next);
            //System.out.println(next);
            pos = start + 1;
            //System.out.println("position is "+pos);
        }
        
        return words;
    }
    
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder); // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString() + " ");
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            //System.out.println("Key word is "+ key);
            //System.out.println("Words that follow are "+ follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next + " ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }
    
    public void testinIndexOf(){
        String s = "this is";
        setTraining("this is just a test yes this is a simple test");
        String[] gram = s.split("\\s+");
        WordGram words = new WordGram(gram,0,2);
        System.out.println(words);
        System.out.println(indexOf(myText,words,0));
        System.out.println(getFollows(words));
    }
    
    public String toString() {
        return "MarkovWord to the order of " + myOrder;
    }
}
