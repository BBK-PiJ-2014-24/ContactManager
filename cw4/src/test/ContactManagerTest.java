// FINAL
package test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import iinterfaces.PastMeeting;
import impls.ContactImpl;
import impls.ContactManagerImpl;
import impls.FutureMeetingImpl;
import impls.MeetingImpl;
import impls.PastMeetingImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * This Test Class is for testing the methods found in ContactManager.
 * @author snewnham
 *
 */
public class ContactManagerTest {

	
	// Declarations
	// ------------
	Calendar calPast;
	Calendar dudDate;
	Calendar calFut;
	Calendar badDate;
	Calendar mar14;
	Calendar apr14;
	Calendar may14;
	Calendar jun14;
	Calendar jul14;
	Calendar aug14;
	Calendar mar15;
	Calendar apr15;
	Calendar may15;
	Calendar jun15;
	Calendar jul15;
	Calendar aug15;
	SimpleDateFormat sdf;
	Contact harry;
	Contact jill;
	Contact jill2;
	Contact jack;
	Contact jim;
	Contact sophie;
	Contact gertrude;
	Contact alan;
	Contact guy;
	Contact mark;
	Set<Contact> contactSet;
	Set<Contact> emptyContactSet;
	Set<Contact>jimJillSet;
	Set<Contact>harrySophieSet;
	Set<Contact> fullContactSet;
	
	List<Calendar> jimMeetingList;
	List<Calendar> harryMeetingList;
	
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
	String nullNotes;
	
	
	@Before
	public void setUp(){
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		badDate = null;
		mar14 = new GregorianCalendar(2014, 3, 23, 12, 05);
		apr14 = new GregorianCalendar(2014, 4, 23, 12, 05);
		may14 = new GregorianCalendar(2014, 5, 23, 12, 05);
		jun14 = new GregorianCalendar(2014, 6, 23, 12, 05);
		jul14 = new GregorianCalendar(2014, 7, 23, 12, 05);
		aug14 = new GregorianCalendar(2014, 8, 23, 12, 05);
		
		mar15 = new GregorianCalendar(2015, 3, 23, 12, 05);
		apr15 = new GregorianCalendar(2015, 4, 23, 12, 05);
		may15 = new GregorianCalendar(2015, 5, 23, 12, 05);
		jun15 = new GregorianCalendar(2015, 6, 23, 12, 05);
		jul15 = new GregorianCalendar(2015, 7, 23, 12, 05);
		aug15 = new GregorianCalendar(2015, 8, 23, 12, 05);
		dudDate = new GregorianCalendar(2022, 8, 23, 12, 05);
				
		// Create Contacts
		// ------------
		 harry = new ContactImpl("Harry","Likes a Drink");
		 jill = new ContactImpl("Jill","Likes White Wine");
		 jill2 = new ContactImpl("Jill","Likes Red Wine");
		 jack = new ContactImpl("Jack", "Teetotal");
		 jim = new ContactImpl("Jim", "BUSY MAN");
		 sophie = new ContactImpl("Sophie", "Angel");
		 gertrude = new ContactImpl("Gertrude", "NOT ON CONTACT LIST");
		 alan = new ContactImpl("Alan", "Big Guy");
		 guy = new ContactImpl("Guy", "Slim Guy");
		 mark = new ContactImpl("Mark", "Shifty");
		 
		
		// Create SubSet of Contacts
		// --------------------------
		contactSet = new HashSet<Contact>();
		contactSet.add(harry);
		contactSet.add(jill);
		contactSet.add(jack);
	
		emptyContactSet = null;
		
		jimJillSet = new HashSet<Contact>();
		jimJillSet.add(jill);
		jimJillSet.add(jim);
		
		harrySophieSet = new HashSet<Contact>();
		harrySophieSet.add(harry);
		harrySophieSet.add(sophie);
		
		fullContactSet = new HashSet<Contact>();
		contactSet.add(jill);
		contactSet.add(jill2);
		contactSet.add(jack);
		contactSet.add(jim);
		contactSet.add(harry);
		contactSet.add(sophie);
		contactSet.add(alan);
		contactSet.add(guy);
		contactSet.add(mark);
		
		
		
		// Instant ContactManager
		// ----------------------
		cm = new ContactManagerImpl();
		
		// Notes
		// -----
		
		notes = "Meeting went well";
		badNotes = "";
		nullNotes = null; 
	}
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	
	/**
	 * This tests runs 10,000 simulations to check the uniqueness of the Meeting ids
	 */
	@Test
	// Tests for addFutureMeeting()
	// ----------------------------
	public void testAddFutureMeeting() {
		List<Integer> idContainer = new ArrayList<Integer>();  // Container for ID's in random Check
		
		for(int i=0; i<100;i++){  // Testing Range and Uniqueness of IDs
			int id = cm.addFutureMeeting(contactSet, calFut);
			
			assertTrue("test addFutureMeeting() -  min id: ", id >= 1000000);	// test id min number
			assertTrue("test addFutureMeeting() - max id: ", id <= 10000000); // test id max number
			assertFalse("test ID Uniquenes: ", idContainer.contains(id));  // test uniqueness
			idContainer.add(id);
		}
		// System.out.println("The end");
	}
	
