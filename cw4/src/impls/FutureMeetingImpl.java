package impls;

import java.util.Calendar;
import java.util.Set;

import iinterfaces.FutureMeeting;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{

	public FutureMeetingImpl(Set contactSet, Calendar date) {
		super(contactSet, date);
	}

}
