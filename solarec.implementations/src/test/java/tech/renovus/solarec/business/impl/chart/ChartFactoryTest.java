package tech.renovus.solarec.business.impl.chart;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;
import testing.tech.renovus.solarec.ChartTest;

public class ChartFactoryTest {

	@Test
	public void test() {
		ChartFactory instance = new ChartFactory();
		assertNotNull(instance);
		
		StatDefinitionVo vo = new StatDefinitionVo();
		vo.setStatDefExecutable(ChartTest.class.getCanonicalName());
		
		Exception exception = null;
		try {
			AbstractChart result = instance.get(vo);
			assertNotNull(result);
			assertTrue(result instanceof ChartTest);
		} catch (CoreException e) {
			exception = e;
			e.printStackTrace();
		}
		
		assertNull(exception);
		String theName = "the name";
		String theError = "the error";
		ChartFilter filter = new ChartFilter();
		String result = instance.generateChartResultErrorAsString(theName, theError, filter);
		
		assertTrue(result.contains(theName));
		assertTrue(result.contains(theError));
		assertTrue(result.contains("901"));
	}
}
