
/**
 * Write a description of DepthFilter here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(double min, double max, String name) {
        minDepth = min;
        maxDepth = max;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }
}
