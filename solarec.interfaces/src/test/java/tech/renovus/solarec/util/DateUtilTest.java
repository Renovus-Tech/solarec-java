package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateUtilTest {

	@Test public void test() {
		assertTrue(Math.abs(DateUtil.getCurrent().getTime() - System.currentTimeMillis()) < 100);
		
		Calendar calExpected = GregorianCalendar.getInstance();
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(DateUtil.getCurrentDay());
		
		assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, cal.get(Calendar.MINUTE));
		assertEquals(0, cal.get(Calendar.SECOND));
		assertEquals(0, cal.get(Calendar.MILLISECOND));
		
		cal.setTime(DateUtil.getYestardayDay());
		calExpected.add(Calendar.DAY_OF_MONTH, -1);
		assertEquals(calExpected.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
		
		cal.setTime(DateUtil.getCurrentMonthStart());
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		
		cal.setTime(DateUtil.getCurrentMonthEnd());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		
		assertEquals(calExpected.get(Calendar.YEAR), DateUtil.getCurrentYear());
	}
}
