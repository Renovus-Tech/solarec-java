package tech.renovus.solarec.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class CoreExceptionTest {

	@Test  public void testConstructors() {
		CoreException textException = new CoreException("First core exception");
		CoreException paramException = new CoreException("The message", Arrays.asList("Param 1", "Param 2"));
		CoreException param2Exception = new CoreException("The message", "Param 1", "Param 2");
		CoreException coreException = new CoreException(textException);
		CoreException exceptionException = new CoreException(new Exception("First exception"));
		CoreException throwableException = new CoreException(new Throwable("First throwable"));
		
		assertTrue(textException.isLabel());
		assertTrue(coreException.isLabel());
		assertTrue(paramException.isLabel());
		assertTrue(param2Exception.isLabel());
		assertFalse(exceptionException.isLabel);
		assertFalse(throwableException.isLabel());

		assertNull(textException.getParams());
		assertNull(coreException.getParams());
		assertNotNull(paramException.getParams());
		assertNotNull(param2Exception.getParams());
		assertNull(exceptionException.getParams());
		assertNull(throwableException.getParams());
		
		assertEquals("First core exception", textException.getMessage());
		assertEquals("tech.renovus.solarec.exceptions.CoreException: First core exception", coreException.getMessage());
		assertEquals("The message", paramException.getMessage());
		assertEquals("The message", param2Exception.getMessage());
		assertEquals("java.lang.Exception: First exception", exceptionException.getMessage());
		assertEquals("java.lang.Throwable: First throwable", throwableException.getMessage());

	}
}
