package impls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;

import iinterfaces.Contact;
import iinterfaces.Meeting;


public class MeetingImpl implements Meeting {
	
	// Fields
	// ------
	
	private int id;
	private Calendar date;
	private Set contactSet;
	
	
	// Constructor
	// -----------
	
	public MeetingImpl(int id, Set contactSet, Calendar date){
		this.id  = id;
		this.contactSet = contactSet;
		this.date = date;
	}


	// getter/setter
	// -------------
	
	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contactSet;
	}
	
	@Override
	public String toString(){
		
		TimeZone tz = TimeZone.getTimeZone("Europe/London");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		String dateString = sdf.format(date);
		
		return ""+ id + "," + dateString + "," + contactSet ;
	}
	
}
