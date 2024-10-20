package tech.renovus.solarec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tech.renovus.solarec.business.TranslationServiceTest;
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
	DataCalculationFactoryTest.class
})

public class SolarecBusinessTests {

}
