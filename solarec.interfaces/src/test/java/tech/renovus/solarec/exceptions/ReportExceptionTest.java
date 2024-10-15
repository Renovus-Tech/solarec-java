package tech.renovus.solarec.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReportExceptionTest {

	@Test  public void testConstructors() {
		ReportException textException = new ReportException("First report exception");
		ReportException ReportException = new ReportException(textException);
		ReportException exceptionException = new ReportException(new Exception("First exception"));
		
		assertEquals("First report exception", textException.getMessage());
		assertEquals("tech.renovus.solarec.exceptions.ReportException: First report exception", ReportException.getMessage());
		assertEquals("java.lang.Exception: First exception", exceptionException.getMessage());
	}
}
