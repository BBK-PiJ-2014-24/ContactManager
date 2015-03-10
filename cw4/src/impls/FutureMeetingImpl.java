// FINAL
package impls;

import java.util.Calendar;
import java.util.Set;

import iinterfaces.FutureMeeting;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{

	public FutureMeetingImpl(int id, Set contactSet, Calendar date) {
		super(id, contactSet, date);
	}

}
