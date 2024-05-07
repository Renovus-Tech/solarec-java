package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.exceptions.CoreExceptionTest;
import tech.renovus.solarec.util.StringUtilTest;

@RunWith(Suite.class)
@SuiteClasses({
	CoreExceptionTest.class,
	StringUtilTest.class, 
})

public class SolarecInterfacesTest {

}
