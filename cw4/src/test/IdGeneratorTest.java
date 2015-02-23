package test;

import static org.junit.Assert.*;
import impls.IdGenerator;

import org.junit.Test;

public class IdGeneratorTest {

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
