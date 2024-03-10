package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.connection.JsonCallerTest;
import tech.renovus.solarec.inverters.brand.fimer.FimerInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fronius.FroniusInverterServiceTest;
import tech.renovus.solarec.inverters.brand.sma.SmaInverterServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	FimerInverterServiceTest.class, 
	FroniusInverterServiceTest.class,
	SmaInverterServiceTest.class, 
	JsonCallerTest.class
})

public class MainTest {

}
