package telran.interviews;

import java.util.LinkedList;

//time complexity O[1]
public class MyStackInt {
	private record StackElement(int value, int maxValue, int prevMaxValue) {
		
	}
	LinkedList<StackElement> stack = new LinkedList<>();
	int prevMaxElement = Integer.MIN_VALUE;
	public void push( int num ) {
		int newMAxValue = Math.max(prevMaxElement, num);
		stack.add(new StackElement(num, newMAxValue, prevMaxElement));
		prevMaxElement = newMAxValue;
	}
	
	public int pop() {
		StackElement poppedElement = stack.removeLast();
		prevMaxElement = poppedElement.prevMaxValue;
		return poppedElement.value;
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