	/** 
	 * This tests if addFutureMeeting throws if given a Past Date
	 */
	@Test
	public void testEXAddFutureMeeting(){
		ex.expect(IllegalArgumentException.class);
		cm.addFutureMeeting(contactSet, calPast);
	}
	
	/**
	 * Tests whether an added FutureMeeting to the Contact Manager is returned
	 * with the correct id, Contacts and Calendar date.
	 */
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
	
	/**
	 * Tests whether getFutureMeeting() will return a null when given an invalid id. 
	 */
	@Test
	public void testExGetFutureMeeting(){
		int id = 1234;
		assertEquals("test getFutureMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	
	/**
	 * Tests whether addNewPastMeeting() will thow IllegalArgumentException if the Contact argument
	 * is empty.
	 */
	@Test
	// Test for addNewPastMeeting()
	// ----------------------------
	public void testExAddNewPastMeeting1(){
			ex.expect(IllegalArgumentException.class);
			cm.addNewPastMeeting(emptyContactSet, calPast, notes);  // test for emptyContactSet
	}
	
	/**
	 * Tests whether addNewPastMeeting() will throw NullPointerException if Calendar date
	 * holds a future date.
	 */
	@Test
	public void testExAddNewPastMeeting2(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, badDate, notes);  // test for BadDate
	}
	
	/**
	 * Tests whether addNewPastMeeting() will throw NullPointer Exception if Notes argument is 
	 * null.
	 */
	@Test
	public void testExAddNewPastMeeting3(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, calPast, badNotes);  // test for BadNotes
	}
	
	
	/**
	 * Tests whether getPastMeeting() will retrieve the correct PastMeeting. Tests for id,
	 * Contacts, Calendar date and Notes.
	 */
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
	
	/**
	 * Tests whether getPastMeeting() will return null, when given date argument
	 */
	@Test
	public void testExGetNewPastMeeting1(){
		cm.addNewPastMeeting(contactSet, calFut, notes);
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		assertEquals("test getPastMeeting() - Check Bad Date", null, cm.getPastMeeting(id));	
	}
	
	/**
	 * Tests whether getPastMeeting() will return null if given invalid id argument
	 */
	@Test
	public void testExGetNewPastMeeting2(){
		int id = 1234; 
		assertEquals("test getPastMeeting() - Check Bad id", null, cm.getPastMeeting(id));	
	}
	
	/**
	 * Tests getMeeting() will correctly retrieve a FutureMeeting and a PastMeeting, given valid id's.
	 */
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
	
