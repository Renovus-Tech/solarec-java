package tech.renovus.solarec.business.impl.calculation.base;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.exceptions.ProcessingException;

public class AbstractDataCalculationTest {

	public static class TestClass extends AbstractDataCalculation {
		@Override public void calculate() throws ProcessingException { }
	}
	
	@Test
	public void testLogs() {
		TestClass test = new TestClass();
		
		test.initLog();
		test.logError("error_code_here");
		test.logInfo("info_code_here");
		test.logWarning("warning_code_here");
		test.endLog();
		
		String log = test.getLog();
		
		assertNotNull(log);
		assertNotEquals(-1, log.indexOf("Start at: "));
		assertTrue(log.indexOf("[ ERROR ]") < log.indexOf("error_code_here"));
		assertTrue(log.indexOf("[ INFO ]") < log.indexOf("info_code_here"));
		assertTrue(log.indexOf("[ WARN ]") < log.indexOf("warning_code_here"));
		assertNotEquals(-1, log.indexOf("End at: "));
	}
}
