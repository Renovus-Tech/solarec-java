package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.db.data.dao.interfaces.DocStationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaStatisticDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.StationVo;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceImplTest {

	@Mock private StationDao stationDao;
	@Mock private DocStationDao docStationDao;
	@Mock private StaStatisticDao staStatisticDao;
	@Mock private StaDataDao staDataDao;
	
	@InjectMocks StationServiceImpl service = new StationServiceImpl();
	
	@Test 
	public void test() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		StationVo staVo = new StationVo(null, Integer.valueOf(2));
		staVo.setDataDefId(Integer.valueOf(3));
		StationVo dbStaVo = new StationVo();

		when(this.stationDao.findVo(cliVo.getCliId(), staVo.getStaId())).thenReturn(dbStaVo);
		
		this.service.create(staVo, userData);
		assertEquals(cliVo.getCliId(), staVo.getCliId());
		verify(this.stationDao).insert(staVo);
		
		this.service.update(staVo, userData);
		assertEquals(staVo.getDataDefId(), dbStaVo.getDataDefId());
		verify(this.stationDao).findVo(cliVo.getCliId(), staVo.getStaId());
		verify(this.stationDao).update(any());

		dbStaVo.setPk(staVo);
		this.service.delete(staVo, userData);
		verify(this.docStationDao).deleteAllForStation(dbStaVo.getCliId(), dbStaVo.getStaId());
		verify(this.staStatisticDao).deleteAllForStation(dbStaVo.getCliId(), dbStaVo.getStaId());
		verify(this.staDataDao).deleteAllForStation(dbStaVo.getCliId(), dbStaVo.getStaId());
		verify(this.stationDao).delete(dbStaVo);
	}
	
	@Test 
	public void testFindAll() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		CoreException exception = null;
		
		try {
			this.service.findAll(null, null, null, userData);
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		verify(this.stationDao).findAll(cliVo.getCliId());
	}
	
	@Test 
	public void testFindVo() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		Integer staId = Integer.valueOf(2);
		
		CoreException exception = null;
		
		try {
			this.service.findVo(staId, userData);
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		verify(this.stationDao).findVo(cliVo.getCliId(), staId);
	}
	
	@Test 
	public void testFindAllForLocation() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		Integer locId = Integer.valueOf(3);
		
		CoreException exception = null;
		
		try {
			this.service.findAllForLocation(locId, userData);
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		verify(this.stationDao).findAll(cliVo.getCliId(), locId);
	}
}
