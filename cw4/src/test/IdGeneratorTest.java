package test;

import static org.junit.Assert.*;
import impls.IdGenerator;

import org.junit.Test;

public class IdGeneratorTest {

	/**
	 * TEST THAT contactID is bounded between 10,000-100,000
	 */
	@Test
	public void testContactID() {
		String c = "contactId";
		int min = 10000;
		int max = 100000;
		for(int i=0;i<10000;i++){
			int x = IdGenerator.generateID(c);
			assertTrue("contactID Generator - min ID: ", x >= min);
			assertTrue("contactID Generator - max ID: ", x <= max);
		}
	}
	
	/**
	 * * TEST THAT meetingID is bounded between 1,000,000-10,000,000
	 */
	public void testMeetingID() {
		String m = "meetingId";
		int min = 100000;
		int max = 1000000;
		for(int i=0;i<1000000;i++){
			int x = IdGenerator.generateID(m);
			assertTrue("contactID Generator - min ID: ", x >= min);
			assertTrue("contactID Generator - max ID: ", x <= max);
		}
	}

}
