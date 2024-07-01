package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListTest {
	Integer[] numbers = { 30, -10, 20, 17, 30};
	LinkedList<Integer> list; 
	@BeforeEach
	void setUp() {
		list = new LinkedList<>(List.of(numbers));
	}
	
	@Test
	void removeTest() {
		Integer[] expected = {-10, 20, 17, 30};
		list.remove();
		runTest(expected, list);
		LinkedList<Integer> emptyList = new LinkedList<Integer>();
		assertThrowsExactly(NoSuchElementException.class, () -> emptyList.remove());
	}
	
	@Test
	void subListTest() {
		List<Integer> subList = list.subList(3, 5);
		Integer[] expected = { 17, 30 };
		runTest(expected, subList);
	}
	
	@Test
	void sublistAsViewTest() {
		List<Integer> subList = list.subList(3, 5);
		subList.add(1, 40);
		subList.add(500);
		subList.add(100);
		Integer[] expected = { 30, -10, 20, 17, 40, 30, 500, 100 };
		runTest(expected, list);
		subList.add(100);
		
		
	}
	
	private void runTest(Integer[] expected, List<Integer> list ) {
		assertArrayEquals(expected, list.toArray(Integer[]::new));
	}

}
