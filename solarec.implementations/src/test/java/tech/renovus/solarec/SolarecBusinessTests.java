package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.business.TranslationServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	TranslationServiceTest.class, 
})

public class SolarecBusinessTests {

}
