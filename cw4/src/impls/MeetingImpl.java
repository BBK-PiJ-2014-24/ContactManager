package impls;

import java.util.Calendar;
import java.util.Set;

import iinterfaces.Contact;
import iinterfaces.Meeting;


public class MeetingImpl implements Meeting {
	
	// Fields
	// ------
	
	public int id;
	private Calendar date;
	private Set contactSet;
	
	
	// Constructor
	// -----------
	
	public MeetingImpl(Set contactSet, Calendar date){
		this.contactSet = contactSet;
		this.date = date;
	}


	// getter/setter
	// -------------
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contactSet;
	}



	
	

}
