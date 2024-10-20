package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.connection.JsonCallerTest;
import tech.renovus.solarec.grid.DataGridServiceTest;
import tech.renovus.solarec.grid.electricMaps.ElectricMapsServiceTest;
import tech.renovus.solarec.grid.ember.EmberDataGridServiceTest;
import tech.renovus.solarec.inverters.brand.aiswei.AisweiInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fimer.FimerInverterServiceTest;
import tech.renovus.solarec.inverters.brand.fronius.FroniusInverterServiceTest;
import tech.renovus.solarec.inverters.brand.sma.SmaInverterServiceTest;
import tech.renovus.solarec.inverters.brand.sofar.SofarInverterServiceTest;
import tech.renovus.solarec.inverters.brand.solarEdge.SolarEdgeInverterServiceTest;
import tech.renovus.solarec.inverters.common.InverterServiceTest;
import tech.renovus.solarec.inverters.common.InvertersUtilTest;
import tech.renovus.solarec.weather.WeatherServiceTest;
import tech.renovus.solarec.weather.meteoblue.MeteoblueWeatherServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({
	AisweiInverterServiceTest.class,
	FimerInverterServiceTest.class, 
	FroniusInverterServiceTest.class,
	SmaInverterServiceTest.class,
	SofarInverterServiceTest.class,
	SolarEdgeInverterServiceTest.class,
	
	InverterServiceTest.class,
	InvertersUtilTest.class,
	
	JsonCallerTest.class,
	
	ElectricMapsServiceTest.class,
	EmberDataGridServiceTest.class,
	DataGridServiceTest.class,
	
	MeteoblueWeatherServiceImplTest.class,
	WeatherServiceTest.class,
})

public class SolarecInveretersTest {

}
