package tech.renovus.solarec.inverters.brand;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.Test;

import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public abstract class BaseInveterTest {

	//--- Util methods --------------------------
	public ClientVo buildClientWith(Collection<CliDataDefParameterVo> cliParameters, Collection<LocDataDefParameterVo> locParameters, Collection<GenDataDefParameterVo> genParameters) {
		ClientVo client = new ClientVo();
		client.setDataDefParameters(cliParameters);
		
		LocationVo locVo = new LocationVo();
		locVo.setDataDefParameters(locParameters);
		
		GeneratorVo genVo = new GeneratorVo();
		genVo.setDataDefParameters(genParameters);
		
		StationVo staVo = new StationVo();

		client.add(locVo);
		locVo.add(genVo);
		locVo.add(staVo);

		return client;
	}
	
	public CliDataDefParameterVo createClientParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		CliDataDefParameterVo result = new CliDataDefParameterVo();
		result.setCliDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	public LocDataDefParameterVo createLocationParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		LocDataDefParameterVo result = new LocDataDefParameterVo();
		result.setLocDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	public GenDataDefParameterVo createGeneratorParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		GenDataDefParameterVo result = new GenDataDefParameterVo();
		result.setGenDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	//--- Abstract methods ----------------------
	public abstract InverterService getService();
	public abstract ClientVo createClient();
	public abstract void prepareMock() throws InveterServiceException;
	public abstract void postDataRetrieveTest();
	
	//--- Test methods --------------------------
	@Test
	public void testDataRetrieve() {
		ClientVo client					= this.createClient();
		InverterService service			= this.getService();
		InveterServiceException error	= null;
		
		try {
			this.prepareMock();
			
			service.prepareFor(client);
			
			assertTrue(service.canRetrieve());
			
			InverterData apiData = service.retrieveData();
			
			assertNotNull(apiData);
			assertNotNull(apiData.getGeneratorData());
			assertNotNull(apiData.getStationData());
			assertTrue(CollectionUtil.notEmpty(apiData.getGeneratorData()));
//			assertTrue(CollectionUtil.notEmpty(apiData.getStationData()));
			
			this.postDataRetrieveTest();
		} catch (InveterServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
