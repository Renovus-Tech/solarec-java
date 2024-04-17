package tech.renovus.solarec.inverters.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;

public class InverterServiceTest {
	private static final String ERROR_MESSAGE		= "Test error message";
	private static final String PARENT_MESSAGE		= "Parent message";

	@Test public void testData() {
		Collection<GenDataVo> generatorData = new ArrayList<>();
		Collection<StaDataVo> stationData = new ArrayList<>();
		
		InverterService.InverterData data = new InverterService.InverterData(generatorData, stationData);
		
		assertEquals(generatorData, data.getGeneratorData());
		assertEquals(stationData, data.getStationData());
	}
	
	@Test public void testException() {
        Exception parentException	= new Exception(PARENT_MESSAGE);
        
        InverterService.InveterServiceException exception1 = new InverterService.InveterServiceException();
        InverterService.InveterServiceException exception2 = new InverterService.InveterServiceException(ERROR_MESSAGE);
        InverterService.InveterServiceException exception3 = new InverterService.InveterServiceException(parentException);
        InverterService.InveterServiceException exception4 = new InverterService.InveterServiceException(ERROR_MESSAGE, parentException);
        
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
	
	@Test(expected = InverterService.InveterServiceException.class)
    public void testThrowCustomException1() throws InverterService.InveterServiceException {
        throw new InverterService.InveterServiceException();
    }
	
	@Test(expected = InverterService.InveterServiceException.class)
	public void testThrowCustomException2() throws InverterService.InveterServiceException {
		throw new InverterService.InveterServiceException(ERROR_MESSAGE);
	}
	
	@Test(expected = InverterService.InveterServiceException.class)
	public void testThrowCustomException3() throws InverterService.InveterServiceException {
		throw new InverterService.InveterServiceException(new Exception(PARENT_MESSAGE));
	}
	
	@Test(expected = InverterService.InveterServiceException.class)
	public void testThrowCustomException4() throws InverterService.InveterServiceException {
		throw new InverterService.InveterServiceException(ERROR_MESSAGE, new Exception(PARENT_MESSAGE));
	}


}
