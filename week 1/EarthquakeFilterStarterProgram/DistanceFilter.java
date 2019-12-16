
/**
 * Write a description of DistanceFilter here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;
    private String filterName;
    
    public DistanceFilter(Location loc, double max, String name) {
        location = loc;
        maxDistance = max;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }
}
