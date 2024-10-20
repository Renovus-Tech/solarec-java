package tech.renovus.solarec.business.impl.chart;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class ChartFactoryTest {

	public static class TestClass extends AbstractChart {
		@Override public Object execute() { return null; }
	}
	
	@Test
	public void test() {
		ChartFactory instance = new ChartFactory();
		assertNotNull(instance);
		
		StatDefinitionVo vo = new StatDefinitionVo();
		vo.setStatDefExecutable(TestClass.class.getCanonicalName());
		
		//Need to fix classloader locations for inner test class
//		Exception exception = null;
//		try {
//			AbstractChart result = instance.get(vo);
//			assertNotNull(result);
//			assertTrue(result instanceof TestClass);
//		} catch (CoreException e) {
//			exception = e;
//			e.printStackTrace();
//		}
//		
//		assertNull(exception);
		String theName = "the name";
		String theError = "the error";
		ChartFilter filter = new ChartFilter();
		String result = instance.generateChartResultErrorAsString(theName, theError, filter);
		
		assertTrue(result.contains(theName));
		assertTrue(result.contains(theError));
		assertTrue(result.contains("901"));
	}
}
