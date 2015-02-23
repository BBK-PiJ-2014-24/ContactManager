package impls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;


public class ContactManagerImpl extends Exception implements ContactManager {
	
	// Fields
	// ------
	private Date today;
	//private Calendar date;
	private Set<Contact> contactSet;
	private List<Meeting> meetingList;
	
	
	// Constructor
	// -----------
	public ContactManagerImpl(){
		// Set Up Calendar Format
		Calendar cal = new GregorianCalendar();
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		cal.setTimeZone(tz);
		today = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		System.out.println("Time of Day: " + sdf.format(today));
		
		//Instant MeetingList and ContactSet
		meetingList = new ArrayList<Meeting>();
		contactSet = new HashSet<Contact>();
		
	}

	// addFutureMeeting()
	// ------------------
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
			Date meetingDate = date.getTime();
		
			if(meetingDate.after(today)){
				FutureMeeting fm = new FutureMeetingImpl(contacts, date);
				meetingList.add(fm);
				return 1234;  // Dummy ID 
			}
			else{
				throw new IllegalArgumentException("Date Must Be In The Future");
			}
	}
	
	

	
}
