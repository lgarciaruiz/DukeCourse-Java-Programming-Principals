
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //replaces line break with space
        st = st.replace('\n', ' ');
        MarkovZero markov = new MarkovZero();
        markov.setRandom(1024);
        markov.setTraining(st);
        //creates three sets of random text
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
        public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //replaces line break with space
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(273);
        //creates three sets of random text
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runTestingCode() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //replaces line break with space
        st = st.replace('\n', ' ');
        testingCode markov = new testingCode();
        markov.setTraining(st);
        markov.setRandom(101);
        //creates three sets of random text
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //replaces line break with space
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(371);
        //creates three sets of random text
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        //replaces line break with space
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(8);
        markov.setTraining(st);
        markov.setRandom(365);
        //creates three sets of random text
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    private void printOut(String s){
        //creates an array of strings (words) everytime it finds a space (not included)
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        //loops over the array of words
        for(int k=0; k < words.length; k++){
            //prints out word at every position with a space after it
            System.out.print(words[k]+ " ");
            //sets the line length to the word length found at kth position +1 for space
            psize += words[k].length() + 1;
            //checks if the line is greater than 60 chars > creates new line
            if (psize > 60) {
                System.out.println();
                //resets char counter
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
