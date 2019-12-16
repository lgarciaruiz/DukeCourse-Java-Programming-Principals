
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    //IMarkovModel is an interface that is being passed to this method
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        markov.setRandom(seed);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 1024);
        */
       /*
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 365);
        */
        
        /*MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 715);*/
        
                
        MarkovModel mThree = new MarkovModel(7);
        runModel(mThree, st, size, 953);

    }
    
    public void testHashMap() {
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        EfficientMarkovModel markov = new EfficientMarkovModel(2);
        runModel(markov,st,size,42);
    }
    
    public void testRunner() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        runModel(markov,st,size,615);
    }
    
    public void compareMethods(){
        MarkovModel mm = new MarkovModel(2);
        EfficientMarkovModel em = new EfficientMarkovModel(2);
        FileResource fr = new FileResource();
        String st = fr.asString();
        int size = 1000;
        int seed = 42;
        double Start = System.nanoTime();
        runModel(mm,st,size,seed);
        double end = System.nanoTime();
        double time = ((Start - end));
        System.out.println(mm.toString() + " took " + time);
        double mStart = System.nanoTime();
        runModel(em,st,size,seed);
        double mend = System.nanoTime();
        double mtime = ((mStart - mend));
        System.out.println(em.toString() + " took " + mtime);
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
    
}
