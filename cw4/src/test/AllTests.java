package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContactManagerTest.class, ContactManagerTestExport.class,
		ContactTest.class, IdGeneratorTest.class, MeetingTest.class,
		PastMeetingTest.class })
public class AllTests {

}
