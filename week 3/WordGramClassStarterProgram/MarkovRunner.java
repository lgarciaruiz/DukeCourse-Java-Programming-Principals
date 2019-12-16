
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            //markov.setRandom(seed);
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 50, 844); 
    } 
    
    public void runEfficientMarkovWord() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(6); 
        runModel(markovWord, st, 50, 792); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void testHashMap() {
        EfficientMarkovWord mk = new EfficientMarkovWord(2);
        mk.setTraining("this is a test yes this is really a test yes a test this is wow"); 
        mk.setRandom(42);
        mk.getRandomText(50);
    }
    
    public void compareMethods() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(2);
        double start = System.nanoTime();   
        runModel(markovWord, st, 1000, 42); 
        double end = System.nanoTime();
        double time = end - start;
        System.out.println(time);
        EfficientMarkovWord mk = new EfficientMarkovWord(2);
        double mstart = System.nanoTime();   
        runModel(mk, st, 1000, 42); 
        double mend = System.nanoTime();
        double mtime = mend - mstart;
        System.out.println(mtime);
    } 
}
