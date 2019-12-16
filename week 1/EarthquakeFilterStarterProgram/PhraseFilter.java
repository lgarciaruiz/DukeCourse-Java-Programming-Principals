
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Leonardo Garcia
 * @version v1
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String whereIn, String phraseIn, String name) {
        where = whereIn;
        phrase = phraseIn;
        filterName = name;
    }
    
    public String getName() {
        return filterName;
    }
    
    
    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start")){
            return qe.getInfo().indexOf(phrase) == 0;
        }
        else if (where.equals("any")){
            return qe.getInfo().indexOf(phrase) != -1;
        }
        else if (where.equals("end")){
            return qe.getInfo().lastIndexOf(phrase) >= phrase.length();
        }
        return false;
    }
}
