package impls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import iinterfaces.PastMeeting;


public class ContactManagerImpl extends Exception implements ContactManager {
	
	// Fields
	// ------
	private Date today;
	private Set<Contact> contactSet;
	private Map<Integer,Meeting> meetingMap;
	
	
	// Constructor
	// -----------
	public ContactManagerImpl(){
		// Set Up Calendar Format
		Calendar cal = new GregorianCalendar();
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		cal.setTimeZone(tz);
		today = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		
		//Instant MeetingList and ContactSet
		meetingMap = new HashMap<Integer,Meeting>();
		contactSet = new HashSet<Contact>();
		
	}

	// addFutureMeeting()
	// ------------------
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
			Date meetingDate = date.getTime();
		
			if(meetingDate.after(today)){
				FutureMeetingImpl fm = new FutureMeetingImpl(contacts, date);
				int id = 0;
				while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the future meeting
					id = IdGenerator.generateID("meetingId");
				}
				fm.setId(id);
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
	}


	
	

	

	
}
