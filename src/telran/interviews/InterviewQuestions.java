package telran.interviews;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InterviewQuestions {
	public static void displayOccurences(String[] strings) {
		HashMap<String,Integer> mapOccurences = getOccurencesMap(strings);
		TreeMap<Integer,TreeSet<String>> treeMapOccurences = getTreeMapOccurences(mapOccurences);
		displayOccurences(treeMapOccurences);
	}
	public static void displayOccurencesStream(String[] strings) {
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream().sorted((e1, e2) -> {
			int res = Long.compare(e2.getValue(), e1.getValue());
			return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
		}).forEachOrdered(e -> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()));
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
		HashSet<Integer> hashSet = new HashSet<>();
		int i = 0;
		while( i < array.length && !hashSet.contains(array[i])) {
			hashSet.add(sum - array[i]);
			i++;
		}
		return i == array.length ? false : true;
	}
	static public int getMaxWithNegativePresentation(int [] array) {
		//TODO
		//returns maximal positive value for whitch exists negative value with the same abs value
		//if no pair positive and negative returns -1
		HashSet<Integer> hashSet = new HashSet<>();
		int result = -1;
		for (int i = 0; i < array.length; i++ ) {
			if ( hashSet.contains( -array[i])) {
				result = Math.max(result, Math.abs(array[i]));
			} else {
				hashSet.add(array[i]);
			}
		}
		return result;
		
	}
	
	public static Map<Integer, Integer> getMapSquares(List<Integer> numbers) {
		Map<Integer,Integer> res = numbers.stream().collect(Collectors.toMap(Function.identity(), n -> n * n, (v1, v2) -> v1, LinkedHashMap::new));
		return res;
	}
	
	public static boolean isAnagramStream(String word, String anagram) {
		boolean result = false;
		if ( word.length() == anagram.length() && !word.equals(anagram)) {
			HashMap<Integer,Long> helperMap = word.chars().boxed().collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));
			result =  anagram.chars().allMatch( cp ->  helperMap.computeIfPresent(cp, ( k, v ) -> v == 0 ? null : --v ) != null );
		}
		return result;
	}
	
	//This implementation does not use compareTo method for strings
	public static boolean isAnagram(String word, String anagram) {
		//TODO
		//returns true if "anagram" string conains all letters from "word" in another order
		//O[n] sorting is disallowed
		//case sensitive
		boolean result = false;
		HashMap<Character,LinkedList<Integer>> wordChars;
		int wordLength = word.length();
		if (wordLength == anagram.length()) {
			wordChars = putStringToCollection(word,wordLength);
			result = applayStringOnCollection(wordChars, anagram);
		}
		return result;
	}

	private static boolean applayStringOnCollection(HashMap<Character, LinkedList<Integer>> wordChars, String anagram) {
		boolean stringEquals = true;
		boolean charNotFound = false;
		int index = 0;
		int length = anagram.length();
		while( index < length && !charNotFound) {
			Optional<Integer> charIndex = getCharIndex(wordChars, anagram.charAt(index));
			charNotFound = charIndex.isEmpty();
			if (!charNotFound && stringEquals) {
				stringEquals = ( index == charIndex.get() );
			}
			index++;
		}
		return !charNotFound && !stringEquals;
	}
	private static Optional<Integer> getCharIndex(HashMap<Character, LinkedList<Integer>> wordChars, Character charToSearch) {
		Optional<Integer> result = Optional.empty();
		LinkedList<Integer> indexes = wordChars.get(charToSearch);
		if ( indexes != null ) {
			result = Optional.ofNullable(indexes.pollFirst());
		}
		return result;
	}
	
	private static HashMap<Character, LinkedList<Integer>> putStringToCollection(String word, int wordLength) {
//		Implementation using streams.		
//		char[] chars = word.toCharArray();
//		return  IntStream.range(0, chars.length).mapToObj( c -> Integer.valueOf(c))
//			.collect(Collectors.groupingBy(i -> Character.valueOf(chars[i]), HashMap::new, Collectors.toCollection(LinkedList::new)));
		
		HashMap<Character,LinkedList<Integer>> wordChars = new HashMap<>();
		for( int i = 0; i < wordLength; i++ ) {
			Character charToAdd = word.charAt(i);
			wordChars.computeIfAbsent(charToAdd, k -> new LinkedList<Integer>()).add(i);
		}
		return wordChars;
	}
	
	
	public static List<DateRole> assignRolesDates(List<DateRole> rolesHistory, List<LocalDate> dates) {
		//TODO
		//create List<DateRole> with roles matching with given dates
		//most effective data structure
		TreeMap<LocalDate,String> rolesHistoryMap = 
				rolesHistory.stream().collect(Collectors.toMap(	r -> r.date(),
																r -> r.role(), 
																(v1, v2 ) -> v1,
																TreeMap::new));
		return dates.stream().map( d -> {
			Map.Entry<LocalDate,String> entry= rolesHistoryMap.floorEntry(d);
			return new DateRole(d, entry != null ? entry.getValue() : null);
		}).toList();
	}
	public static void displayDigitsStatistics() {
		//TODO
		//display out statistics in the following format (example)
		/* 1 -> <count of occurrences>
		 * 2 -> .....
		 * .........
		 */
		//sorted by counts of occurrences in the descending order
		//takes 1000000 random numbers in range [0-Integer.MAX_VALUE)
		//one pipeline with no additional yours methods
		new Random().ints(0, Integer.MAX_VALUE)
				.limit(1000000)
				.flatMap(n -> String.valueOf(n).chars())
				.mapToObj( n -> Integer.valueOf(n))
				.collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()))
				.entrySet()
				.stream()
				.forEachOrdered( entry -> System.out.printf("%s -> %d\n", 
															String.valueOf(Character.toChars(entry.getKey())), 
															entry.getValue()
															));
	}

}
