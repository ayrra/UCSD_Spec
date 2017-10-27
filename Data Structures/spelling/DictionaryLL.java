package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		this.dict = new LinkedList<String>();
	}

    /**Add this word to the dictionary.  Convert it to lowercase first, dont forget to check if its already in dict **/
    public boolean addWord(String word) {
    	if (!isWord(word)) {
	    	dict.add(word.toLowerCase());
	    	return true;
    	}
    	return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	if (dict.contains(s.toLowerCase())) {
    		return true;
    	}
    		return false;
    }

    
}
