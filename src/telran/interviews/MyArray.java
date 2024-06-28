package telran.interviews;

import java.util.HashMap;

//all methods must have complexity 1
public class MyArray<T> {
//TODO Data structure
	HashMap<Integer,T> map;
	T defaultValue = null;
	int size;
	public void setAll(T value ) {
		//TODO
		//all array's elements should be set with a given value
		map.clear();
		defaultValue = value;
	}
	public void set(int index, T value) {
		checkIndex(index);
		map.put(index, value);
		//set new value at a given index
		//throws ArrayIndexOutOfBoundsException for incorrect index
	}
	private void checkIndex(int index) {
		if( index < 0 || index > size - 1 )
			throw new ArrayIndexOutOfBoundsException();
	}
	
	public T get(int index) {
		checkIndex(index);
		return map.getOrDefault(map, defaultValue);
	}
	public MyArray( int size ) {
		if( size < 0 )
			throw new IllegalArgumentException();
		map = new HashMap<Integer, T>();
		
	}
}
