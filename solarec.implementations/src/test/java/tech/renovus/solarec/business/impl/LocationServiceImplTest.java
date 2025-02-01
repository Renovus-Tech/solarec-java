package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.FrequencyDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocEstimationDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocEstimationVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.rest.entity.Location;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
	
	@Mock private LocationDao dao;
	@Mock private GeneratorDao generatorsDao;
	@Mock private StationDao stationsDao;
	@Mock private DataDefinitionDao dataDefinitionDao;
	@Mock private LocEstimationDao locEstimationDao;
	@Mock private CountryDao countryDao;
	@Mock private FrequencyDao frequencyDao;
	
	@InjectMocks LocationServiceImpl service = new LocationServiceImpl();
	
	@Test 
	public void test() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UsersVo usrVo = new UsersVo(Integer.valueOf(10));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		userData.setUserVo(usrVo);
		
		Integer locId = Integer.valueOf(2);
		CoreException exception = null;
		
		LocationVo locVo = new LocationVo(cliVo.getCliId(), locId);
		locVo.setCtrId(Integer.valueOf(3));
		locVo.setDataDefId(Integer.valueOf(4));
		
		Location location = new Location();
		location.setId(locId);
		
		when(this.dao.findVo(cliVo.getCliId(), locId)).thenReturn(locVo);
		when(this.dataDefinitionDao.findVo(locVo.getDataDefId())).thenReturn(new DataDefinitionVo());
		when(this.generatorsDao.findAll(cliVo.getCliId(), locId)).thenReturn(Arrays.asList(new GeneratorVo()));
		when(this.stationsDao.findAll(cliVo.getCliId(), locId)).thenReturn(Arrays.asList(new StationVo()));
		when(this.locEstimationDao.findAll(cliVo.getCliId(), locId)).thenReturn(Arrays.asList(new LocEstimationVo()));
		when(this.countryDao.findVo(locVo.getCtrId())).thenReturn(new CountryVo());
		when(this.dao.findAllForUser(cliVo.getCliId(), usrVo.getUsrId(), false)).thenReturn(Arrays.asList(locVo));
		when(this.locEstimationDao.findAll(cliVo.getCliId(), locId)).thenReturn(new ArrayList<>());
		when(this.frequencyDao.findVo(any())).thenReturn(null);
		
		//findall
		try {
			this.service.findAll(null, null, null, userData);
			
			verify(this.dao).findAll(cliVo.getCliId());
		} catch	(CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//findVo
		try {
			LocationVo dbLocVo = this.service.findVo(locId, userData);
			
			verify(this.dao).findVo(cliVo.getCliId(), locId);
			
			assertNotNull(dbLocVo);
			assertEquals(locVo.getCliId(), dbLocVo.getCliId());
			assertEquals(locVo.getLocId(), dbLocVo.getLocId());
		} catch	(CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//findFullVo
		try {
			LocationVo dbLocVo = this.service.findFullVo(locId, userData);
			
			verify(this.dao, atLeast(1)).findVo(cliVo.getCliId(), locId);
			verify(this.dataDefinitionDao, atLeast(1)).findVo(locVo.getDataDefId());
			verify(this.generatorsDao, atLeast(1)).findAll(cliVo.getCliId(), locId);
			verify(this.stationsDao, atLeast(1)).findAll(cliVo.getCliId(), locId);
			verify(this.locEstimationDao, atLeast(1)).findAll(cliVo.getCliId(), locId);
			verify(this.countryDao, atLeast(1)).findVo(locVo.getCtrId());
			
			assertNotNull(dbLocVo);
			assertNotNull(dbLocVo.getDataDefinitionVo());
			assertNotNull(dbLocVo.getGenerators());
			assertNotNull(dbLocVo.getStations());
			assertNull(dbLocVo.getEstimations());
			assertNotNull(dbLocVo.getCountryVo());
		} catch	(CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//create
		LocationVo newLocVo = new LocationVo();
		this.service.create(newLocVo, userData);
		
		verify(this.dao).insert(newLocVo);
		
		assertEquals(userData.getCliId(), newLocVo.getCliId());
		
		//update
		locVo.setDataDefId(Integer.valueOf(4));
		newLocVo = new LocationVo(locVo.getCliId(), locVo.getLocId());
		this.service.update(newLocVo, userData);
		
		verify(this.dao, atLeast(1)).update(locVo);
		
		//delete
		this.service.delete(locVo, userData);
		
		verify(this.dao, atLeast(1)).findVo(locVo.getCliId(), locVo.getLocId());
		verify(this.dao, atLeast(1)).delete(locVo);
		
		//getEstimations
		locVo.setEstimations(null);
		Collection<LocationVo> estimations = this.service.getEstimations(userData);
		
		verify(this.dao, atLeast(1)).findAllForUser(cliVo.getCliId(), usrVo.getUsrId(), false);
		verify(this.locEstimationDao, atLeast(1)).findAll(cliVo.getCliId(), locId);
		
		assertNotNull(estimations);
		assertEquals(1, CollectionUtil.size(estimations));
		assertNotNull(locVo.getEstimations());
		
		//setEstimations
		this.service.setEstimations(Arrays.asList(location), userData);
		
		verify(this.dao, atLeast(1)).findAllForUser(cliVo.getCliId(), usrVo.getUsrId(), false);
		verify(this.locEstimationDao, atLeast(1)).deleteAllFor(cliVo.getCliId(), locId);
		verify(this.locEstimationDao, atLeast(1)).synchronize(locVo.getEstimations());
	}
}
