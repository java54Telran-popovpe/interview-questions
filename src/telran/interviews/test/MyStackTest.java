package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.MyStackInt;

class MyStackTest {

	MyStackInt stack = new MyStackInt();
	Integer[] initValues = { 5, -10, 45, -10, 84 };
	MyStackInt emptyStack = new MyStackInt();
	
	
	@BeforeEach
	void setUp( ) {
		for (Integer num: initValues) {
			stack.push(num);
		}
	}
	
	@Test
	void testPopAndIsEmpty() {
		LinkedList<Integer> result = new LinkedList<>();
		while(!stack.isEmpty()) {
			result.addFirst(stack.pop());
		}
		assertArrayEquals( initValues, result.toArray(Integer[]::new));
		assertThrowsExactly(NoSuchElementException.class, () -> stack.pop());
	}
	
	@Test
	void getMaxElementTest() {
		//{ 5, -10, 45, -10, 84 };
		assertEquals(84, stack.getMaxElement());
		stack.pop();
		//{ 5, -10, 45, -10,};
		assertEquals(45, stack.getMaxElement());
		stack.pop();
		//{ 5, -10, 45,};
		assertEquals(45, stack.getMaxElement());
		stack.pop();
		//{ 5, -10, };
		assertEquals(5, stack.getMaxElement());
		stack.pop();
		//{ 5, };
		assertEquals(5, stack.getMaxElement());
		stack.pop();
		//{ };
		assertThrowsExactly(NoSuchElementException.class, () -> stack.getMaxElement());
	}
	
	@Test
	void peekTest() {
		assertThrowsExactly(NoSuchElementException.class, () -> emptyStack.peek());
		assertEquals(84, stack.peek());
	}
	
	@Test
	void pushInEmptyStackTest() {
		//(1)stack.push(-1000); (should be -1000)
		//(2) stack.push(1000); stack.push(2000); 
		//stack.pop(); stack.push(1500) (should be 1500)
		emptyStack.push(-1000);
		assertEquals(-1000, emptyStack.getMaxElement());
		
	}
	
	void pushInEmptyStack1Test() {
		//(2) stack.push(1000); stack.push(2000); 
		//stack.pop(); stack.push(1500) (should be 1500)
		emptyStack.push(2000);
		emptyStack.pop();
		stack.push(1500);
		assertEquals(1500, emptyStack.getMaxElement());
	}
	
	

}
