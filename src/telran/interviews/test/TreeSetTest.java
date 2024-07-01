package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreeSetTest {
	
	TreeSet<Integer> treeSet;
	Integer[] numbers = { 10, -20, 30, -100, 80, 35, 200 };
	
	@BeforeEach
	void setUp() {
		treeSet = new TreeSet<>(List.of(numbers));
	}
	
	@Test
	void subSetAsViewRemoveTest() {
		Set<Integer> set = treeSet.subSet(0, true, 50, false);
		set.clear();
		Integer[] expected = { -20, -100, 80, 200,  }; 
		runTest(expected, treeSet);

	}
	
	@Test
	void subSetAsCopyRemoveTest() {
		List<Integer> list = treeSet.stream()
				.filter( n -> n >=0 && n <= 50)
				.collect(Collectors.toList());
		TreeSet<Integer> set = new TreeSet<Integer>(list);
		Integer[] expected = { 10, 30, 35, };
		runTest(expected, set);

	}
	
	@Test
	void subSetTest() {
		Set<Integer> set = treeSet.subSet(10, true, 40, false);
		Integer[] expected = { 30, 35, 10 }; 
		runTest(expected, set);
		set.add(31);
		expected = new Integer[]{ 30, 35, 10, 31 }; 
		runTest(expected, set);
	}
	
	@Test
	void subSetAsViewAddTest() {
		Set<Integer> set = treeSet.subSet(0, true, 50, false);
		set.add(20);
		Integer[] expected = { 10, -20, 30, -100, 80, 35, 200, 20 }; 
		runTest(expected, treeSet);
	}
	
	private void runTest(Integer[] expected, Set<Integer> set ) {
		Arrays.sort(expected);
		Integer[] actual = set.toArray( Integer[]::new);
		assertArrayEquals(expected, actual);
	}

}
