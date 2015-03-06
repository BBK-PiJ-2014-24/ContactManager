package iinterfaces;

import java.util.Map;

/**
 * An Interface for a container, containing the Meeting and Contact data found in a ContactManager Object
 * @author snewnham
 *
 */
public interface DataStorage {

	/**
	 * Getter for Obtaining Meeting Data
	 * @return Map of MeetingData, with Meeting id as key and Meetings as values.
	 */
	public Map<Integer,Meeting> getMeetingData();
	
	/**
	 * Setter for Meeting Data. Map of MeetingData
	 * @param Map of meetings. Meeting id as key and Meetings as values
	 */
	public void setMeetingData(Map<Integer,Meeting> m);
	
	/**
	 * Getter for Obtaining Contact Data
	 * @return Map of Contact Data, with Contact id as key and Contact as values.
	 */
	public Map<Integer,Contact> getContactData();
	
	/**
	 * Setter for Contact Data. Map of Contact Data
	 * @param Map of Contact. Contact id as key and Contact as values
	 */
	public void setContactData(Map<Integer,Meeting> m);
	
	
}
