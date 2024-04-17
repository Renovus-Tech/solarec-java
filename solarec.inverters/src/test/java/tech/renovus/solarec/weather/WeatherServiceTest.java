package tech.renovus.solarec.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class WeatherServiceTest {
	private static final String ERROR_MESSAGE		= "Test error message";
	private static final String PARENT_MESSAGE		= "Parent message";

	@Test public void testFormatters() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 1979);
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		cal.set(Calendar.MINUTE, 18);
		cal.set(Calendar.SECOND, 20);
		cal.set(Calendar.MILLISECOND, 100);
		
		Date aDate = cal.getTime();
		
		assertTrue(WeatherService.ISO_FORMATTER.format(aDate).startsWith("1979-11-03T08:18:20 GMT"));
		assertEquals("1979-11-03 08:18", WeatherService.DATE_HOUR_FORMATTER.format(aDate));
		assertEquals("1979-11-03", WeatherService.DATE_FORMATTER.format(aDate));
	}
	
	@Test public void testException() {
        Exception parentException	= new Exception(PARENT_MESSAGE);
        
        WeatherService.WeatherServiceException exception1 = new WeatherService.WeatherServiceException();
        WeatherService.WeatherServiceException exception2 = new WeatherService.WeatherServiceException(ERROR_MESSAGE);
        WeatherService.WeatherServiceException exception3 = new WeatherService.WeatherServiceException(parentException);
        WeatherService.WeatherServiceException exception4 = new WeatherService.WeatherServiceException(ERROR_MESSAGE, parentException);
        
        // Assert
        assertNull(ERROR_MESSAGE, exception1.getMessage());
        assertEquals(ERROR_MESSAGE, exception2.getMessage());
        assertEquals("java.lang.Exception: " + PARENT_MESSAGE, exception3.getMessage());
        assertEquals(ERROR_MESSAGE, exception4.getMessage());
        assertNull(exception1.getCause());
        assertNull(exception2.getCause());
        assertNotNull(exception3.getCause());
        assertNotNull(exception4.getCause());
        assertEquals(PARENT_MESSAGE, exception3.getCause().getMessage());
        assertEquals(PARENT_MESSAGE, exception4.getCause().getMessage());
	}
	
	@Test(expected = WeatherService.WeatherServiceException.class)
    public void testThrowCustomException1() throws WeatherService.WeatherServiceException {
        throw new WeatherService.WeatherServiceException();
    }
	
	@Test(expected = WeatherService.WeatherServiceException.class)
	public void testThrowCustomException2() throws WeatherService.WeatherServiceException {
		throw new WeatherService.WeatherServiceException(ERROR_MESSAGE);
	}
	
	@Test(expected = WeatherService.WeatherServiceException.class)
	public void testThrowCustomException3() throws WeatherService.WeatherServiceException {
		throw new WeatherService.WeatherServiceException(new Exception(PARENT_MESSAGE));
	}
	
	@Test(expected = WeatherService.WeatherServiceException.class)
	public void testThrowCustomException4() throws WeatherService.WeatherServiceException {
		throw new WeatherService.WeatherServiceException(ERROR_MESSAGE, new Exception(PARENT_MESSAGE));
	}
}
