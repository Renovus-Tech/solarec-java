package tech.renovus.solarec.business.impl.alert;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tech.renovus.solarec.business.impl.alert.base.AbstractAlertProcessing;
import tech.renovus.solarec.business.impl.processing.DataProcessingFactory;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;

public class AlertProcessingFactoryTest {

	public static class TestClass extends AbstractAlertProcessing {
		@Override public void prepareFor(DataProcessingVo dataProVo) { }
		@Override public void process() throws ProcessingException { }
		@Override public ClientVo generateAlertToSave() { return null; }
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
