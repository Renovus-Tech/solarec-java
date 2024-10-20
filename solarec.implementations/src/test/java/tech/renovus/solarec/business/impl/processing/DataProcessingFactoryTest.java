package tech.renovus.solarec.business.impl.processing;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import org.junit.Test;

import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

public class DataProcessingFactoryTest {

	public static class TestClass extends AbstractDataProcessing {
		@Override public Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException { return null; }
		@Override public void prepareFor(ClientVo cliVo, InputStream stream) { }
		@Override public void process() throws ProcessingException { }
		@Override public ClientVo generateDataToSave() { return null; }
		@Override public boolean continueWithSatistics() { return false; }
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
