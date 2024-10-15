package tech.renovus.solarec.scheduler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AlertNotificationSendTest.class,
	DataGridCheckSchedulerTest.class,
	ReportSchedulerTest.class
})

public class SolarecScheduleTest {

}
