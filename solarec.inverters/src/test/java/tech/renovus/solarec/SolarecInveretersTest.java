package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.connection.JsonCallerTest;
import tech.renovus.solarec.grid.electricMaps.ElectricMapsServiceTest;
import tech.renovus.solarec.grid.ember.EmberDataGridServiceTest;
import tech.renovus.solarec.inverters.brand.aiswei.AisweiInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fimer.FimerInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fronius.FroniusInverterServiceTest;
import tech.renovus.solarec.inverters.brand.sma.SmaInverterServiceTest;
import tech.renovus.solarec.inverters.brand.sofar.SofarInverterServiceTest;
import tech.renovus.solarec.inverters.brand.solarEdge.SolarEdgeInverterServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	AisweiInverterServiceTest.class,
	FimerInverterServiceTest.class, 
	FroniusInverterServiceTest.class,
	SmaInverterServiceTest.class,
	SofarInverterServiceTest.class,
	SolarEdgeInverterServiceTest.class, 
	
	JsonCallerTest.class,
	
	ElectricMapsServiceTest.class,
	EmberDataGridServiceTest.class,
})

public class SolarecInveretersTest {

}
