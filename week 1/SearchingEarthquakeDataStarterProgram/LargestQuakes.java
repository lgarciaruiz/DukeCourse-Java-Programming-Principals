import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        /*
        for (QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
        System.out.println("Amount of quakes read " + list.size());
        
        int index = indexOfLargest(list);
        System.out.println("Index of largest magnitude quake is " + index + " for quake: " + list.get(index));
        
        int amount = 50;
        ArrayList<QuakeEntry> largestQuakes = getLargest(list,amount);
        System.out.println(amount + "largest quakes are: \n");
        for (QuakeEntry qe : largestQuakes){
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        for (int i=1; i < data.size(); i++){
            QuakeEntry currQuake = data.get(i);
            if(currQuake.getMagnitude() > data.get(index).getMagnitude()){
                index = i;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        if(howMany > quakeData.size()){
            return quakeData;
        }
        for (int i=0; i < howMany; i++){
            int index = indexOfLargest(copy);
            answer.add(copy.get(index));
            copy.remove(index);
        }
        
        return answer;
    }
}
