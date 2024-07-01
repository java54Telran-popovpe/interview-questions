package telran.interviews;

import java.util.TreeSet;

public class AutoCompletion {
	//TODO data structure
	private TreeSet<String> wordsSet = new TreeSet<>(String::compareToIgnoreCase);
			
	public boolean addWord(String word) {
		//TODO adds new word into autocompletion variants
		//return true if added, false otherwise ( if a given word already exists
		return wordsSet.add(word);
	}
	
	public String[] getVariants(String prefix) {
		return wordsSet.tailSet(prefix,true)
				.stream()
				.takeWhile(t -> t.toLowerCase().contains(prefix.toLowerCase()))
				.toArray(String[]::new);
	}

}
