import java.util.*;

public class QuakeSort {
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for(QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }
        return min;
    }
    
    /*This is a selection sort, it involves looking at each element and picking the smallest element 
    running through every element that needs to be sorted*/
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        //out starts as empty ArrayList
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        //As long as in is not empty
        while(!in.isEmpty()) {
            //Find smallest element in in (minElement)
            QuakeEntry miElememt = getSmallestMagnitude(in);
            //Remove minElement from in
            in.remove(miElememt);
            //Add minElement to out
            out.add(miElememt);
        }
        //out is the answer
        return out;
    }
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        list = sortByMagnitude(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

}
