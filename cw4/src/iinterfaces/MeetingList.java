package iinterfaces;

// DECOMMISIONED

import java.util.Calendar;

public interface MeetingList {
	/**
	 * adds Meeting to a List of meetings and returns id.
	 * @param m - meeting
	 * @returns - id
	 */
	void add(int id, Meeting m);
		
	/**
	 * Finds a Meeting in the List
	 * @param id - The Meeting ID
	 * @return the meeting being looked for in the list
	 */
	Meeting getMeeting(int id);

	/**
	 * sets the ID
	 */
	void setID();
	
	 /**
	 * gets the ID of the meetings
	 */
	int getID();

}
