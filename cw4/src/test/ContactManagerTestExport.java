// FINAL
package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import iinterfaces.DataStorage;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import iinterfaces.PastMeeting;
import impls.ContactImpl;
import impls.ContactManagerImpl;
import impls.FutureMeetingImpl;
import impls.PastMeetingImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for the I/O from the file "Contacts.txt". The contact and meeting Maps found in ContactManager 
 * are exported to "Contacts.txt" with the method flush() and imported with loadContactsMeetings(). 
 * In this Test Class, Meetings and Contacts are built in ContactManager cm, then flushed. A new 
 * ContactManager, cm2, is instantiated and its contact and meeting maps will be compared with those in cm.
 * 
 * Note that preliminary tests, testMakeContact(), testMakeFutureMeeting() and testMakePastMeeting(), are 
 * run to test whether a Contact and a Meeting Can Be Created From a String. 
 * @author snewnham
 *
 */
public class ContactManagerTestExport {

	// Declarations
	// ------------
	Calendar calPast;
	Calendar calFut;
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
	Set<Contact>jimJillSet;
	Set<Contact>harrySophieSet;
	
	List<Calendar> jimMeetingList;
	List<Calendar> harryMeetingList;
	
	FutureMeetingImpl fm1;
	FutureMeetingImpl fm3;
	PastMeeting pm1; 
	Meeting m;
	
	FutureMeeting meetingMar15;
	FutureMeeting meetingApr15;
	FutureMeeting meetingMay15;
	FutureMeeting meetingJun15;
	FutureMeeting meetingJul15;
	FutureMeeting meetingAug15;
	
	String notes;
	ContactManagerImpl cm;
	ContactManagerImpl cm2;
	
	ArrayList<Meeting> idMeetingList;
	ArrayList<Contact> contactList;
	ArrayList<Integer> jimJackIds;
	ArrayList<Integer> harryJillIds;
	
	Map<Integer, Contact> cMap;
	Map<Integer, Meeting> mMap;
	
	@Before
	public void setUp(){
		
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		
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
		
		// ContactManager
		// --------------
		
		cm = new ContactManagerImpl();
				
		// Create Contacts
		// ------------
		 
		 cm.addNewContact("Harry","Likes a Drink");
		 int id1 = cm.getLastIdUpdate();
		 Contact c1 =  new ContactImpl(id1, "Harry","Likes a Drink");
		 
		 cm.addNewContact("Jill","Likes White Wine");
		 int id2 = cm.getLastIdUpdate();
		 Contact c2 =  new ContactImpl(id2, "Jill","Likes White Wine");
		 
		 cm.addNewContact("Jill","Likes Red Wine");
		 int id3 = cm.getLastIdUpdate();
		 Contact c3 =  new ContactImpl(id3, "Jill","Likes Red Wine");
		 
		 cm.addNewContact("Jack", "Teetotal");
		 int id4 = cm.getLastIdUpdate();
		 Contact c4 =  new ContactImpl(id4, "Jack", "Teetotal");
		 
		 cm.addNewContact("Jim", "BUSY MAN");
		 int id5 = cm.getLastIdUpdate();
		 Contact c5 =  new ContactImpl(id5, "Jim", "BUSY MAN");
		 
		 cm.addNewContact("Sophie", "Angel");
		 int id6 = cm.getLastIdUpdate();
		 Contact c6 =  new ContactImpl(id6, "Sophie", "Angel");
		 
		 cm.addNewContact("Alan", "Big Guy");
		 int id7 = cm.getLastIdUpdate();	 
		 Contact c7 =  new ContactImpl(id7, "Alan", "Big Guy");
		 
		 cm.addNewContact("Guy", "Slim Guy");
		 int id8 = cm.getLastIdUpdate();		 
		 Contact c8 =  new ContactImpl(id8, "Guy", "Slim Guy");
		 
		 cm.addNewContact("Mark", "Shifty");
		 int id9 = cm.getLastIdUpdate();
		 Contact c9 =  new ContactImpl(id9, "Mark", "Shifty");	 

	 		 
			// Create Individual SubSets of Contacts
			// --------------------------------------
				 
		 Set<Contact> harrySet = new HashSet<Contact>();		 
		 Set<Contact> jill1Set = new HashSet<Contact>();	
		 Set<Contact> jill2Set = new HashSet<Contact>();	
		 Set<Contact> jackSet = new HashSet<Contact>();	
		 Set<Contact> jimSet = 	new HashSet<Contact>();	
		 Set<Contact> sophieSet = new HashSet<Contact>();	
		 Set<Contact> alanSet = new HashSet<Contact>();	
		 Set<Contact> guySet = 	new HashSet<Contact>();	
		 Set<Contact> markSet = new HashSet<Contact>();	
		 
		 
		harrySet.add(c1); 
		jill1Set.add(c2);
		jill2Set.add(c3);
		jackSet.add(c4);
		jimSet.add(c5);
		sophieSet.add(c6);
		alanSet.add(c7);
		guySet.add(c8);
		markSet.add(c9);
		
		// Create Pair Sets for Meetings
		// -----------------------------
		
		Set<Contact> harryJillSet = new HashSet<Contact>();  
		Set<Contact> JimJackSet = new HashSet<Contact>();
		 
		 Contact c1Copy = c1;
		 Contact c2Copy = c2;
		 harryJillSet.add(c1Copy);
		 harryJillSet.add(c2Copy);
		 
		 Contact c4Copy = c4;
		 Contact c5Copy = c5;
		 JimJackSet.add(c4Copy);
		 JimJackSet.add(c5Copy);
		 
		// List of id's for jack set
		// -------------------------
		
		jimJackIds = new ArrayList<Integer>();
		jimJackIds.add(id4);
		jimJackIds.add(id5);
		
		harryJillIds = new ArrayList<Integer>();
		harryJillIds.add(id1);
		harryJillIds.add(id2);
		
		// Create Meetings
		// ---------------
	
		 cm.addNewPastMeeting(harrySet, calPast,"Meeting went well"); 
		 int id1pm = cm.getLastIdUpdate();
		 pm1 = new PastMeetingImpl(id1pm, harrySet, calPast,"Meeting went well");
		 
		 cm.addFutureMeeting(jill1Set , mar15);		 
		 cm.addFutureMeeting(jill2Set, mar15);		 
		 cm.addFutureMeeting(jackSet, apr15);
		 int id3m = cm.getLastIdUpdate();
		 fm3 = new FutureMeetingImpl(id3m, jackSet, apr15);
		 
		 cm.addFutureMeeting(jimSet, may15);		 
		 cm.addFutureMeeting(sophieSet, jun15);	 
		 cm.addFutureMeeting(alanSet, jun15);		 
		 cm.addFutureMeeting(guySet, jun15);
		 cm.addFutureMeeting(markSet, aug15);

		 String notes = "Likes a Drink";
		 cm.flush();
		 cm2 = new ContactManagerImpl();
		 mMap = cm2.getMeetingMap();
		 cMap = cm2.getContactMap();
	}
	
