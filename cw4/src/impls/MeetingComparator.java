// FINAL
package impls;

import iinterfaces.Meeting;

import java.util.Calendar;
import java.util.Comparator;

/**
 * A Comparator class so as to Order Meeting Lists in Chronological Order
 * @author snewnham
 *
 */
public class MeetingComparator implements Comparator{

	/**
	 * compare method to order Meetings in Chronological Order
	 */
	@Override
	public int compare(Object o1, Object o2) {
		
		Meeting m1 = (Meeting) o1;
		Meeting m2 = (Meeting) o2;
		
		Calendar cal1 = m1.getDate();
		Calendar cal2 = m2.getDate();
		
		
		if(cal1.getTime().before(cal2.getTime())) return -1;
		if(cal1.getTime().equals(cal2.getTime())) return 0;
		return 1;
	}

}
