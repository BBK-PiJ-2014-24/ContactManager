package impls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.junit.Test;

import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.DataStorage;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import iinterfaces.MeetingList;
import iinterfaces.PastMeeting;


public class ContactManagerImpl extends Exception implements ContactManager {
	
	// Fields
	// ------
	private Date today;
	private Map<Integer,Contact> contactMap;
	private Map<Integer, Meeting> meetingMap;
	private int lastIdUpdate;		// Used to grab a Meeting ID for Testing.
	private Calendar cal = new GregorianCalendar();  // Set Up Calendar Format
	private TimeZone tz = TimeZone.getTimeZone("Europe/London");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
	private DataStorage dataStorage;
	
	// Constructor
	// -----------
	public ContactManagerImpl(){
		
		cal.setTimeZone(tz);
		today = cal.getTime();
		meetingMap = new HashMap<Integer, Meeting>();   // Instant MeetingList and ContactSet
		contactMap = new HashMap<Integer, Contact>();
		dataStorage = new DataStorageImpl();
	}
	
	
	// getter/setter
	// -------------
	/**
	 * Set the Today's Date to a Fictitious date in order to help With Testing 
	 * @param aprFool - a Fictitious Today Date
	 */
	public void setTodayDate(Calendar aprFool){   //Used Only For Testing
		today = aprFool.getTime();
	}
	/**
	 * A Setter - Resets the "Today's" Date to today. 
	 */
	public void resetToday(){
		today = cal.getTime();
	}
	
	/**
	 * a Getter - Returns the DataStorage Object storing the Meeting and Contact Maps.
	 * @return Returns the DataStorage Object storing the Meeting and Contact Maps.
	 */
	public DataStorage getDatStorage(){
		return dataStorage;
	}
	
	/**
	 * A Getter for meetingMap, the map of meetings contained in ContactManager
	 * @return the map(id, Meeting)
	 */
	public Map<Integer, Meeting> getMeetingMap(){
		return meetingMap;
	}
	
	/**
	 * A Getter for contactMap, the map of Contact contained in ContactManager
	 * @return the map(id, Contact)
	 */	
	public Map<Integer, Contact> getContactMap(){
		return contactMap;
	}
	

	// addFutureMeeting()
	// ------------------
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		
			Date meetingDate = date.getTime();
		
