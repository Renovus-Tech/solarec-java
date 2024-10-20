package tech.renovus.solarec.business.impl.calculation;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculation;
import tech.renovus.solarec.business.impl.processing.DataProcessingFactory;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

public class DataCalculationFactoryTest {

	public static class TestClass extends AbstractDataCalculation {
		@Override public void calculate() throws ProcessingException { }
	}
	
	@Test
	public void test() {
		DataProcessingFactory instance = DataProcessingFactory.getInstance();
		assertNotNull(instance);
		
		DataDefinitionVo vo = new DataDefinitionVo();
		vo.setDataDefExecutable(TestClass.class.getCanonicalName());
		
		//Need to fix classloader locations for inner test class
//		Exception exception = null;
//		try {
//			AbstractDataProcessing result = instance.get(vo);
//			assertNotNull(result);
//			assertTrue(result instanceof TestClass);
//		} catch (CoreException e) {
//			exception = e;
//			e.printStackTrace();
//		}
//		
//		assertNull(exception);
	}
}
