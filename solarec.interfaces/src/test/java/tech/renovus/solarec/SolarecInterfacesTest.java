package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.exceptions.CoreExceptionTest;
import tech.renovus.solarec.exceptions.ProcessingExceptionTest;
import tech.renovus.solarec.exceptions.ReportExceptionTest;
import tech.renovus.solarec.util.BooleanUtilsTest;
import tech.renovus.solarec.util.ClassUtilTest;
import tech.renovus.solarec.util.CollectionUtilTest;
import tech.renovus.solarec.util.DateUtilTest;
import tech.renovus.solarec.util.FileUtilTest;
import tech.renovus.solarec.util.FlagUtilTest;
import tech.renovus.solarec.util.NumberUtilTest;
import tech.renovus.solarec.util.StringUtilTest;
import tech.renovus.solarec.util.db.BaseDBUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
	CoreExceptionTest.class,
	ProcessingExceptionTest.class,
	ReportExceptionTest.class,
	
	BaseDBUtilTest.class,
	
	StringUtilTest.class,
	CollectionUtilTest.class,
	BooleanUtilsTest.class,
	NumberUtilTest.class,
	FlagUtilTest.class,
	FileUtilTest.class,
	ClassUtilTest.class,
	DateUtilTest.class
})

public class SolarecInterfacesTest {

}
