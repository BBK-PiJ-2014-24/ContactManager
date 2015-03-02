package impls;

import iinterfaces.Meeting;

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
		
		if(m1.getDate().before(m2)) return -1;
		if(m1.getDate().after(m2)) return 1;
		return 0;
	}

}
