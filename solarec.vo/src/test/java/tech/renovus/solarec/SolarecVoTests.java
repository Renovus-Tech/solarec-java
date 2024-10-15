package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.vo.BasicVoTester;
import tech.renovus.solarec.vo.comparator.ComparatorTests;
import tech.renovus.solarec.vo.db.data.LocationVoTest;

@RunWith(Suite.class)
@SuiteClasses({
	ComparatorTests.class, 
	BasicVoTester.class,
	
	LocationVoTest.class
})

public class SolarecVoTests {

}
