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
import impls.PastMeetingImpl;

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
	Calendar badDate;
	Calendar mar15;
	Calendar apr15;
	Calendar may15;
	Calendar jun15;
	Calendar jul15;
	Calendar aug15;
	SimpleDateFormat sdf;
	Contact c1;
	Contact c2;
	Contact c3;
	Contact jim;
	Contact c5;
	Contact gertrude;
	Set<Contact> contactSet;
	Set<Contact> emptyContactSet;
	Set<Contact>JimJillSet;
	Set<Contact>HarrySophieSet;
	
	List<Meeting> jimMeetingList;
	List<Meeting> harryMeetingList;
	
	ContactManagerImpl cm;
	FutureMeetingImpl futMeetGood;
	FutureMeetingImpl futMeetBad;
	PastMeetingImpl pastMeetGood;
	PastMeetingImpl pastMeetBad;
	Meeting m;
	
	FutureMeeting meetingMar15;
	FutureMeeting meetingApr15;
	FutureMeeting meetingMay15;
	FutureMeeting meetingJun15;
	FutureMeeting meetingJul15;
	FutureMeeting meetingAug15;
	
	String notes;
	String badNotes;
	
	
	
	@Before
	public void setUp(){
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		badDate = null;
		mar15 = new GregorianCalendar(2015, 3, 23, 12, 05);
		apr15 = new GregorianCalendar(2015, 4, 23, 12, 05);
		may15 = new GregorianCalendar(2015, 5, 23, 12, 05);
		jun15 = new GregorianCalendar(2015, 6, 23, 12, 05);
		jul15 = new GregorianCalendar(2015, 7, 23, 12, 05);
		aug15 = new GregorianCalendar(2015, 8, 23, 12, 05);
		
				
		// Create Contacts
		// ------------
		 c1 = new ContactImpl("Harry","Likes a Drink");
		 c2 = new ContactImpl("Jill","Likes White Wine");
		 c3 = new ContactImpl("Jack", "Teetotal");
		 jim = new ContactImpl("Jim", "BUSY MAN");
		 c5 = new ContactImpl("Sophie", "Angel");
		 gertrude = new ContactImpl("Gertrude", "NOT ON CONTACT LIST");
		 
		 
		
		// Create Set of Contacts
		// ----------------------
		contactSet = new HashSet<Contact>();
		contactSet.add(c1);
		contactSet.add(c2);
		contactSet.add(c3);
	
		emptyContactSet = null;
		
		JimJillSet = new HashSet<Contact>();
		JimJillSet.add(c2);
		JimJillSet.add(jim);
		
		HarrySophieSet = new HashSet<Contact>();
		HarrySophieSet.add(c1);
		HarrySophieSet.add(c5);
		
		// Instant ContactManager
		// ----------------------
		cm = new ContactManagerImpl();
		
		// Instant Meetings
		// ----------------

		 
		 // MeetingList
		 // -----------



		// Notes
		// -----
		
		notes = "Meeting went well";
		badNotes = "";
	}
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	
	
	@Test
	// Tests for addFutureMeeting()
	// ----------------------------
	public void testAddFutureMeeting() {
		List<Integer> idContainer = new ArrayList<Integer>();  // Container for ID's in random Check
		
		for(int i=0; i<900;i++){  // Testing Range and Uniqueness of IDs
			int id = cm.addFutureMeeting(contactSet, calFut);
			
			assertTrue("test addFutureMeeting() -  min id: ", id >= 1000000);	// test id min number
			assertTrue("test addFutureMeeting() - max id: ", id <= 10000000); // test id max number
			assertFalse("test ID Uniquenes: ", idContainer.contains(id));  // test uniqueness
			idContainer.add(id);
		}
		// System.out.println("The end");
	}
	
	@Test
	public void testEXAddFutureMeeting(){
		ex.expect(IllegalArgumentException.class);
		cm.addFutureMeeting(contactSet, calPast);
	}
	
	@Test
	// Tests for getFutureMeeting(id)
	// ------------------------------
	public void testGetFutureMeeting(){
		int id = cm.addFutureMeeting(contactSet, calFut);
		futMeetGood = new FutureMeetingImpl(id, contactSet, calFut);  // FutureMeeting w/Future Date
		assertEquals("test getFutureMeeting() - Meeting ID Check: ", futMeetGood.getId(), cm.getFutureMeeting(id).getId());
		assertEquals("test getFutureMeeting() - Meeting Contacts Check: ", futMeetGood.getContacts(), cm.getFutureMeeting(id).getContacts());
		assertEquals("test getFutureMeeting() - Meeting Date Check: ", futMeetGood.getDate(), cm.getFutureMeeting(id).getDate());
	}
	
	@Test
	public void testExGetFutureMeeting(){
		int id = 1234;
		assertEquals("test getFutureMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	@Test
	// Test for addNewPastMeeting()
	// ----------------------------
	public void testExAddNewPastMeeting1(){
			ex.expect(IllegalArgumentException.class);
			cm.addNewPastMeeting(emptyContactSet, calPast, notes);  // test for emptyContactSet
	}
	
	@Test
	public void testExAddNewPastMeeting2(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, badDate, notes);  // test for BadDate
	}
	@Test
	public void testExAddNewPastMeeting3(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, calPast, badNotes);  // test for BadNotes
	}
	
	@Test
	// Test for getPastMeeting(id)
	// ---------------------------
	public void testGetNewPastMeeting(){
		cm.addNewPastMeeting(contactSet, calPast, notes);
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		pastMeetGood = new PastMeetingImpl(id, contactSet, calPast, notes);  // PastMeeting w/Past Date
		assertEquals("test getPastMeeting() - Meeting ID Check: ", pastMeetGood.getId(), cm.getPastMeeting(id).getId());
		assertEquals("test getPastMeeting() - Meeting Contacts Check: ", pastMeetGood.getContacts(), cm.getPastMeeting(id).getContacts());
		assertEquals("test getPastMeeting() - Meeting Date Check: ", pastMeetGood.getDate(), cm.getPastMeeting(id).getDate());
		assertEquals("test getPastMeeting() - Meeting Notes Check: ", pastMeetGood.getNotes(), cm.getPastMeeting(id).getNotes());
	}
	
	@Test
	public void testExGetNewPastMeeting1(){
		cm.addNewPastMeeting(contactSet, calFut, notes);
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		assertEquals("test getPastMeeting() - Check Bad Date", null, cm.getPastMeeting(id));	
	}
	
	@Test
	public void testExGetNewPastMeeting2(){
		int id = 1234; 
		assertEquals("test getPastMeeting() - Check Bad id", null, cm.getPastMeeting(id));	
	}
	
	@Test
	// Test for getMeeting()
	// ---------------------
	public void testGetMeeting(){
		int id = cm.addFutureMeeting(contactSet, calFut);
		futMeetGood = new FutureMeetingImpl(id, contactSet, calFut);  // FutureMeeting w/Future Date
		assertEquals("test getMeeting() - FutMeeting ID Check: ", futMeetGood.getId(), cm.getMeeting(id).getId());
		assertEquals("test getMeeting() - FutMeeting Contacts Check: ", futMeetGood.getContacts(), cm.getMeeting(id).getContacts());
		assertEquals("test getMeeting() - FutMeeting Date Check: ", futMeetGood.getDate(), cm.getMeeting(id).getDate());
		
		cm.addNewPastMeeting(contactSet, calPast, notes);
		id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		pastMeetGood = new PastMeetingImpl(id, contactSet, calPast, notes);  // PastMeeting w/Past Date
		assertEquals("test getMeeting() - PastMeeting ID Check: ", pastMeetGood.getId(), cm.getPastMeeting(id).getId());
		assertEquals("test getMeeting() - PastMeeting Contacts Check: ", pastMeetGood.getContacts(), cm.getPastMeeting(id).getContacts());
		assertEquals("test getMeeting() - PastMeeting Date Check: ", pastMeetGood.getDate(), cm.getPastMeeting(id).getDate());
		assertEquals("test getMeeting() - PastMeeting Notes Check: ", pastMeetGood.getNotes(), cm.getPastMeeting(id).getNotes());	
	}
	@Test
	public void testExGetMeeting1(){
		int id = 1234;
		assertEquals("test getMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	@Test
	// Test for getFutureMeetingList(Contact)
	// --------------------------------------
	public void testGetFutureMeetingContact(){
		 int idMar15 = cm.addFutureMeeting(JimJillSet, mar15);  // add meetings to ContactManager
		 int idApr15 = cm.addFutureMeeting(JimJillSet, apr15);
		 int idMay15 = cm.addFutureMeeting(JimJillSet, may15);
		 int idJun15 = cm.addFutureMeeting(JimJillSet, jun15);
		 int idJul15 = cm.addFutureMeeting(HarrySophieSet, jul15);
		 int idAug15 = cm.addFutureMeeting(HarrySophieSet, aug15);
		 
		 meetingMar15 = new FutureMeetingImpl(idMar15,JimJillSet, mar15); // instant test Fut Meetings
		 meetingApr15 = new FutureMeetingImpl(idApr15,JimJillSet, apr15);
		 meetingMay15 = new FutureMeetingImpl(idMay15,JimJillSet, may15);
		 meetingJun15 = new FutureMeetingImpl(idJun15,JimJillSet, jun15);
		 meetingJul15 = new FutureMeetingImpl(idJul15,HarrySophieSet, jul15);
		 meetingAug15 = new FutureMeetingImpl(idAug15,HarrySophieSet, aug15);
		 
		 jimMeetingList = new ArrayList<Meeting>();    // Create a test List for Contacts Jim, harry
		 jimMeetingList.add(meetingMar15);
		 jimMeetingList.add(meetingApr15);
		 jimMeetingList.add(meetingMay15);
		 jimMeetingList.add(meetingJun15);
		  
		 harryMeetingList = new ArrayList<Meeting>();
		 harryMeetingList.add(meetingJul15);
		 harryMeetingList.add(meetingAug15);
		 
		 assertEquals("test getFutureMeeting(Contact)I: ", jimMeetingList, cm.getFutureMeetingList(jim));
		 assertEquals("test getFutureMeeting(Contact)II: ", jimMeetingList, cm.getFutureMeetingList(jim));
	}
	
	@Test
	public void testEXGetFutureMeetingContact(){
		ex.expect(IllegalArgumentException.class);
		cm.getFutureMeetingList(gertrude);
	}
	
	
}
