import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    //array list of Filter interface type
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    //method to add filter to array list
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    
    public String getName(){
        StringBuilder names = new StringBuilder();
        for (Filter f : filters){
            names.append(f.getName() + " ");
        }    
        return names.toString();
    }
    
    //checks if all filters are true
    public boolean satisfies(QuakeEntry qe){
        for (Filter f : filters){
            if (! f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
