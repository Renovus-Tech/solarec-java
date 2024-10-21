package tech.renovus.solarec.business.impl.alert;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.business.impl.processing.DataProcessingFactory;
import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import testing.tech.renovus.solarec.DataProcessingTest;

public class AlertProcessingFactoryTest {

	@Test
	public void test() {
		DataProcessingFactory instance = DataProcessingFactory.getInstance();
		assertNotNull(instance);
		
		DataDefinitionVo vo = new DataDefinitionVo();
		vo.setDataDefExecutable(DataProcessingTest.class.getCanonicalName());
		
		Exception exception = null;
		try {
			AbstractDataProcessing result = instance.get(vo);
			assertNotNull(result);
			assertTrue(result instanceof DataProcessingTest);
		} catch (CoreException e) {
			exception = e;
			e.printStackTrace();
		}
		
		assertNull(exception);
	}
}
