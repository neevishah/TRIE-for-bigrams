import java.util.ArrayList;
import java.util.HashMap;

import tokenizer.SimpleTokenizer;

public class Q3 {

	private static HashMap<String, HashMap <String, Integer>> trie = new HashMap<String, HashMap <String, Integer>>();
	private static 	int freq = 0;
	
	public static void buildTrie(String s) {
		
		//tokenize string
		SimpleTokenizer tok1 = new SimpleTokenizer();
		ArrayList<String> tokens = tok1.tokenize(s);
		//System.out.println(tokens);
		
		//for loop for i=0 i<1 word before the end bc we want pairs of words and the last word won't ever be the outer key
		
		for (int i = 0; i < (tokens.size()-1) ; i++) {
			//take ith and i+1 terms and add that into a hashmap
			String term = (tokens.get(i));
			//System.out.println(term);
				
			//first time word is present. 1st word = key for outer hashmap
			if (trie.containsKey(term)==false) {
				trie.put(term, new HashMap <String, Integer>());
				trie.get(term).put(tokens.get(i+1), 1);
			}
			
			//word is present, but pairing is new
			else if (trie.containsKey(term) && trie.get(term).get(tokens.get(i+1)) == null) {
				trie.putIfAbsent(term, new HashMap <String, Integer>()); //if it doesnt already exist, it will put
				trie.get(term).put(tokens.get(i+1), 1);//set freq to 1
			}
			
			//pairing shows up again, so increase freq					
			else if (trie.get(term).get(tokens.get(i+1)) != null) {
				freq = trie.get(term).get(tokens.get(i+1));
				trie.get(term).put(tokens.get(i+1), freq + 1);
			}
		}
		
		//System.out.println(trie);
	}
	
	public static int getFreq (String s1, String s2) {		
		if (trie.containsKey(s1) && trie.get(s1).get(s2) != null) { //if pair exists
			freq = trie.get(s1).get(s2); //get freq of pair
		}
		
		else {
			freq = 0; //otherwise freq = 0
		}
		
		return freq;
	}
	
	public static void main (String [] args) {
		buildTrie("to be or not to be Shakespeare");
		buildTrie("a b c");
		
		System.out.println(getFreq("a" , "b")); //1
		System.out.println(getFreq("to" , "not")); //0
		System.out.println(getFreq("or" , "not")); //1
		System.out.println(getFreq("to" , "be"));  //2
	}
}	