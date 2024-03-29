
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String filterName;
    
    public MagnitudeFilter(double min, double max, String name) {
        minMag = min;
        maxMag = max;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }
}
