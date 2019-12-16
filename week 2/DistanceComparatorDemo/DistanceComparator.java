import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double distance1 = q1.getLocation().distanceTo(fromWhere);
        double distance2 = q2.getLocation().distanceTo(fromWhere);
        //this will return a int ex. -1,1 or 0 depending on 
        //if distance 1 is smaller, larger or the same, respectively
        return Double.compare(distance1,distance2);
    }
}
