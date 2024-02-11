package tech.renovus.solarec.inverters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.inverters.brand.fimer.FimerInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fronius.FroniusInverterServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	FimerInverterServiceTest.class, 
	FroniusInverterServiceTest.class, 
})

public class MainTest {

}
