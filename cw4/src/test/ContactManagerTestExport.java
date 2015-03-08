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
 * Tests for the I/O of the contact and meeting Maps using ContactManager method flush(). 
 * Meetings and Contacts are built in ContactManager cm, then flushed. A new ContactManager will
 * be instantiated and its contact and meeting maps will tested in those found in cm.
 * @author snewnham
 *
 */
public class ContactManagerTestExport {

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
	String badNotes;
	String nullNotes;
	
	DataStorage ds;
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
		
		// ContactManager
		// --------------
		
		cm = new ContactManagerImpl();
		ds = cm.getDatStorage();
				
		// Create Contacts
		// ------------
		 
		 cm.addNewContact("Harry","Likes a Drink");
		 int id1 = cm.getLastIdUpdate();
		 Contact c1 =  new ContactImpl(id1, "Harry","Likes a Drink");
		 
		 cm.addNewContact("Jill","Likes White Wine");
		 int id2 = cm.getLastIdUpdate();
		 Contact c2 =  new ContactImpl(id2, "Jill","Likes White Wine");
		 
		 cm.addNewContact("Jill","Likes White Wine");
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

		 contactList = new ArrayList<Contact>();    // Stored for testing
		 contactList.add(c1);
		 contactList.add(c2);
		 contactList.add(c3);
		 contactList.add(c4);
		 contactList.add(c5);
		 contactList.add(c6);
		 contactList.add(c7);
		 contactList.add(c8);
		 contactList.add(c9);
		 
		 
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
		harrySet.add(c2); // add Jill1 to harrySet
		jill1Set.add(c2);
		jill2Set.add(c3);
		jackSet.add(c4);
		jackSet.add(c5);  // add Jim to Jack set
		jimSet.add(c5);
		sophieSet.add(c6);
		alanSet.add(c7);
		guySet.add(c8);
		markSet.add(c9);

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
		 int id1m = cm.getLastIdUpdate();
		 fm1 = new FutureMeetingImpl(id1m,jill1Set , mar15);
		 
		 cm.addFutureMeeting(jill2Set, mar15);
		 int id2m = cm.getLastIdUpdate();
		 FutureMeeting fm2 = new FutureMeetingImpl(id2m,jill2Set, mar15);
		 
		 cm.addFutureMeeting(jackSet, apr15);
		 int id3m = cm.getLastIdUpdate();
		 fm3 = new FutureMeetingImpl(id3m, jackSet, apr15);
		 
		 cm.addFutureMeeting(jimSet, may15);
		 int id4m = cm.getLastIdUpdate();
		 FutureMeeting fm4 = new FutureMeetingImpl(id4m, jimSet, may15);
		 
		 cm.addFutureMeeting(sophieSet, jun15);
		 int id5m = cm.getLastIdUpdate();
		 FutureMeeting fm5 = new FutureMeetingImpl(id5m, sophieSet, jun15);
		 
		 cm.addFutureMeeting(alanSet, jun15);
		 int id6m = cm.getLastIdUpdate();
		 FutureMeeting fm6 = new FutureMeetingImpl(id6m, alanSet, jun15);
		 
		 cm.addFutureMeeting(guySet, jun15);
		 int id7m = cm.getLastIdUpdate();
		 FutureMeeting fm7 = new FutureMeetingImpl(id7m, guySet, jun15);
		 
		 cm.addFutureMeeting(markSet, aug15);
		 int id8m = cm.getLastIdUpdate();
		 FutureMeeting fm8 = new FutureMeetingImpl(id8m, markSet, aug15);

		 idMeetingList = new ArrayList<Meeting>(); // Stored for testing
		 idMeetingList.add(pm1);
		 idMeetingList.add(fm1);
		 idMeetingList.add(fm2);
		 idMeetingList.add(fm3);
		 idMeetingList.add(fm4);
		 idMeetingList.add(fm5);
		 idMeetingList.add(fm6);
		 idMeetingList.add(fm7);
		 idMeetingList.add(fm8);
		 
		 String notes = "Likes a Drink";
		 cm.flush();
		 cm2 = new ContactManagerImpl();
	}
	
	@Test
	public void testMakeContact(){
		String s = "1234,Harry,Likes a Drink";
		Contact c = cm.makeContact(s);
		assertEquals("test makeContact check id: ",1234, c.getId());
		assertEquals("test makeContact check name: ", "Harry", c.getName());
		assertEquals("test makeContact check notes: ", "Likes a Drink", c.getNotes());
	}
	
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
	
	@Test
	public void testMakePastMeeting(){
		String s = pm1.toString();
		PastMeeting m = cm.makeMeeting(s);
		System.out.println("+++ PM "  + s);
		System.out.println("harryJillids" + harryJillIds.toString());
		assertEquals("test makeMeeting check id: ", pm1.getId(), m.getId());
		assertEquals("test makeMeeting check calendar: ", pm1.getDate(), m.getDate());
		for(Contact i : m.getContacts()){
			assertTrue("test makeMeeting check contacts: ", harryJillIds.contains(i.getId()));
		}
		assertEquals("test makeMeeting check notes: ", pm1.getNotes(), m.getNotes());
	}
	
	
	@Test
	public void testMeetingExport(){
	
		mMap = cm2.getMeetingMap();
		assertEquals("testMeetingMap Size: ",idMeetingList.size(), mMap.size());
		for(Integer i : mMap.keySet()){
			assertTrue("testMeetingMap Content: ", idMeetingList.contains(i));
		}
	}
	
	@Test
	public void testContactExport(){
		
		cMap = cm2.getContactMap();
		assertEquals("testContactMap Size: ", contactList.size(), cm2.getContactMapSize());
		for(Integer i : cMap.keySet()){
			assertTrue("testContactMap Content: ", contactList.contains(i));
		}
	}
	
	


}
