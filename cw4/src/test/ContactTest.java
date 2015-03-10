// FINAL
package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import impls.ContactImpl;

import org.junit.Test;

public class ContactTest {

	
	/**
	 * Test Contact's Constructor, Accessor and toString() methods
	 */
	// Test Constructor, Accessors & toString()
	// ----------------------------------------
	@Test
	public void test() {
		
		ContactImpl c = new ContactImpl(12345, "Harris", "He Likes A Drink");
		assertEquals("getName Check: ", "Harris", c.getName());
		assertEquals("getNotes Check: ", "He Likes A Drink", c.getNotes());
		assertEquals("getID Check: ", 12345, c.getId());
		
		System.out.println(c.toString());
		assertEquals("toString() Check: ", "12345,Harris,He Likes A Drink", c.toString());
		
		c.setName("Harriet");  
		c.addNotes("Drinks White Wine");
		assertEquals("setName() Check", c.getName(), "Harriet");
		assertEquals("getName() Check", c.getNotes(), "Drinks White Wine");
	
	}

}
