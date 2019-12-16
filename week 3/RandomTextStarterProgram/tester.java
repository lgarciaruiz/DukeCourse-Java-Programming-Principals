import java.util.*;
import edu.duke.*;
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester {
    public void testGetFollows() {
        MarkovOne mOne = new MarkovOne();
        mOne.setTraining("aaab");
        ArrayList <String> follows = mOne.getFollows("aa");
        printArray(follows);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne mOne = new MarkovOne();
        FileResource fr = new FileResource();
        mOne.setTraining(fr.asString());
        ArrayList<String> follows = mOne.getFollows("he");
        printArray(follows);
    }
    
    public void printArray(ArrayList<String> list) {
        for(String s : list) {
            System.out.println(s);
        }
        System.out.println("Size of list is "+list.size());
    }
}
