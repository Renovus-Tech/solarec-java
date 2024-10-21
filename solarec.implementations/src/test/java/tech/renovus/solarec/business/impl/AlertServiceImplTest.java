package tech.renovus.solarec.business.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.db.data.dao.interfaces.AlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;

@RunWith(MockitoJUnitRunner.class)
public class AlertServiceImplTest {

	@Mock private ClientDao clientDao;
	@Mock private AlertProcessingDao alertProcessingDao;
	@Mock private LocationDao locationDao;
	@Mock private GeneratorDao generatorDao;
	@Mock private StationDao stationDao;
	@Mock private LocAlertDao locAlertDao;
	@Mock private GenAlertDao genAlertDao;
	@Mock private StaAlertDao staAlertDao;
	
	@InjectMocks AlertServiceImpl service = new AlertServiceImpl();
	
	@Test 
	public void test() {
		DataProcessingVo dataProVo = new DataProcessingVo();
		dataProVo.setCliId(Integer.valueOf(1));
		dataProVo.setLocId(Integer.valueOf(2));
		
		AlertDefinitionVo alertDefVo = new AlertDefinitionVo();
		alertDefVo.setAlertDefId(Integer.valueOf(3));
		alertDefVo.setAlertDefExecutable("testing.tech.renovus.solarec.AlertProcessingTest");
		
		this.service.doProcessing(dataProVo, alertDefVo);
		
		verify(this.clientDao).update(any());
		verify(this.alertProcessingDao).insert(any());
		verify(this.alertProcessingDao).update(any());
		verify(this.locAlertDao).insert(any(Collection.class));
		verify(this.genAlertDao).insert(any(Collection.class));
		verify(this.staAlertDao).insert(any(Collection.class));
	}
}
