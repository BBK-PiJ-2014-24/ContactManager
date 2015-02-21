package test;


import org.junit.Test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import impls.ContactImpl;
import impls.;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;




public class MeetingTest {

	@Test
	public void test() {
		
		// Set Date()
		// ----------
		Calendar cal = new GregorianCalendar(2014, 8, 24, 12, 00);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy hh:mm");
		System.out.println("Check Date: " + sdf.format(cal.getTime()));		
		
		// Set Contacts
		// ------------
		Contact c1 = new ContactImpl("Harry","Likes a Drink");
		Contact c2 = new ContactImpl("Jill","Likes White Wine");
		Contact c3 = new ContactImpl("Jack", "Teetotal");
		
		// Set of Contacts
		// ---------------
		Set<Contact> ContactSet = new HashSet<Contact>();
		ContactSet.add(c1);
		ContactSet.add(c2);
		ContactSet.add(c3);
		
	//	Meeting m = MeetingImpl(ContactSet, cal);
	//	m.id = 1234;
	//	assertEquals("getID() Check: ", m.getId(), 1234);
		
		
		
	}

}
