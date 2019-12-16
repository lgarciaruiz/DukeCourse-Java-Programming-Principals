
/**
 * Write a description of class QuakeSortInPlace here.
 * learning bubble sort and selection sort both are run time of n squared/n to the power of 2/ and are quadratic.
 * bubble sorts by looking at elements right next to each other (adjacent). This will always end up with the biggest number in the right place on the first pass
 * selection sorts by looking for the smallest element each time it runs. this will always end up with the smallest number in the right place on the first pass
 * @author Leonardo Garcia 
 * @version v1
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        // only loops if the ArrayList is not in order
        for (int i = 0; !checkInSortedOrder(in); i++){
            //helper function that does the sorting
            onePassBubbleSort(in,i);
            passes ++;
        }
        System.out.println("Amount of passes = " + passes);
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        int index = 0;
        for (int i = 1; i < quakes.size(); i++){
            if(quakes.get(index).getMagnitude() > quakes.get(i).getMagnitude()) {
                return false;
            }
            index = i;
        }
        return true;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //index to compare against 1st item in arraylist
        int index = 0;
        //loop over items starting at second item
        //loop over the size of the array minus the amount of times this method has been called (numSorted)
        //numSorted would also be the amount of items already in order - this are at the end of the arraylist
        for(int i = 1; i < quakeData.size() - numSorted; i++) {
            //compare the magnitudes
            if(quakeData.get(index).getMagnitude() > quakeData.get(i).getMagnitude()){
                //swap the magnitudes
                QuakeEntry qIndex = quakeData.get(index);
                QuakeEntry qi = quakeData.get(i);
                quakeData.set(index,qi);
                quakeData.set(i,qIndex);
            }
            //update index to compare next two items in arraylist MUST BE OUTSIDE IF STATEMENT
            index = i;
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        //loop over the ArrayList - 1 because the last number should not be compared since it is the last number
        for (int i = 0; i < in.size() - 1; i++){
            //helper function that does the sorting
            onePassBubbleSort(in,i);
        }
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakes) {
        for(int i = 0; i < 70; i++){
            int minIndex = getLargestDepth(quakes,i);
            QuakeEntry qi = quakes.get(i);
            QuakeEntry qMin = quakes.get(minIndex);
            quakes.set(i,qMin);
            quakes.set(minIndex,qi);
        }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int minIndex = from;
        for (int i = from + 1; i < quakes.size(); i++){
            if (quakes.get(i).getDepth() > quakes.get(minIndex).getDepth()){
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; !checkInSortedOrder(in); i++){
            int minIndex = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qMin = in.get(minIndex);
            in.set(i,qMin);
            in.set(minIndex,qi);
            passes ++;
        }
        System.out.println("Amount of passes = " + passes);
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 

    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
