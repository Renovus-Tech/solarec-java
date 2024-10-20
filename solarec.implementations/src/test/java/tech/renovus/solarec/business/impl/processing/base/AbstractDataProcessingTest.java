package tech.renovus.solarec.business.impl.processing.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing.DateRange;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class AbstractDataProcessingTest {

	public static class TestClass extends AbstractDataProcessing {
		@Override public Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException { return null; }
		@Override public void prepareFor(ClientVo cliVo, InputStream stream) {}
		@Override public void process() throws ProcessingException { }
		@Override public ClientVo generateDataToSave() { return null; }
		@Override public boolean continueWithSatistics() { return false; }
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
		assertNotEquals(-1, log.indexOf("Ended at: "));
	}
	
	@Test
	public void testRange() {
		DateRange range = new DateRange();
		
		Date first = new Date();
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date middle = calendar.getTime();
		
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date last = calendar.getTime();
		
		range.set(last);
		range.set(middle);
		range.set(first);
		
		assertEquals(first, range.getDateFrom());
		assertEquals(last, range.getDateTo());
	}

}
