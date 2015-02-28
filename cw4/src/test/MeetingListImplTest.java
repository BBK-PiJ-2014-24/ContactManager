package test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.Meeting;
import iinterfaces.MeetingList;
import impls.ContactImpl;
import impls.FutureMeetingImpl;
import impls.MeetingListImpl;
import impls.PastMeetingImpl;

import org.junit.Before;
import org.junit.Test;

public class MeetingListImplTest {

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
	PastMeetingImpl pastMeetGood;
	PastMeetingImpl pastMeetBad;
	MeetingList m;
	String notes;
	
	
	@Before
	public void setUp(){
		 m = new MeetingListImpl();
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				
		// Create Contacts
		// ------------
		 c1 = new ContactImpl("Harry","Likes a Drink");
		 c2 = new ContactImpl("Jill","Likes White Wine");
		 c3 = new ContactImpl("Jack", "Teetotal");
		
		// Create Set of Contacts
		// ---------------
		contactSet = new HashSet<Contact>();
		contactSet.add(c1);
		contactSet.add(c2);
		contactSet.add(c3);
		
		
		futMeetGood = new FutureMeetingImpl(contactSet, calFut);  // FutureMeeting w/Future Date
		futMeetBad = new FutureMeetingImpl(contactSet, calPast);   // FutureMeeting w/Past Date 
	
		pastMeetGood = new PastMeetingImpl(contactSet, calPast, notes); // PastMeeting w/Past Date 
		pastMeetBad = new PastMeetingImpl(contactSet, calFut, notes); // PasteMeeting w/Future Date
		
		notes = "Meeting went well";
		
		
	}
	
	@Test
	public void testID() {
		int id = m.add(futMeetGood);
		futMeetGood.setId(id);
		assertEquals("test add() - Meeting ID Check: ", futMeetGood.getId(), m.getMeeting(id));
		assertEquals("test add() - Meeting Contacts Check: ", futMeetGood.getContacts(), m.getMeeting(id).getContacts());
		assertEquals("test add() - Meeting Date Check: ", futMeetGood.getDate(), m.getMeeting(id).getDate());
	}
		
	

}
