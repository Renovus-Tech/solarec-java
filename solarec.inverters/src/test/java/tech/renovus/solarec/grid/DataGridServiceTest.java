package tech.renovus.solarec.grid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DataGridServiceTest {
	private static final String ERROR_MESSAGE		= "Test error message";
	private static final String PARENT_MESSAGE		= "Parent message";

	@Test public void testException() {
        Exception parentException	= new Exception(PARENT_MESSAGE);
        
        DataGridService.DataGridServiceException exception1 = new DataGridService.DataGridServiceException();
        DataGridService.DataGridServiceException exception2 = new DataGridService.DataGridServiceException(ERROR_MESSAGE);
        DataGridService.DataGridServiceException exception3 = new DataGridService.DataGridServiceException(parentException);
        DataGridService.DataGridServiceException exception4 = new DataGridService.DataGridServiceException(ERROR_MESSAGE, parentException);
        
        // Assert
        assertNull(ERROR_MESSAGE, exception1.getMessage());
        assertEquals(ERROR_MESSAGE, exception2.getMessage());
        assertEquals("java.lang.Exception: " + PARENT_MESSAGE, exception3.getMessage());
        assertEquals(ERROR_MESSAGE, exception4.getMessage());
        assertNull(exception1.getCause());
        assertNull(exception2.getCause());
        assertNotNull(exception3.getCause());
        assertNotNull(exception4.getCause());
        assertEquals(PARENT_MESSAGE, exception3.getCause().getMessage());
        assertEquals(PARENT_MESSAGE, exception4.getCause().getMessage());
	}
	
	@Test(expected = DataGridService.DataGridServiceException.class)
    public void testThrowCustomException1() throws DataGridService.DataGridServiceException {
        throw new DataGridService.DataGridServiceException();
    }
	
	@Test(expected = DataGridService.DataGridServiceException.class)
	public void testThrowCustomException2() throws DataGridService.DataGridServiceException {
		throw new DataGridService.DataGridServiceException(ERROR_MESSAGE);
	}
	
	@Test(expected = DataGridService.DataGridServiceException.class)
	public void testThrowCustomException3() throws DataGridService.DataGridServiceException {
		throw new DataGridService.DataGridServiceException(new Exception(PARENT_MESSAGE));
	}
	
	@Test(expected = DataGridService.DataGridServiceException.class)
	public void testThrowCustomException4() throws DataGridService.DataGridServiceException {
		throw new DataGridService.DataGridServiceException(ERROR_MESSAGE, new Exception(PARENT_MESSAGE));
	}
}
