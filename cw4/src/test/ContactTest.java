package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import impls.ContactImpl;

import org.junit.Test;

public class ContactTest {

	
	// Test Constructor and Accessors
	@Test
	public void test() {
		
		Contact c = new ContactImpl("Harry", "He Likes A Drink");
		assertEquals("Name Check: ", "Harry", c.getName());
		assertEquals("Notes Check: ", "He Likes A Drink", c.getNotes());
		
	}

}
