package tech.renovus.solarec.scheduler;

import java.util.Arrays;
import java.util.Collection;

import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;

public class InverterServiceTest implements InverterService {

	public static boolean calledPrepareFor = false;
	public static boolean calledCanRetrieve = false;
	public static boolean calledContinueWithStats = false;
	public static boolean calledGetReasonWhyCantRetrieve = false;
	public static boolean calledRetrieveData = false;
	
	@Override
	public void prepareFor(ClientVo client) {
		InverterServiceTest.calledPrepareFor = true;
	}

	@Override
	public boolean canRetrieve() {
		InverterServiceTest.calledCanRetrieve = true;
		return true;
	}

	@Override
	public boolean continueWithStats() {
		InverterServiceTest.calledContinueWithStats = true;
		return false;
	}

	@Override
	public String getReasonWhyCantRetrieve() {
		InverterServiceTest.calledGetReasonWhyCantRetrieve = true;
		return null;
	}

	@Override
	public InverterData retrieveData() throws InveterServiceException {
		InverterServiceTest.calledRetrieveData = true;
		
		GenDataVo genDataVo = new GenDataVo();
		StaDataVo staDataVo = new StaDataVo();
		CliLocAlertVo locAlertVo = new CliLocAlertVo();
		CliGenAlertVo genAlertVo = new CliGenAlertVo();
		
		Collection<GenDataVo> genData = Arrays.asList(genDataVo);
		Collection<StaDataVo> staData = Arrays.asList(staDataVo);
		Collection<CliLocAlertVo> locAlerts = Arrays.asList(locAlertVo);
		Collection<CliGenAlertVo> getAlerts = Arrays.asList(genAlertVo);
		
		InverterData newData = new InverterService.InverterData(genData, staData, locAlerts, getAlerts);
		
		return newData;
	}

}
