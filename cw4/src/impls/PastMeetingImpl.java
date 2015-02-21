package impls;

import iinterfaces.PastMeeting;

import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	
	// Fields
	// ------
	private String notes;
	
	// Constructor
	// -----------
	public PastMeetingImpl(Set contactSet, Calendar date, String notes) {
		super(contactSet, date);
		this.notes = notes;
	}

	// getter/setter
	// -------------
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	
}
