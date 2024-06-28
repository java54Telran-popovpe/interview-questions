package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.interviews.InterviewQuestions.*;

import org.junit.jupiter.api.Test;

class interviewQuestionsTests {

	@Test
	void displayOccurencesTest() {
		String[] strings = {
				"b",
				"a",
				"aa",
				"lmn",
				"aa",
				"lmn",
				"lmn"
		};
		displayOccurences(strings);
		//Should be displayed out the following:
/**
 *  lmn => 3
 *  aa => 2
 *  a => 1
 *  b => 1		
 */
	}
	@Test
	void isSum2Test() {
		int [] array = {10,2,40,-30};
		assertTrue(isSum2(array, 30));
		assertFalse(isSum2(array,32));
	}
	
	@Test
	void maxPositiveWithNagativeTest {
		int [] array1 = {-200, 10, -10, 1,2,3};
		int[] array2 = {-200, -200, 100, 10, 10	};
		assertEquals(200, getMax....(array1));
		assertEquals(-1, getMax...(array2));
		

}
