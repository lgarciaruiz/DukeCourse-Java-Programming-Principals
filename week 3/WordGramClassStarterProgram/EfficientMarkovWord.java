import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    
    EfficientMarkovWord(int numWords) {
        map = new HashMap<WordGram,ArrayList<String>>();
        myRandom = new Random();
        myOrder = numWords;
        
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        map.clear();
        myText = text.split("\\s+");
        buildMap();
        printHashMap();
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
        if (map.containsKey(kGram)){
            words = map.get(kGram);
        }        
        return words;
    }
    
    private void buildMap() {
        int pos = 0;
        WordGram key = new WordGram(myText, pos, myOrder);
        while (pos < myText.length) {
            if (! map.containsKey(key)) {
                ArrayList<String> list = new ArrayList<String>();
                map.put(key,list);
            }
            if (myOrder + pos > myText.length -1) {
                break;
            }
            else{
                String next = myText[pos + myOrder];
                map.get(key).add(next);
            }
            pos +=1;
            key = new WordGram(myText, pos, myOrder);
        }
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
    
    public void testIndexOf(){
        String s = "this is";
        setTraining("this is just a test yes this is a simple test");
        String[] gram = s.split("\\s+");
        WordGram words = new WordGram(gram,0,2);
        System.out.println(words);
        System.out.println(indexOf(myText,words,0));
        System.out.println(getFollows(words));
    }
    
    public void printHashMap() {
        int count = 0;
        int sizeLG = 0;
        for (WordGram wg : map.keySet()) {
            System.out.println(wg + " contains keys below:" + map.get(wg));
            count += 1;
            if (map.get(wg).size() > sizeLG) {
                sizeLG = map.get(wg).size();
            }
        }
        System.out.println("Amount of keys: " + count);
        System.out.println("Size of largest keyset: " + sizeLG);
        /*for (WordGram wg : map.keySet()){
            if (map.get(wg).size() == sizeLG) {
                System.out.println(wg + " with words " + map.get(wg));
            }
        }*/
    }
    
    public String toString() {
        return "MarkovWord to the order of " + myOrder;
    }
}
