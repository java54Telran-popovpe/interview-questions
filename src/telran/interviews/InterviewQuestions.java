package telran.interviews;
import java.util.*;

public class InterviewQuestions {
	public static void displayOccurences(String[] strings) {
		HashMap<String,Integer> mapOccurences = getOccurencesMap(strings);
		TreeMap<Integer,TreeSet<String>> treeMapOccurences = getTreeMapOccurences(mapOccurences);
		displayOccurences(treeMapOccurences);
	}

	private static void displayOccurences(TreeMap<Integer, TreeSet<String>> treeMapOccurences) {
		treeMapOccurences.entrySet().forEach( e -> {
			e.getValue().forEach(str -> System.out.printf("%s => %d\n", str, e.getKey() ));
		});
	}

	private static TreeMap<Integer, TreeSet<String>> getTreeMapOccurences(HashMap<String, Integer> mapOccurences) {
		Comparator<Integer> comp = Integer::compare;
		TreeMap<Integer, TreeSet<String>> result = new TreeMap<>(comp.reversed());
		mapOccurences.entrySet()
			.forEach( e -> {
			result.computeIfAbsent(e.getValue(), k -> new TreeSet<>()).add(e.getKey());
		});
		return result;
	}

	private static HashMap<String, Integer> getOccurencesMap(String[] strings) {
		HashMap<String,Integer> result = new HashMap<>();
		for(String str: strings) {
			result.merge(str, 1, Integer::sum);
		}
		return result;
	}
	//hashset
	static public boolean isSum2(int[] array, int sum) {
		//TODO true if exist two element of sum 
		return false;
	}
	static public int getMaxWithNegativePresentation(int [] array) {
		//TODO
		//returns maximal positive value for whitch exists negative value with the same abs value
		//if no pair positive and negative returns -1
		return -1;
		
	}

}
