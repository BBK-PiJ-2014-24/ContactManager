package impls;

/**
 * A Class to Genrate IDs for Contacts and Meetings
 * @author snewnham
 *
 */
public class IdGenerator {
	/**
	 * RETURNS a randomly generated ID number. For contact IDs, the ID ranges from 10,000-100,000.
	 * For meeting ID's, the ID range is from 1,000,000 - 10,000,000.
	 * @param idType Determines the type of ID - "contactID" or "meetingID"
	 * @return a randomly generated ID number
	 * @author snewnham
	 */	
	public static int generateID(String idType){
		
		int scalar = 0;
		
		
		switch(idType){
			case "contactId": scalar = 10000;
			break;
			
			case "meetingId" : scalar = 1000000;
			break;
			
			default: System.out.println("ID Error");
			break;
		} // end Switch
		
		int x = 0;
		int min = scalar;

		while(x<min){
			x = (int) (Math.random()*scalar*10);
		}
		return x;
		
	}
}
