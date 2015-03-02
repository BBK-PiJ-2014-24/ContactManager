package impls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import iinterfaces.MeetingList;
import iinterfaces.PastMeeting;


public class ContactManagerImpl extends Exception implements ContactManager {
	
	// Fields
	// ------
	private Date today;
	private Map<Integer,Contact> contactMap;
	private Map<Integer, Meeting> meetingMap;
	private int lastIdUpdate;		// Used to grab a Meeting ID for Testing.
	
	
	
	// Constructor
	// -----------
	public ContactManagerImpl(){
		
		Calendar cal = new GregorianCalendar();  // Set Up Calendar Format
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		cal.setTimeZone(tz);
		today = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		
		meetingMap = new HashMap<Integer, Meeting>();   // Instant MeetingList and ContactSet
		contactMap = new HashMap<Integer, Contact>();
		
	}

	// addFutureMeeting()
	// ------------------
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
			Date meetingDate = date.getTime();
		
			if(meetingDate.after(today)){
				int id = 0;
				while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the future meeting
					id = IdGenerator.generateID("meetingId");
				}
				FutureMeeting fm = new FutureMeetingImpl(id, contacts, date);
				meetingMap.put(id, fm);
				return fm.getId();  // Dummy ID 
			}
			else{
				throw new IllegalArgumentException("Date Must Be In The Future");
			}			
	}  // end addFutureMeeting()

	
	// getFutureMeeting()
	// ------------------
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		
		Meeting FoundMeeting;
		FoundMeeting = meetingMap.get(id);
		Date meetingDate = FoundMeeting.getDate().getTime();
		
		if(meetingDate.after(today)){
				return (FutureMeeting)FoundMeeting;
		}
		else
			return null;
	} // end getFutureMeeting
	
	

	// addNewPastMeeting()
	// -------------------
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) 
	throws IllegalArgumentException, NullPointerException
	{
		if(contacts == null){
			throw new IllegalArgumentException("Contact Set Is Empty");
		}
		else if(date== null ||text == null || text == ""){
			throw new NullPointerException("Date and notes Cannot be Empty");
		}
		else{
			int id = 0;
			while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the Past meeting
				id = IdGenerator.generateID("meetingId");
			}
			PastMeeting pm = new PastMeetingImpl(id, contacts, date, text);
			meetingMap.put(id, pm);
			lastIdUpdate = id;   // records the randomly generated ID 
		}
	} //end addNewPastMeeting
	
	
	@Override
	public PastMeeting getPastMeeting(int id) {
		Meeting FoundMeeting;
		FoundMeeting = meetingMap.get(id);
		if(FoundMeeting == null){
			return null;
		}
		else{	
			Date meetingDate = FoundMeeting.getDate().getTime();
			if(meetingDate.before(today)){
					return (PastMeeting)FoundMeeting;
			}
			else{
			return null;
			}
		}
	}
	
	
	/**
	 * returns the latest ID number when a new Meeting Object has been Instantiated.
	 * @return ID of the last Instant Meeting
	 */
	public int getLastIdUpdate(){
		return lastIdUpdate;
	}

	@Override
	public Meeting getMeeting(int id) {
		Meeting FoundMeeting;
		FoundMeeting = meetingMap.get(id);
		return FoundMeeting;
	}

	@Override
	public void addNewContact(String name, String notes) throws IllegalArgumentException {
		
		if(name == null || name == ""){
			throw new IllegalArgumentException("Invalid Name Entry");
		}
		else if(notes == null || notes == ""){
			throw new IllegalArgumentException("Invalid Notes Entry");
		}
		else{
			int id = 0;
			while(id == 0 || contactMap.containsKey(id)){  // generate an id for the Contact
				id = IdGenerator.generateID("contactId");
			}
			Contact newContact = new ContactImpl(id, name, notes); 
			contactMap.put(id, newContact);
			lastIdUpdate = id;   // records the randomly generated ID 
		}
	}
	
	/**
	 * Finds the size of the Contact Map
	 * @return the number of contacts in the Map
	 */
	public int getContactMapSize(){
		return contactMap.size();
	}

	@Override
	public Set<Contact> getContacts(int... ids) {
		
		Set<Contact> set = new HashSet<Contact>();
		
		for(int i : ids){
			if(contactMap.containsKey(i) == false){
				throw new IllegalArgumentException("Invalid ID Entry");
			}	
			else{
				Contact c = contactMap.get(i);
				set.add(c);
			} // end else				
		} // end loops
		return set;
	} // end getContacts

	@Override
	public Set<Contact> getContacts(String name) throws IllegalArgumentException {
		Set<Contact> set = new HashSet<Contact>();
		if(name == null || name == ""){
			throw new IllegalArgumentException("Invalid Name Entry");
		}
		else{
			for(Contact i : contactMap.values()){
					if(i.getName().equals(name)){
						set.add(i);
					}
			} // end loop
		} // end else	
		return set;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		Comparator mComparator = new MeetingComparator();  // A Comparator for Ordering
														   // Meetings in Chrono Order.		
		if(!contactMap.containsValue(contact)){
			throw new IllegalArgumentException("Invalid Contact");
		}
		else{
			for(Meeting i : meetingMap.values()){
				if(i.getClass().equals(FutureMeetingImpl.class)){
					if(i.getContacts().contains(contact)){
						mList.add(i);
					}
				}
			} // end loop
		} // end else
		
		Collections.sort(mList,mComparator);
		
		return mList;	
	}


	




	
	

	

	
}
