package telran.interviews;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class AutoCompletion {
	//TODO data structure
	private TreeSet<String> wordsSet = new TreeSet<>(String::compareToIgnoreCase);
	private TreeMap<String,String> twoLetterPrefixHelper = new TreeMap<>();
			
	public boolean addWord(String word) {
		boolean resultOfAdd = wordsSet.add(word);
		//TODO adds new word into autocompletion variants
		//return true if added, false otherwise ( if a given word already exists
		if (resultOfAdd) {
			addToHelperMap(word);
		}
		return resultOfAdd;
	}
	
	private void addToHelperMap(String word) {
		String key = word.substring(0, 2).toLowerCase();
		twoLetterPrefixHelper.merge(key, word, (oldVal,newVal) -> oldVal.compareToIgnoreCase(newVal) < 0 ? oldVal : newVal);
	}

	public String[] getVariants(String prefix) {
		String key = prefix.substring(0, 2).toLowerCase();
		Map.Entry<String,String> entry = twoLetterPrefixHelper.higherEntry(key);
		String[] result = null;
		if (entry == null ) {
			result = wordsSet.tailSet(prefix,true).toArray(String[]::new);
		} else {
			result = wordsSet.subSet(prefix, true, entry.getValue(), false).toArray(String[]::new);
		}
		return result;
	}

}
