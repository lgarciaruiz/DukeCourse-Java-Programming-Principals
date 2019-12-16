import java.util.*;
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author Leonardo Garcia 
 * @version v1
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        //compare strings
        int comp = q1.getInfo().compareTo(q2.getInfo());
        if (comp == 0){
            return Double.compare(q1.getDepth(),q2.getDepth());
        }
        return comp;
    }
}
