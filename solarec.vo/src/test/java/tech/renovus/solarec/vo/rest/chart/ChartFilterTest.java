package tech.renovus.solarec.vo.rest.chart;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.BasicVoTester;

public class ChartFilterTest {

	@Test public void testChartFilter() throws Exception {
		ChartFilter filter = new BasicVoTester().testGettersSetters(ChartFilter.class);
		ChartFilter copy = filter.createCopy();
		
        assertEquals(filter.getPeriod(), copy.getPeriod());
        assertEquals(filter.getFrom(), copy.getFrom());
        assertEquals(filter.getTitle(), copy.getTitle());
        assertEquals(filter.getClient(), copy.getClient());
        assertEquals(filter.getLocation(), copy.getLocation());
        assertEquals(filter.getTo(), copy.getTo());
        assertEquals(filter.getGroupBy(), copy.getGroupBy());
        assertEquals(filter.getType(), copy.getType());
        assertEquals(CollectionUtil.size(filter.getGenerators()), CollectionUtil.size(copy.getGenerators())	);
        assertEquals(CollectionUtil.size(filter.getStations()), CollectionUtil.size(copy.getStations())	);
        assertEquals(CollectionUtil.size(filter.getAngles()), CollectionUtil.size(copy.getAngles())	);
	}
}
