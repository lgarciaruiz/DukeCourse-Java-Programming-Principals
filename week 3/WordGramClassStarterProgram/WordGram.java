
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        //copys array from a source, starting position to a new array of a certain size
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    //returns wordgram at index position; throws expection if does not exist.
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < myWords.length;i++) {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }
    
    //returns true if they are the same false otherwise
    public boolean equals(Object o) {
        //casting object parameter as wordgram so it knows to compare the two
        WordGram other = (WordGram) o;
        //checks if the length of the two objects aren't the same; this refers to the object passed in as param
        if (this.length() != other.length()){
            return false;
        }
        //checks if the
        for (int i = 0; i < myWords.length; i++){
            if (! myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;     
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] list = new String[myWords.length];
        for (int i = 0; i < out.length()-1; i++){
            list[i] = out.wordAt(i+1);
        }
        list[myWords.length-1] = word;
        out = new WordGram(list, 0, myWords.length);
        return out;
    }
    
    public int hashCode() {
        String words = toString();
        //hashCode is from string object
        return words.hashCode(); 
    }

}