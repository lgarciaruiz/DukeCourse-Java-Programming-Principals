import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String q1Word = q1.getInfo().substring(q1.getInfo().lastIndexOf(" "));
        String q2Word = q2.getInfo().substring(q2.getInfo().lastIndexOf(" "));
        
        int comp = q1Word.compareTo(q2Word);
        if(comp == 0) {
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
        return comp;
    }
}