	/** 
	 * Tests whether getMeeting() will return null if given invalid id.
	 */
	@Test
	public void testExGetMeeting1(){
		int id = 1234;
		assertEquals("test getMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	/**
	 * Tests whether addNewContact() adds a Contact to the ContactManager Map.
	 */
	@Test
	// test addNewContact()
	// --------------------
	public void testAddGetNewContact(){
		int contactSize = cm.getContactMapSize();  // size of contact Map before addition
		Contact Stefan = new ContactImpl("Stefan", "cub scout");
		cm.addNewContact("Stefan", "cub scout");
		assertEquals("test Single addNewContact - Size of  : ", (contactSize+1), cm.getContactMapSize());
	}
	
	/**
	 * Tests whether addNewContact() throws a NullPointerException if given an invalid "" notes.
	 */
	public void testExAddGetNewContact1(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("Stefan", "");   // No Notes
	}
	
	/**
	 * Tests whether addNewContact() throws a NullPointerException if given an invalid notes that 
	 * are null.
	 */
	public void testExAddGetNewContact2(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("Stefan", null);   // null Notes
	}	
	
	/**
	 * Tests whether addNewContact() throws a NullPointerException if given an invalid name, "".
	 */
	public void testExAddGetNewContact3(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("", "cub scout");   // No name
	}
	
	/**
	 * Tests whether addNewContact() throws a NullPointerException if given a name argument 
	 * that is null.
	 */
	public void testExAddGetNewContact4(){
		ex.expect(NullPointerException.class);
		cm.addNewContact(null, "cub scout");   // No name
	}	
	
	
	/**
	 * Tests addNewContact() adds a Contact with correct field states.
	 * 
	 */
	@Test
	// test getContacts()
	// ---------------------
	public void testGetContacts1(){
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		Set<Contact> foundContacts = cm.getContacts(id);  // return type is Set
		// Convert set =>toArray
		Contact[] contactArray = (Contact[]) foundContacts.toArray(new Contact[foundContacts.size()]); // Convert to array
		assertEquals("test getContacts() - single contact Name", "Harry", contactArray[0].getName());
		assertEquals("test getContacts() - single contact Notes", "Likes a Drink", contactArray[0].getNotes());
	}
	
	/**
	 * Tests whether getContacts(ids ..) correctly retrieves numerous Contacts (with correct states)
	 */
	@Test
	// getContacts(ids ...);
	public void testGetContacts2(){
		// Load Some Contacts into ContactManager
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id1 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		int id2 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		int id3 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		
		// Create a parallel test set of contacts 
		Set<Contact> testSet = new HashSet<Contact>();
		Contact harry = new ContactImpl(id1,"Harry","Likes a Drink");
		Contact paul = new ContactImpl(id2,"Paul","policeman");
		Contact portia = new ContactImpl(id3,"Portia","sweet girl");
		testSet.add(harry);
		testSet.add(paul);
		testSet.add(portia);
		
		List<String> names = new ArrayList<String>();
		names.add("Harry");
		names.add("Paul");
		names.add("Portia");
		
		Set<Contact> foundContacts = cm.getContacts(id1,id2,id3);  // return type is Set
		for(Contact i : foundContacts){
			assertTrue("testGetContacts - multiple Contacts - Check Names", names.contains(i.getName()));
		}
		
		List<String> listNotes = new ArrayList<String>();
		listNotes.add("Likes a Drink");
		listNotes.add("policeman");
		listNotes.add("sweet girl");
		
		for(Contact i : foundContacts){
			assertTrue("testGetContacts - multiple Contacts - Check Notes", listNotes.contains(i.getNotes()));
		}
	}
	
	/**
	 * Tests getContacts() throws an IllegalArgumentException if there is an invalid id argument.
	 */
	@Test
	public void testExGetContacts(){ // TEST FOR INVALID ID
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id1 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		int id2 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		int id3 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		
		ex.expect(IllegalArgumentException.class);
		cm.getContacts(id1,id2,id3,999);
	}
	
	/**
	 * Tests whether getContacts(String name) correctly retreives all Contacts with the same name.
	 */
	@Test
	// getContacts(String name)
	// ------------------------
	public void testGetContactsNames(){
		 cm.addNewContact("Harry","Likes a Drink");
		 cm.addNewContact("Jill","Likes White Wine");
		 cm.addNewContact("Jill","Likes Red Wine");
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 cm.addNewContact("Sophie", "Angel");
		 cm.addNewContact("Alan", "Big Guy");
		 cm.addNewContact("Guy", "Slim Guy");
		 cm.addNewContact("Mark", "Shifty");
		 cm.addNewContact("Jill","Likes Whisky");
		
		 
		 List<String> names = new ArrayList<String>();
			names.add("Jill");
			names.add("Jill");
			names.add("Jill");
			
			Set<Contact> foundContacts = cm.getContacts("Jill");	
		 for(Contact i : foundContacts){
			assertTrue("testGetContacts - Same Names", names.contains(i.getName()));
		 }
	}
	/**
	 * Tests getContacts(Name) throws an IllegalArgumentExecution if the Name argument is null.
	 */
	 @Test
	 public void testExGetContactsNames(){
		 ex.expect(IllegalArgumentException.class);
		 String s = null;
		 cm.getContacts(s);
	 }
	
	 /**
	  * Tests whether getFutureMeeting() returns the correct list of meetings and in the right order.
	  */
	@Test
	// Test for getFutureMeetingList(Contact)
	// --------------------------------------
	public void testGetFutureMeetingContact(){
		
		 cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 Set<Contact> harrySet = cm.getContacts(idHarry);  // Create a Contact for Harry
		 Set<Contact> jimSet= cm.getContacts(idJim);  // Create a Contact for Jim
		 
		 Contact harry=null;
		 for(Contact i : harrySet){
			 harry = i;
		 }
		 Contact jim = null;
		 for(Contact i : jimSet){
			 jim = i;
		 }
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		 cm.addNewPastMeeting(jimJillSet, calPast,"Meeting went well");  // add BAD Past meeting to ContactManager
		 cm.addFutureMeeting(jimJillSet, mar15);  // add Future meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);
		 cm.addFutureMeeting(harrySophieSet, jul15);
		 cm.addFutureMeeting(harrySophieSet, aug15);
		 
		 jimMeetingList = new ArrayList<Calendar>();    // Create a Test List for Contacts Jim, harry
		 jimMeetingList.add(mar15);
		 jimMeetingList.add(apr15);
		 jimMeetingList.add(may15);
		 jimMeetingList.add(jun15);
		  
		 harryMeetingList = new ArrayList<Calendar>(); // Incorrect Date Order
		 harryMeetingList.add(aug15);
		 harryMeetingList.add(jul15);
		 
		 
		 List<Meeting> findJimList = cm.getFutureMeetingList(jim);
		 List<Meeting> findHarryList = cm.getFutureMeetingList(harry);
		 		 
		 int k=0;
		 for(Meeting i : findJimList){
			 assertTrue("test getFutureMeeting(Contact) for Jim: ", jimMeetingList.contains(i.getDate()));
			 assertTrue("test Date Order getFutureMeeting(Contact) for Jim: ", jimMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 k=0;
		 for(Meeting i : findHarryList){
			 assertTrue("test getFutureMeeting(Contact) for Harry: ", harryMeetingList.contains(i.getDate()));
			 assertFalse("test Wrong Date Order getFutureMeeting(Contact) for Harry: ", harryMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 
	}
	
	/**
	 * Tests whether if getFutureMeeting throws an IllegalArgumentExcpetion when given an
	 * invalid name as an argument.
	 */
	@Test
	public void testEXGetFutureMeetingContact(){
		ex.expect(IllegalArgumentException.class);
		cm.getFutureMeetingList(gertrude);
	}
	
	/**
	 * Tests whether getFutureMeeting(Calendar) returns the correct List of meetings for a given date.
	 */
	@Test
	// getFutureMeetingList(Calendar)
	// ------------------------------
	public void testgetFutureMeetingListContact(){
		
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		  
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
			 
		 List<Calendar> mar15MeetingList = new ArrayList<Calendar>();    // Create a Test List for Mar 15
		 mar15MeetingList.add(mar15);
		 mar15MeetingList.add(mar15);
		  
		 List<Calendar> jun15MeetingList = new ArrayList<Calendar>(); // Create a Test List for Jun 15
		 jun15MeetingList.add(jun15);
		 jun15MeetingList.add(jun15);
		 	 
		 List<Meeting> findMar15Meetings = cm.getFutureMeetingList(mar15);
		 List<Meeting> findJun15Meetings = cm.getFutureMeetingList(jun15);
		 	 
		 for(Meeting i : findMar15Meetings){
			 assertTrue("test getFutureMeeting(Calendar) for Mar15: ", mar15MeetingList.contains(i.getDate()));
		 }
		 
		 for(Meeting i : findJun15Meetings){
			 assertTrue("test getFutureMeeting(Calendar) for Jun15: ", jun15MeetingList.contains(i.getDate()));
		 }
	}
	
	/**
	 * Tests whether getFutureMeeting(Calendar) returns no meetings for an invalid date argument.
	 */
	@Test
	public void testExgetFutureMeetingListContact(){
		List<Meeting> noMeetings = cm.getFutureMeetingList(dudDate);
		assertEquals("test getFutureMeeting(Calendar) for DudDate: ", 0, noMeetings.size() ); // Test for No Entries
	}
	
	
	/**
	 * Tests whether a getPASTeMeetingList(Contact) returns the correct list of PastMeetings for a 
	 * given contact (and ignores returning FutureMeetings)
	 */
	@Test
	// Test for getPASTeMeetingList(Contact)
	// --------------------------------------
	public void testGetPastMeetingContact(){
		
		 cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 Set<Contact> harrySet = cm.getContacts(idHarry);  // Create a Contact for Harry
		 Set<Contact> jimSet= cm.getContacts(idJim);  // Create a Contact for Jim
		 
		 Contact harry=null;
		 for(Contact i : harrySet){
			 harry = i;
		 }
		 Contact jim = null;
		 for(Contact i : jimSet){
			 jim = i;
		 }
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		 cm.addFutureMeeting(jimJillSet, calFut);  // add BAD Past meeting to ContactManager
		 cm.addNewPastMeeting(jimJillSet, mar14, "Meeting went well");  // add Future meetings to ContactManager
		 cm.addNewPastMeeting(jimJillSet, apr14,"Meeting went well");
		 cm.addNewPastMeeting(jimJillSet, may14, "Meeting went well");
		 cm.addNewPastMeeting(jimJillSet, jun14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		 jimMeetingList = new ArrayList<Calendar>();    // Create a Test List for Contacts Jim, harry
		 jimMeetingList.add(mar14);
		 jimMeetingList.add(apr14);
		 jimMeetingList.add(may14);
		 jimMeetingList.add(jun14);
		  
		 harryMeetingList = new ArrayList<Calendar>(); // Incorrect Date Order
		 harryMeetingList.add(aug14);
		 harryMeetingList.add(jul14);
		 
		 
		 List<PastMeeting> findJimList = cm.getPastMeetingList(jim);
		 List<PastMeeting> findHarryList = cm.getPastMeetingList(harry);
		 	 
		 int k=0;
		 for(Meeting i : findJimList){
			 assertTrue("test getPastMeeting(Contact) for Jim: ", jimMeetingList.contains(i.getDate()));
			 assertTrue("test Date Order getPastMeeting(Contact) for Jim: ", jimMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 k=0;
		 for(Meeting i : findHarryList){
			 assertTrue("test getPastMeeting(Contact) for Harry: ", harryMeetingList.contains(i.getDate()));
			 assertFalse("test Wrong Date Order getPastMeeting(Contact) for Harry: ", harryMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
	}
	
	/**
	 * Tests whether getPASTeMeetingList(Contact) throws an IllegalArgumentException if given
	 * an invalid Contact argument.
	 */
	@Test
	public void testEXGetPastMeetingContact(){
		ex.expect(IllegalArgumentException.class);
		cm.getFutureMeetingList(gertrude);
	}
	
	/**
	 * Tests whether addMeetingNotes(id, text) changes a FutureMeeting to a PastMeeting 
	 * and append it with Notes.
	 */
	@Test
	// addMeetingNotes(id, text)
	// --------------------------
	public void testAddMeetingNotes(){
		
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		 cm.addFutureMeeting(jimJillSet, calFut);  // add Meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, mar15);  // *** We are going to change this to a past meeting **
		 int meetingId = cm.getLastIdUpdate();
		 
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		 Calendar aprFool = new GregorianCalendar(2015, 4, 1, 12, 05);
		 cm.setTodayDate(aprFool);      // Change Today's Date for testing 
		 cm.addMeetingNotes(meetingId, notes);
		 PastMeeting pm = cm.getPastMeeting(meetingId);
		 assertEquals("test addMeeting Notes - check FutureMeeting turn to Past: ", jimJillSet, pm.getContacts());  
		 cm.resetToday();
	}
	
	
	/**
	 * Tests whether addMeetingNotes(id, text) rejects a FutureMeeting that is NOT on the
	 * ContactManager's Meeting Map  by throwing an ILLegalArgumentException
	 */
	@Test   // test addMeeting Notes  - Check FutureMeeting Not In Contact Manager
	public void testEx1AddMeetingNotes(){
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		// *** We are going to create a TEST FutureMeeting, but not add it to the ContactManager
		 int meetingId = 1234;
		 FutureMeeting testFM =  new FutureMeetingImpl(meetingId, jimJillSet, mar15);  
		 
		 cm.addFutureMeeting(jimJillSet, calFut);  // add Meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		ex.expect(IllegalArgumentException.class);
		cm.addMeetingNotes(meetingId, notes);
	}
	
	/**
	 * Tests whether addMeetingNotes(id, text) rejects FutureMeeting argument that is still 
	 * in the future by throwing an IllegalArgumentException.
	 */
	@Test   // test addMeeting Notes  - Check FutureMeeting Is Still Not In The Future
	public void testEx2AddMeetingNotes(){
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);		
		 
		 cm.addFutureMeeting(jimJillSet, calFut);  // add Meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);  // ***WE ARE GOING TO TEST THIS MEETING****
		 int meetingId =cm.getLastIdUpdate();
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		ex.expect(IllegalArgumentException.class);
		cm.addMeetingNotes(meetingId, notes);
	}
	
	/**
	 * Tests whether addMeetingNotes(id, text) throws an IllegalArgumentException if
	 * notes are null. 
	 */
	@Test   // test addMeeting Notes  - Check FutureMeeting NOTES Are Empty
	public void testEx3AddMeetingNotes(){
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();		 
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
			 
		 cm.addFutureMeeting(jimJillSet, calFut);  // add Meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);  
		 int meetingId =cm.getLastIdUpdate();
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		ex.expect(NullPointerException.class);
		cm.addMeetingNotes(meetingId, nullNotes);
	}
	
	
}
