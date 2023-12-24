package tech.renovus.solarec.inverters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.inverters.branch.fimer.FimerInverterServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	FimerInverterServiceTest.class, 
})

public class MainTest {

}
