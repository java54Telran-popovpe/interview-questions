package telran.interviews;

import java.util.TreeSet;

public class AutoCompletion {
	//TODO data structure
	private TreeSet<String> wordsSet = new TreeSet<>(String::compareToIgnoreCase);
			
	public boolean addWord(String word) {
		return wordsSet.add(word);
	}

	public String[] getVariants(String prefix) {
		String newLastChar = String.valueOf((char)(prefix.charAt(prefix.length() - 1 ) + 1 ) );
		String upperBound = prefix.substring(0, prefix.length() -1 ).concat( newLastChar );
		return wordsSet.subSet(prefix, upperBound ).toArray(String[]::new);
	}

}
