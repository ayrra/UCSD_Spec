package spelling;

public class TrieTester {

	final String dictFile = "data/words.small.txt"; 
	
	public static void main(String[] args) {
		
		AutoCompleteDictionaryTrie testDict = new AutoCompleteDictionaryTrie();
		
		testDict.addWord("Hello");
		testDict.addWord("Hello");
		testDict.addWord("doge");
		testDict.addWord("dog");
		testDict.addWord("doc");
		testDict.addWord("docter");
		testDict.addWord("dock");
		testDict.addWord("dollar");
		testDict.addWord("dogs");
		testDict.addWord("cat");
		testDict.addWord("snout");
		testDict.addWord("camera");
		testDict.addWord("lens");
		testDict.addWord("Spider");
		System.out.println(testDict.predictCompletions("d", 4));
	}

}
