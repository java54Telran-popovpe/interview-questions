package telran.interviews;

import java.util.LinkedList;

//time complexity O[1]
public class MyStackInt {
	private record StackElement(int value, int maxValue) {
		
	}
	LinkedList<StackElement> stack = new LinkedList<>();
	int maxElement = 0;
	public void push( int num ) {
		maxElement = Math.max(maxElement, num);
		stack.add(new StackElement(num, maxElement));
	}
	
	public int pop() {
		return stack.removeLast().value;
	}
	
	public int peek() {
		return stack.getLast().value;
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public int getMaxElement() {
		return stack.getLast().maxValue;
	}
}
