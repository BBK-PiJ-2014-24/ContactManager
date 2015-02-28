package iinterfaces;

import java.util.Calendar;

public interface MeetingList {
	/**
	 * adds Meeting to a List of meetings and returns id.
	 * @param m - meeting
	 * @returns - id
	 */
	int add(Meeting m);

	/**
	 * sets the ID
	 */
	void setID();
	
	 /**
	 * gets the ID of the meetings
	 */
	int getID();

}
