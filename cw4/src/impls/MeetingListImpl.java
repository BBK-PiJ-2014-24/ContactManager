package impls;

// DECOMMISSIONED 

import java.util.HashMap;
import java.util.Map;

import iinterfaces.Meeting;
import iinterfaces.MeetingList;

public abstract class MeetingListImpl implements MeetingList{

	// Fields
	// ------
	Map<Integer, Meeting> meetingMap;
	
	
	// Constructor
	// -----------
	public MeetingListImpl(){
		meetingMap = new HashMap<Integer,Meeting>();
	}
	
	
	public void add(int id, MeetingImpl newMeeting) {
		
		while(id == 0 || meetingMap.containsKey(id)){  // generate an id for the future meeting
			id = IdGenerator.generateID("meetingId");
		}
		newMeeting.setId(id);
		meetingMap.put(id,newMeeting);	
	}

	@Override
	public Meeting getMeeting(int id) {
		for(Integer i : meetingMap.keySet()){
			
		}
		return null;
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
