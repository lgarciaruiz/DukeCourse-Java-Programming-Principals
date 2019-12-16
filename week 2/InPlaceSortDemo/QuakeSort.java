import java.util.*;

public class QuakeSort {
    /*helper method that returns the index for the smallest 
      magnitude starting from variable int from to the end of the ArrayList
     */
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        //this set min index to the first variable
        int minIdx = from;
        //loop starts looking at the index next to "from"
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                //sets minIndex to current index if current index is smallest
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        for (int i=0; i < in.size(); i++){
            /* find the index of the smallest quake*/
            int minIndex = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            /*store the current indexes being compared 
             ie. smallest found with current index */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIndex);
            /*swap them by setting i to the place minIndex was found
             * and setting minIndex at the current position in the loop*/
            in.set(i,qmin);
            in.set(minIndex,qi);
        }
    }
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByMagnitude(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
}
