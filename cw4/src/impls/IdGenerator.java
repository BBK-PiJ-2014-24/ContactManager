package impls;
/**
 * RETURNS a randomly generated ID number. For contact IDs, the ID ranges from 10,000-100,000.
 * For meeting ID's, the ID range is from 1,000,000 - 10,000,000.
 * @param idType Determines 
 * @author snewnham
 *
 */
public class IdGenerator {
	
	
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
