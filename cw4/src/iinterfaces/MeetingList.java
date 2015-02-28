package iinterfaces;

import java.util.Calendar;

public interface MeetingList {
	/**
	 * puts Meetings in a map
	 * @param id - meeting id
	 * @param m - meeting
	 */
	void put(Integer id, Meeting m);

	/**
	 * sets the ID
	 */
	void setID();
	
	 /**
	 * gets the ID of the meetings
	 */
	int getID();

}
