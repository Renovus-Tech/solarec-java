package tech.renovus.solarec.vo.rest.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LocationReportTest {

	@Test public void testLocation() {
		LocationReport location1 = new LocationReport(1, 2);
		LocationReport location2 = new LocationReport(1, 2, true);
		
		assertEquals(0, location1.compareTo(location2));
	}
}
