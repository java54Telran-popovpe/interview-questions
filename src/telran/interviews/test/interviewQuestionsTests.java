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

}
