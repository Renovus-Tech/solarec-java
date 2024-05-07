package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.api.rest.controller.RestFactoryTest;

@RunWith(Suite.class)
@SuiteClasses({
	RestFactoryTest.class, 
})

public class CoreMaintTest {

}