			if(meetingDate.after(today)){
				int id = 0;
				while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the future meeting
					id = IdGenerator.generateID("meetingId");
				}
				FutureMeeting fm = new FutureMeetingImpl(id, contacts, date);
				meetingMap.put(id, fm);
				lastIdUpdate = id;  // recorded for testing purposes
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
	} // end getFutureMeeting
	
	

	// addNewPastMeeting()
	// -------------------
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) 
	throws IllegalArgumentException, NullPointerException
	{
		if(contacts == null){
			throw new IllegalArgumentException("Contact Set Is Empty");
		}
		else if(date== null ||text == null || text == ""){
			throw new NullPointerException("Date and notes Cannot be Empty");
		}
		else{
			int id = 0;
			while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the Past meeting
				id = IdGenerator.generateID("meetingId");
			}
			PastMeeting pm = new PastMeetingImpl(id, contacts, date, text);
			meetingMap.put(id, pm);
			lastIdUpdate = id;   // records the randomly generated ID 
		}
	} //end addNewPastMeeting
	
	// getPastMeeting(id)
	// -------------------
	@Override
	public PastMeeting getPastMeeting(int id) {
		Meeting FoundMeeting;
		FoundMeeting = meetingMap.get(id);
		if(FoundMeeting == null){
			return null;
		}
		else{	
			Date meetingDate = FoundMeeting.getDate().getTime();
			if(meetingDate.before(today)){
					return (PastMeeting)FoundMeeting;
			}
			else{
			return null;
			}
		}
	}
	
	// getLastIdUpdate()
	// -----------------
	/**
	 * returns the latest ID number when a new Meeting Object has been Instantiated.
	 * @return ID of the last Instant Meeting
	 */
	public int getLastIdUpdate(){
		return lastIdUpdate;
	}

	
	// getMeeting(id)
	// --------------
	@Override
	public Meeting getMeeting(int id) {
		Meeting FoundMeeting;
		FoundMeeting = meetingMap.get(id);
		return FoundMeeting;
	}

	// addNewContact(name, notes)
	@Override
	public void addNewContact(String name, String notes) throws IllegalArgumentException {
		
		if(name == null || name == ""){
			throw new IllegalArgumentException("Invalid Name Entry");
		}
		else if(notes == null || notes == ""){
			throw new IllegalArgumentException("Invalid Notes Entry");
		}
		else{
			int id = 0;
			while(id == 0 || contactMap.containsKey(id)){  // generate an id for the Contact
				id = IdGenerator.generateID("contactId");
			}
			Contact newContact = new ContactImpl(id, name, notes); 
			contactMap.put(id, newContact);
			lastIdUpdate = id;   // records the randomly generated ID 
		}
	}
	
	/**
	 * Finds the size of the Contact Map
	 * @return the number of contacts in the Map
	 */
	public int getContactMapSize(){
		return contactMap.size();
	}

	// getContacts(ids..)
	@Override
	public Set<Contact> getContacts(int... ids) {
		
		Set<Contact> set = new HashSet<Contact>();
		
		for(int i : ids){
			if(contactMap.containsKey(i) == false){
				throw new IllegalArgumentException("Invalid ID Entry");
			}	
			else{
				Contact c = contactMap.get(i);
				set.add(c);
			} // end else				
		} // end loops
		return set;
	} // end getContacts

	// getContacts(name)
	@Override
	public Set<Contact> getContacts(String name) throws IllegalArgumentException {
		Set<Contact> set = new HashSet<Contact>();
		if(name == null || name == ""){
			throw new IllegalArgumentException("Invalid Name Entry");
		}
		else{
			for(Contact i : contactMap.values()){
					if(i.getName().equals(name)){
						set.add(i);
					}
			} // end loop
		} // end else	
		return set;
	}

	// getFutureMeetingList(contact)
	// -----------------------------
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException {
		
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		Comparator mComparator = new MeetingComparator();  // A Comparator for Ordering
														   // Meetings in Chrono Order.		
		if(!contactMap.containsValue(contact)){
			throw new IllegalArgumentException("Invalid Contact");
		}
		else{
			for(Meeting i : meetingMap.values()){
				if(i.getClass().equals(FutureMeetingImpl.class)){
					if(i.getContacts().contains(contact)){
						mList.add(i);
					}
				}
			} // end loop
		} // end else
		
		Collections.sort(mList,mComparator);
		
		return mList;	
	}

	// getFutureMeetingList(date)
	// --------------------------
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		
		Date targetDate = date.getTime();
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		Comparator mComparator = new MeetingComparator();  // A Comparator for Ordering
														   // Meetings in Chrono Order.	
		
		
		for(Meeting i : meetingMap.values()){
			Date possibleDate = i.getDate().getTime();
			if(possibleDate.equals(targetDate)){
				mList.add(i);
			}
		} // end loop
		
		Collections.sort(mList,mComparator);
		
		return mList;
	}

	// getPastMeetingList(Contact)
	// ---------------------------
	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException {
		
		ArrayList<PastMeeting> mList = new ArrayList<PastMeeting>();
		Comparator mComparator = new MeetingComparator();  // A Comparator for Ordering
														   // Meetings in Chrono Order.		
		if(!contactMap.containsValue(contact)){
			throw new IllegalArgumentException("Invalid Contact");
		}
		else{
			for(Meeting i : meetingMap.values()){
				if(i.getClass().equals(PastMeetingImpl.class)){
					if(i.getContacts().contains(contact)){
						mList.add((PastMeeting) i);
					}
				}
			} // end loop
		} // end else
		Collections.sort(mList,mComparator);
		
		return mList;	
	}

	// addMeetingNotes(id, text)
	// -------------------------
	@Override
	public void addMeetingNotes(int id, String text) throws IllegalArgumentException, NullPointerException{
		
	    boolean foundMeeting = false;
	    FutureMeeting targetMeeting = null;
		    
	    // Testing for Exceptions
		if(text == null){
			throw new NullPointerException("The Notes Entry is Invalid");
		}
			    
	    for(Integer i : meetingMap.keySet()){
	    	if(i == id){
	    		foundMeeting = true;
	    		targetMeeting = (FutureMeeting) meetingMap.get(id);
	    		break;
	    	}
	    }
	    if(foundMeeting == false)
	    	throw new IllegalArgumentException("Invalid Meeting");
	    
	    if(targetMeeting.getDate().getTime().after(today))
	    	throw new IllegalArgumentException("Meeting Is Still In Future");
	    
	    // Grab State Info of FutureMeeting
	    Set<Contact> meetingContacts = targetMeeting.getContacts(); // Obtain FutureMeeting Contacts 
	    Calendar meetingDate = targetMeeting.getDate();  // Obtain FutureMeeting Date
	    
	    // Instant a PastMeeting with the old FutureMeeting State
	    PastMeeting pm = new PastMeetingImpl(id, meetingContacts, meetingDate, text); 
	    meetingMap.put(id, pm);  // Map the new PastMeeting to where the old FutureMeeeting Was.	 		
	}

	// flush()
	// -------
	@Override
	public void flush() {	
		
		File file = new File("Contacts.txt");
		BufferedWriter bw = null;
		
		try{
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			for(Contact i : contactMap.values()){
				bw.write(i.toString() + "\n");
				System.out.println( i.toString());
			} // end loop
			
			for(Meeting i : meetingMap.values()){
				bw.write(i.toString() + "\n");
			} // end loop
		}// end try
		catch (IOException ex1){
				System.out.println("File Not Writable: " + file.toString());
				ex1.printStackTrace();
		}
		finally{
			try{
				bw.close();
			}
			catch(IOException ex2){
				System.out.println("File Can't Be Closes");
			}
		}
	} // end flush()
		
		
		/*
		dataStorage.setMeetingData(meetingMap);
		dataStorage.setContactData(contactMap);
		
		try{
			FileOutputStream fs = new FileOutputStream("Contacts.txt");
			System.out.println("FileOutputStream");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			System.out.println("ObjectOutputStream");
			os.writeObject(dataStorage);
			System.out.println("WRITE");
			os.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		*/
	
	/**
	 * Instantiates a Contact Object From Arguments formatted in a String
	 * @param strInput a String of Contact arguments (id,name,notes). String delimiter is ","
	 * @return a Contact Object with states specified in strInput 
	 */
	public Contact makeContact(String strInput){
				
		String delim = ",";
		StringTokenizer inputStream = new StringTokenizer(strInput, delim);
		
		String tok1 = inputStream.nextToken();
		int id = Integer.parseInt(tok1);
		String name = inputStream.nextToken();
		String notes = inputStream.nextToken();
		
		Contact c = new ContactImpl(id, name, notes);
		contactMap.put(id, c);
		return c;
	}
	/**
	 * Instantiates a Future or PastMeeting Object from Arguments formatted in a String
	 * @param strInput a String of Meeting arguments (id,date, contacts + optional notes). String delimiter is ","
	 * @return a Future or PastMeeting Object.
	 */
	public<T extends Meeting> T makeMeeting(String strInput){
		
		// id + "," + date + "," + contactSet 
		
		String delim = ",";
		StringTokenizer inputStream = new StringTokenizer(strInput, delim);
		
		String tok1 = inputStream.nextToken();  // convert String to Meeting id
		int id = Integer.parseInt(tok1);
		
		String tok2 = inputStream.nextToken();   // convert String to Meeting Calendar
		Date recoverDate = null;
		try {
			recoverDate = sdf.parse(tok2);
		} catch (ParseException e) {
			System.out.println("Failure to Parse Date");
			e.printStackTrace();
		}
		Calendar meetingCal = Calendar.getInstance();
		meetingCal.setTime(recoverDate);
		
		
		Set<Contact> cSet = new HashSet<Contact>(); // convert String to a Set of Contacts
		boolean endOfContactList = false;
		while(!endOfContactList){
			String tokcId = inputStream.nextToken();
			if(tokcId.charAt(0) == '[' || tokcId.charAt(0) == ' ')  // Check if beginning of set
				tokcId= tokcId.substring(1); // remove [ from first token = Contact ID.
			String tokcName = inputStream.nextToken(); // = Contact Name
			String tokcNotes = inputStream.nextToken(); // = Contact Notes
			if(tokcNotes.charAt(tokcNotes.length()-1) == ']'){  // Check if last of set
				tokcNotes = tokcNotes.substring(0,tokcNotes.length()-1);  // if so, remove ]
				endOfContactList = true;
			}
			String contactString = tokcId + "," + tokcName + "," + tokcNotes;
			Contact c = makeContact(contactString);
			cSet.add(c);
		} // end while
		
		if(inputStream.hasMoreTokens()){
			String meetingNotes = inputStream.nextToken();
			PastMeeting pm = new PastMeetingImpl(id, cSet, meetingCal, meetingNotes);
			meetingMap.put(id, pm);
			return (T) pm;
		}
		else{
			FutureMeeting fm = new FutureMeetingImpl(id, cSet, meetingCal);
			meetingMap.put(id, fm);
			return (T) fm;
		}
	}
	
	
	



	
}
	
	

	


