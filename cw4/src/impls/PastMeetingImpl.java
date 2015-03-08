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
	public PastMeetingImpl(int id, Set contactSet, Calendar date, String notes) {
		super(id, contactSet, date);
		this.notes = notes;
	}

	// getter/setter
	// -------------
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes){
		this.notes = notes;
	}
	
	@Override
	public String toString(){
		 String s = super.toString();
		 return s + "," + notes;
	}
	
	
}
