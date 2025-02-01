package tech.renovus.solarec.vo.rest.chart;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PeriodTest {

	@Test public void testPeriod() {
		int startYear = 2018;
		int startMonth = 1;
		int endYear = 2019;
		int endMonth = 12;
		
		Period period = new Period(startYear, startMonth, endYear, endMonth);
		assertEquals(startYear + "-" + startMonth + " --> " + endYear + "-" + endMonth, period.toString());
		
		Period copy = period.createCopy();
		
		assertEquals(period.getStartMonth(), copy.getStartMonth());
		assertEquals(period.getStartYear(), copy.getStartYear());
		assertEquals(period.getEndMonth(), copy.getEndMonth());
		assertEquals(period.getEndYear(), copy.getEndYear());
	}
}
