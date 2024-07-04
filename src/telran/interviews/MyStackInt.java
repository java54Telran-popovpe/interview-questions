package telran.interviews;

import java.util.LinkedList;
import java.util.Optional;

//time complexity O[1]
public class MyStackInt {
	private record StackElement(int value, Optional<Integer> prevMaxValue) {
		
	}
	LinkedList<StackElement> stack = new LinkedList<>();
	Optional<Integer> currentMaxValue = Optional.empty();
	public void push( int num ) {
		stack.add(new StackElement(num, currentMaxValue));
		int newMAxValue = Math.max(currentMaxValue.orElse(Integer.MIN_VALUE), num);
		currentMaxValue = Optional.of(newMAxValue);
		
	}
	
	public int pop() {
		StackElement poppedElement = stack.removeLast();
		currentMaxValue = poppedElement.prevMaxValue;
		return poppedElement.value;
	}
	
	public int peek() {
		return stack.getLast().value;
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public int getMaxElement() {
		return currentMaxValue.orElseThrow();
	}
}