	/**
	 * Tests whether makeContact() can create a Contact from a String
	 */
	@Test
	public void testMakeContact(){
		String s = "1234,Harry,Likes a Drink";
		Contact c = cm.makeContact(s);
		assertEquals("test makeContact check id: ",1234, c.getId());
		assertEquals("test makeContact check name: ", "Harry", c.getName());
		assertEquals("test makeContact check notes: ", "Likes a Drink", c.getNotes());
	}
	
	/**
	 * Tests whether makeMeeting() can create a FutureMeeting from a String
	 */
	@Test
	public void testMakeFutureMeeting(){
		String s = fm3.toString();
		FutureMeeting m = cm.makeMeeting(s);
		assertEquals("test makeMeeting check id: ", fm3.getId(), m.getId());
		assertEquals("test makeMeeting check calendar: ", fm3.getDate(), m.getDate());
		for(Contact i : m.getContacts()){
			assertTrue("test makeMeeting check contacts: ", jimJackIds.contains(i.getId()));
		}
	}
	
	/**
	 * Tests whether makeMeeting() can create a PastMeeting from a String
	 */
	@Test
	public void testMakePastMeeting(){
		String s = pm1.toString();
		PastMeeting m = cm.makeMeeting(s);
		assertEquals("test makeMeeting check id: ", pm1.getId(), m.getId());
		assertEquals("test makeMeeting check calendar: ", pm1.getDate(), m.getDate());
		for(Contact i : m.getContacts()){
			assertTrue("test makeMeeting check contacts: ", harryJillIds.contains(i.getId()));
		}
		assertEquals("test makeMeeting check notes: ", pm1.getNotes(), m.getNotes());
	}
	
	/**
	 * tests whether a Map<Integer id, Meeting m> can be created from file, "Contacts.txt"
	 */
	@Test
	public void testMeetingExport(){
		assertEquals("testMeetingMap Size: ", cm.getMeetingMap().size(), mMap.size());
		for(Integer i : mMap.keySet()){
			assertTrue("testMeetingMap Content: ", cm.getMeetingMap().containsKey(i));
		}
	}
	
	/**
	 * tests whether a Map<Integer id, Contact c> can be created from file, "Contacts.txt"
	 */
	@Test
	public void testContactExport(){
		assertEquals("testContactMap Size: ", cm.getContactMap().size(), cm2.getContactMapSize());
		for(Integer i : cMap.keySet()){
			assertTrue("testContactMap Content: ", cm.getContactMap().containsKey(i));
		}
	}
	
}
