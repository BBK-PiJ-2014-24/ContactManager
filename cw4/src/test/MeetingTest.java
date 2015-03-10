package test;


import org.junit.Test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import iinterfaces.Meeting;
import impls.ContactImpl;
import impls.MeetingImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;
import java.util.TimeZone;


public class MeetingTest {

	/**
	 * Tests for Meeting's accessors for id, date, contact and toString() methods.
	 */
	@Test
	public void test() {
		
		// Set Date()
		// ----------
		Calendar cal = new GregorianCalendar(2014, 8, 24, 12, 05);
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		cal.setTimeZone(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		String dateString = sdf.format(cal.getTime());
	
		
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
		MeetingImpl m = new MeetingImpl(1234, ContactSet, cal);
		
		
		
		// tests
		// -----
		//m.id = 1234;
		assertEquals("getID() Check: ", 1234, m.getId());
		assertEquals("getDate() Check: ", cal, m.getDate());
		assertEquals("getContacts() Check: ", ContactSet, m.getContacts());
		System.out.println("expect" + cal.getTime());
		System.out.println("actual" + m.toString());
		assertEquals("toString() check: ", "1234" + "," + dateString + "," + ContactSet.toString(), 
				      m.toString());
		
		
	}

}
