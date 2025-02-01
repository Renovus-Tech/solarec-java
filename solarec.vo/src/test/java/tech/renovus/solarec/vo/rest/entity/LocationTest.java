package tech.renovus.solarec.vo.rest.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.renovus.solarec.vo.BasicVoTester;

public class LocationTest {

	@Test public void testLocation() throws Exception {
		Location location = new BasicVoTester().testGettersSetters(Location.class);
		
		location.add(new Generator());
		location.add(new Sdg());
		location.add(new Station());
		
		assertEquals(1, location.getGenerators().size());
		assertEquals(1, location.getSdgs().size());
		assertEquals(1, location.getStations().size());
	}
}
