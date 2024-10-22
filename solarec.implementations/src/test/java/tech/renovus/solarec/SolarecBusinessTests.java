package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.business.ParserServiceImplTest;
import tech.renovus.solarec.business.TranslationServiceTest;
import tech.renovus.solarec.business.impl.AlertServiceImplTest;
import tech.renovus.solarec.business.impl.CalculationServiceImplTest;
import tech.renovus.solarec.business.impl.ClientServiceImplTest;
import tech.renovus.solarec.business.impl.DataDefinitionServiceImplTest;
import tech.renovus.solarec.business.impl.GeneratorServiceImplTest;
import tech.renovus.solarec.business.impl.LocationServiceImplTest;
import tech.renovus.solarec.business.impl.SecurityServiceImplTest;
import tech.renovus.solarec.business.impl.SolarServiceImplTest;
import tech.renovus.solarec.business.impl.StationServiceImplTest;
import tech.renovus.solarec.business.impl.alert.AlertProcessingFactoryTest;
import tech.renovus.solarec.business.impl.alert.base.AbstractAlertProcessingTest;
import tech.renovus.solarec.business.impl.base.BaseServiceImplTest;
import tech.renovus.solarec.business.impl.calculation.DataCalculationFactoryTest;
import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculationTest;
import tech.renovus.solarec.business.impl.chart.ChartFactoryTest;
import tech.renovus.solarec.business.impl.processing.DataProcessingFactoryTest;
import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessingTest;

@RunWith(Suite.class)
@SuiteClasses({
	TranslationServiceTest.class, 
	RestFactoryTest.class, 
	BaseServiceImplTest.class,
	AbstractAlertProcessingTest.class,
	AbstractDataProcessingTest.class,
	AbstractDataCalculationTest.class,
	
	ChartFactoryTest.class,
	AlertProcessingFactoryTest.class,
	DataProcessingFactoryTest.class,
	DataCalculationFactoryTest.class,
	
	AlertServiceImplTest.class,
	ParserServiceImplTest.class,
	CalculationServiceImplTest.class,
	ClientServiceImplTest.class,
	DataDefinitionServiceImplTest.class,
	StationServiceImplTest.class,
	SolarServiceImplTest.class,
	LocationServiceImplTest.class,
	GeneratorServiceImplTest.class,
	SecurityServiceImplTest.class,
	SecurityServiceImplTest.class
})

public class SolarecBusinessTests {

}
