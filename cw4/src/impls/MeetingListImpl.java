package impls;

import java.util.HashMap;
import java.util.Map;

import iinterfaces.Meeting;
import iinterfaces.MeetingList;

public class MeetingListImpl implements MeetingList{

	// Fields
	// ------
	
	Map<Integer, Meeting> meetingMap = new HashMap<Integer,Meeting>();
	
	MeetingListImpl(){
		
	}
	
	@Override
	public void put(Integer id, Meeting m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setID() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
