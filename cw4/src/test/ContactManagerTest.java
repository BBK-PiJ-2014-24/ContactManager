package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import impls.ContactImpl;
import impls.ContactManagerImpl;
import impls.FutureMeetingImpl;
import impls.MeetingImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ContactManagerTest {

	
	// Declarations
	// ------------
	Calendar calPast;
	Calendar calFut;
	SimpleDateFormat sdf;
	Contact c1;
	Contact c2;
	Contact c3;
	Set<Contact> contactSet;
	ContactManager cm;
	FutureMeetingImpl futMeetGood;
	FutureMeetingImpl futMeetBad;
	Meeting m;
	
	@Before
	public void setUp(){
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				
		// Set Contacts
		// ------------
		 c1 = new ContactImpl("Harry","Likes a Drink");
		 c2 = new ContactImpl("Jill","Likes White Wine");
		 c3 = new ContactImpl("Jack", "Teetotal");
		
		// Set of Contacts
		// ---------------
		contactSet = new HashSet<Contact>();
		contactSet.add(c1);
		contactSet.add(c2);
		contactSet.add(c3);
		
		cm = new ContactManagerImpl();
		
		futMeetGood = new FutureMeetingImpl(contactSet, calFut);  // FutureMeeting w/Future Date
		futMeetBad = new FutureMeetingImpl(contactSet, calPast);   // FutureMeeting w/Past Date 
		m = new MeetingImpl(contactSet, calPast);
	}
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	
	
	@Test
	// Tests for addFutureMeeting()
	// ----------------------------
	public void testAddFutureMeeting() {
		List<Integer> idContainer = new ArrayList<Integer>();
		
		for(int i=0; i<900;i++){
			int id = cm.addFutureMeeting(contactSet, calFut);
			
			assertTrue("test addFutureMeeting() -  min id: ", id >= 1000000);	// test id min number
			assertTrue("test addFutureMeeting() - max id: ", id <= 10000000); // test id max number
			assertFalse("test ID Uniquenes: ", idContainer.contains(id));  // test uniqueness
			idContainer.add(id);
		}
		System.out.println("The end");
	}
	
	@Test
	public void testEXAddFutureMeeting(){
		ex.expect(IllegalArgumentException.class);
		cm.addFutureMeeting(contactSet, calPast);
	}
	
	@Test
	// Tests for getFutureMeeting()
	// ----------------------------
	public void testGetFutureMeeting(){
		int id = cm.addFutureMeeting(contactSet, calFut);
		futMeetGood.setId(id);
		assertEquals("test getFutureMeeting() - Meeting ID Check: ", futMeetGood.getId(), cm.getFutureMeeting(id).getId());
		assertEquals("test getFutureMeeting() - Meeting Contacts Check: ", futMeetGood.getContacts(), cm.getFutureMeeting(id).getContacts());
		assertEquals("test getFutureMeeting() - Meeting Date Check: ", futMeetGood.getDate(), cm.getFutureMeeting(id).getDate());
	}
	

	
}
