package impls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	private String fileName = "Contacts.txt";
	
	// Constructor
	// -----------
	public ContactManagerImpl(){
		cal.setTimeZone(tz);
		today = cal.getTime();
		meetingMap = new HashMap<Integer, Meeting>();   // Instant MeetingList and ContactSet
		contactMap = new HashMap<Integer, Contact>();
		dataStorage = new DataStorageImpl();
		File file = new File(fileName);
		if(file.exists())
			loadContactsMeetings(); // Loads Contacts and Meetings stored on file (Contacts.txt)
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
		boolean isInMap = false;
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
			isInMap = copyContact(newContact);
			if(!isInMap){
				contactMap.put(id, newContact);
				lastIdUpdate = id;   // records the randomly generated ID
			}
			else
				System.out.println("Contact " + newContact.getName() + " Is Already On File");
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
		
	
	/**
	 * Retuens a Contact Object From Arguments formatted in a String. The method also adds the
	 * Contact to the contactMap if it is not a copy. 
	 * @param strInput a String of Contact arguments (id,name,notes). Note that the String delimiter is ","
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
		boolean checkInMap = copyContact(c);
		if(!checkInMap)
			contactMap.put(id, c);
		return c;
	}
	/**
	 * Returns a Future or PastMeeting Object from Arguments formatted in a String. The Method also 
	 * adds the Meeting to the meetingMap (the Map containing all the Meetings in ContactManager) 
	 * if it is not a copy. 
	 * @param strInput - a String of Meeting arguments (id,date, contacts + optional notes). 
	 * Note that the String delimiter is ","
	 * @return a Future or PastMeeting Object.
	 */
	public<T extends Meeting> T makeMeeting(String strInput){
	
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
		
		boolean checkInMap = false;
		if(inputStream.hasMoreTokens()){
			String meetingNotes = inputStream.nextToken();
			PastMeeting pm = new PastMeetingImpl(id, cSet, meetingCal, meetingNotes);
			checkInMap = copyMeeting(pm);
			if(!checkInMap)
				meetingMap.put(id, pm);
			return (T) pm;
		}
		else{
			FutureMeeting fm = new FutureMeetingImpl(id, cSet, meetingCal);
			if(!checkInMap)
				meetingMap.put(id, fm);
			return (T) fm;
		}
	}  // end makeMeeting
	
	/**
	 * A private method that checks if a Contact is already in the ContactManager's contactMap 
	 * database by checking whether its id or its Name & Notes are the same.
	 * @param c Contact to be tested if it is in the ContactManager's contactMap database
	 * @return return true if Contact c is a copy of an ContactManager's contactMap 
	 * database otherwise false
	 */
	private boolean copyContact(Contact c){
		
		if(contactMap.containsKey(c.getId())){
			System.out.println("Contact ID Already In The Contact's Database");	
			return true;
		}
		for(Contact i : contactMap.values()){
			if(i.getName().equals(c.getName())){
				if(i.getNotes().equals(c.getNotes())){
					System.out.println("Contact " + c.getName() + " with the same Notes is already on the Contact Database");
					return true;
				} // end notes if
			} // end name if
		} // end loop
		return false;	
	}
	
	/**
	 * A private method that checks if a Meeting is already in the ContactManager's meetingMap 
	 * database by checking its id.
	 * @param m Meeting to be tested if it is in the ContactManager's contactMap database
	 * @return return true if Contact c is a copy of an ContactManager's contactMap 
	 * database otherwise false
	 */
	private boolean copyMeeting(Meeting m){
		
		if(meetingMap.containsKey(m.getId())){
			return true;
		}
		return false;
	}
	
	/**
	 * Loads the Contact and Meeting data from previous sessions run on ContactManager. This
	 * Method is Called from the Constructor. 
	 */
	private void loadContactsMeetings(){
		// Set UP File Reader
		File file = new File("Contacts.txt");
		BufferedReader br = null;
		String delim = ",";
		int idThreshold = 100000;  // The Highest Contact id number. Used to Identify whether we are 
								   // reading in a Meeting or a Contact.	
		
		try{
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			// WHILE(there is NEXT LINE)
			String line;
			while((line=br.readLine()) != null){
				// READ LINE
				StringTokenizer inputStream = new StringTokenizer(line, delim);
				// Check the first token // Convert to int 
				String tok1 = inputStream.nextToken();
				int id = Integer.parseInt(tok1);
				
				if(id < idThreshold){
					makeContact(line);  // Reading in Contact Data
				}
				else
					makeMeeting(line);   // Reading in Meeting Data
							
			} // end while
		} // end try
		
		catch(FileNotFoundException ex1){
			System.out.println("Cannot Find File " + file.toString());
			ex1.printStackTrace();
		}
		catch(IOException ex2){
			System.out.println("Cannot OPen File " + file.toString());
			ex2.printStackTrace();
		}
		
		finally{
			 try{
				 br.close();
			 }
			 catch(IOException ex3){
				 System.out.println("Cannot Close File " + file.toString());
				 ex3.printStackTrace();
			 }
		}  // end finally				
	}  // end uploadContactsMeetings()
	

	
} // end Class
	
	

	


