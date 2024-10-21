package tech.renovus.solarec.business.impl.calculation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculation;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import testing.tech.renovus.solarec.DataCalculationTest;

public class DataCalculationFactoryTest {

	@Test
	public void test() {
		DataCalculationFactory instance = DataCalculationFactory.getInstance();
		assertNotNull(instance);
		
		StatDefinitionVo vo = new StatDefinitionVo();
		vo.setStatDefExecutable(DataCalculationTest.class.getCanonicalName());
		
		Exception exception = null;
		try {
			AbstractDataCalculation result = instance.get(vo);
			assertNotNull(result);
			assertTrue(result instanceof AbstractDataCalculation);
		} catch (CoreException e) {
			exception = e;
			e.printStackTrace();
		}
		
		assertNull(exception);
	}
}
