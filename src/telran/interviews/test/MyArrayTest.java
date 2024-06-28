package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.MyArray;

class MyArrayTest {
	
	private static final int SIZE = 1_000_000;
	private static final int FILL_PERCENT = 70;
	private static final Integer INTEGER_OBJ = 5;
	MyArray<Integer> myArray;
	Integer[] referenceArray;
	HashSet<Integer> initializedIndexes;
	
	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() {
		myArray = MyArray.create(SIZE);
		referenceArray = new Integer[SIZE];
		Random random = new Random();
		initializedIndexes = new HashSet<Integer>();
		new Random().ints(0, SIZE )
					.distinct()
					.limit(SIZE * FILL_PERCENT / 100 )
					.forEach( ind -> {
						Integer value = random.nextInt();
						myArray.set(ind, value);
						referenceArray[ind] = value;
						initializedIndexes.add(ind);
					});
		}
	
	@Test
	void setAndGetTest() {
		for( Integer i = 0; i < SIZE; i++) {
			if ( initializedIndexes.contains(i) ) {
				assertEquals(referenceArray[i], myArray.get(i));
			} else {
				assertNull(myArray.get(i));
			}
		}
	}
	
	@Test
	void setAllTest() {
		myArray.setAll(INTEGER_OBJ);
		for( Integer i = 0; i < SIZE; i++) {
				assertEquals(INTEGER_OBJ, myArray.get(i));
		}
		
	}

}
