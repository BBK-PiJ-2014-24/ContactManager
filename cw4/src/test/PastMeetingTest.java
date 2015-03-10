// FINAL
package test;

import org.junit.Test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import iinterfaces.PastMeeting;
import impls.ContactImpl;
import impls.PastMeetingImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;


public class PastMeetingTest {

	@Test
	public void test() {
		
		// Set Date()
		// ----------
		Calendar cal = new GregorianCalendar(2014, 8, 24, 12, 05);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		//System.out.println("Check Date: " + sdf.format(cal.getTime()));		
		
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
		
		
		// Set up Notes
		// ------------
		
		String myNotes = "He Was Very Drunk";
		PastMeetingImpl pm = new PastMeetingImpl(1234, ContactSet, cal, myNotes);
		
		// tests
		// -----
		//m.id = 1234;
		assertEquals("getID() Check: ", pm.getId(), 1234);
		assertEquals("getDate() Check: ", cal, pm.getDate());
		assertEquals("getContacts() Check: ", ContactSet, pm.getContacts());
		assertEquals("getNotes() Check: ", myNotes, pm.getNotes());
		
		String myNotes2 = "He Was Very Drunk and Now he's in hospital";
		pm.setNotes(myNotes2);
		assertEquals("setNotes() Check: ", myNotes2, pm.getNotes());
		
		
	}

}
