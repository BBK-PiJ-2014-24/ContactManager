package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import impls.ContactImpl;

import org.junit.Test;

public class ContactTest {

	
	// Test Constructor and Accessors
	@Test
	public void test() {
		
		ContactImpl c = new ContactImpl(1234, "Harry", "He Likes A Drink");
		assertEquals("getName Check: ", "Harry", c.getName());
		assertEquals("getNotes Check: ", "He Likes A Drink", c.getNotes());
		assertEquals("getID Check: ", 1234, c.getId());
		
		c.setName("Harriet");  
		c.addNotes("Drinks White Wine");
		assertEquals("setName() Check", c.getName(), "Harriet");
		assertEquals("getName() Check", c.getNotes(), "Drinks White Wine");
		
		assertEquals("toString() Check: ", "1234,Harry,He Likes A Drink", c.toString());
		
		
	}

}
