package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import impls.ContactImpl;

import org.junit.Test;

public class ContactTest {

	
	// Test Constructor and Accessors
	@Test
	public void test() {
		
		ContactImpl c = new ContactImpl("Harry", "He Likes A Drink");
		assertEquals("getName Check: ", "Harry", c.getName());
		assertEquals("getNotes Check: ", "He Likes A Drink", c.getNotes());
		
		c.setName("Harriet");  
		c.setNotes("Drinks White Wine");
		assertEquals("setName Check", "Harriet", c.getName());
		assertEquals("Drinks White Wine", c.getNotes());
		
	}

}
