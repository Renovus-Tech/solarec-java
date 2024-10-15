package tech.renovus.solarec.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProcessingExceptionTest {

	@Test  public void testConstructors() {
		ProcessingException textException = new ProcessingException("First processing exception");
		ProcessingException ProcessingException = new ProcessingException(textException);
		ProcessingException exceptionException = new ProcessingException(new Exception("First exception"));
		
		assertEquals("First processing exception", textException.getMessage());
		assertEquals("tech.renovus.solarec.exceptions.ProcessingException: First processing exception", ProcessingException.getMessage());
		assertEquals("java.lang.Exception: First exception", exceptionException.getMessage());
	}
}
