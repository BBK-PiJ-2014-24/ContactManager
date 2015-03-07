package test;

import static org.junit.Assert.*;
import iinterfaces.Contact;
import iinterfaces.DataStorage;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
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
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataStorageTest {


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
		
		DataStorage ds;
		ContactManagerImpl cm;
		
		ArrayList<Integer> idMeetingList;
		ArrayList<Integer> contactList;
		
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
			ds = new DataStorageImpl();
					
			// Create Contacts
			// ------------
			 contactList = new ArrayList<Integer>();
			 
			 cm.addNewContact("Harry","Likes a Drink");
			 contactList.add(cm.getLastIdUpdate());
			
			 cm.addNewContact("Jill","Likes White Wine");
			 contactList.add(cm.getLastIdUpdate());
			
			 cm.addNewContact("Jill","Likes Red Wine");
			 contactList.add(cm.getLastIdUpdate());
						 
			 cm.addNewContact("Jack", "Teetotal");
			 contactList.add(cm.getLastIdUpdate());

			 cm.addNewContact("Jim", "BUSY MAN");
			 contactList.add(cm.getLastIdUpdate());	
			 
			 cm.addNewContact("Sophie", "Angel");
			 contactList.add(cm.getLastIdUpdate());
			 			
		     cm.addNewContact("Alan", "Big Guy");
		     contactList.add(cm.getLastIdUpdate());
		     		     
			 cm.addNewContact("Guy", "Slim Guy");
			 contactList.add(cm.getLastIdUpdate());
			 			 
			 cm.addNewContact("Mark", "Shifty");
			 contactList.add(cm.getLastIdUpdate());
			
			 
			// Create SubSet of Contacts
			// ----------------------
			
			emptyContactSet = null;
			
			jimJillSet = new HashSet<Contact>();
			jimJillSet.add(jill);
			jimJillSet.add(jim);
			
			harrySophieSet = new HashSet<Contact>();
			harrySophieSet.add(harry);
			harrySophieSet.add(sophie);
			
			// Notes
			// -----
			
			notes = "Meeting went well";
			
			// Create Meetings
			// ---------------
			
			 idMeetingList = new ArrayList<Integer>();
			 cm.addNewPastMeeting(jimJillSet, calPast,"Meeting went well");  // add BAD Past meeting to ContactManager
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(jimJillSet, mar15);  // add Future meetings to ContactManager
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(harrySophieSet, mar15);
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(jimJillSet, apr15);
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(jimJillSet, may15);
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(jimJillSet, jun15);
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(harrySophieSet, jun15);
			 idMeetingList.add(cm.getLastIdUpdate());
			 
			 cm.addFutureMeeting(harrySophieSet, aug15);
			 idMeetingList.add(cm.getLastIdUpdate());
		}
		
		@Test
		public void testMeetingStorage(){
		
			assertEquals("testMeetingStorage Size: ",idMeetingList.size(), ds.getMeetingData().size());
			for(Integer i: idMeetingList){
				assertTrue("testMeetingStorage Content: ", ds.getMeetingData().containsKey(i));
			}
		}
		
		
		public void testContactStorage(){
			assertEquals("testContactStorage Size: ", contactList.size(), ds.getContactData().size());
			for(Integer i : contactList){
				assertTrue("testContactStorage Content: ", ds.getContactData().containsKey(i));
			}
		}
		
	
	

}
