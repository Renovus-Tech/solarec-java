package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.db.data.dao.interfaces.DocGeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenNeighbourDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenPowerDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenStatisticDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorServiceImplTest {

	@Mock private GeneratorDao dao;
	@Mock private DocGeneratorDao docGeneratorDao;
	@Mock private GenStatisticDao genStatisticDao;
	@Mock private GenNeighbourDao genNeighbourDao;
	@Mock private GenDataDao genDataDao;
	@Mock private GenPowerDao genPowerDao;
	@Mock private LocationDao locationDao;
	
	@InjectMocks GeneratorServiceImpl service = new GeneratorServiceImpl();
	
	@Test 
	public void test() {
		Integer genId = Integer.valueOf(2);
		Integer locId = Integer.valueOf(3);
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		CoreException exception = null;
		
		GeneratorVo genVo = new GeneratorVo(cliVo.getCliId(), genId);
		genVo.setLocId(locId);
		
		LocationVo locVo = new LocationVo(cliVo.getCliId(), locId);
		
		when(this.dao.findAll(userData.getCliId())).thenReturn(Arrays.asList(genVo));
		when(this.dao.findVo(cliVo.getCliId(), genId)).thenReturn(genVo);
		when(this.locationDao.findAll(cliVo.getCliId())).thenReturn(Arrays.asList(locVo));
		when(this.genNeighbourDao.getAllFor(cliVo.getCliId())).thenReturn(Arrays.asList(new GenNeighbourVo(cliVo.getCliId(), genId, Integer.valueOf(2))));
		
		//findall
		try {
			Collection<GeneratorVo> result = this.service.findAll(null, null, null, userData);
			
			verify(this.dao).findAll(cliVo.getCliId());
			
			assertNotNull(result);
		} catch	(CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//findFullVo
		try {
			GeneratorVo dbGenVo = this.service.findFullVo(genId, userData);
			
			verify(this.dao).findVo(cliVo.getCliId(), genId);
			verify(this.genPowerDao, atLeast(1)).getAllFor(cliVo.getCliId(), genId);
			
			assertNotNull(dbGenVo);
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//findVo
		try {
			GeneratorVo dbGenVo = this.service.findVo(genId, userData);
			
			verify(this.dao, atLeast(1)).findVo(cliVo.getCliId(), genId);
			
			assertNotNull(dbGenVo);
			assertEquals(genId, dbGenVo.getGenId());
		} catch	(CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		//findAllForLocation
		try {
			Collection<GeneratorVo> result = this.service.findAllForLocation(locId, userData);
			
			verify(this.dao, atLeast(1)).findAll(cliVo.getCliId(), locId);
			
			assertNotNull(result);
			assertEquals(0, CollectionUtil.size(result));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		
		//create
		GeneratorVo newGenVo = new GeneratorVo();
		newGenVo = this.service.create(newGenVo, userData);
		
		verify(this.dao).insert(newGenVo);
		
		assertEquals(cliVo.getCliId(), newGenVo.getCliId());
		
		//update
		genVo = this.service.update(genVo, userData);
		
		assertNotNull(genVo);
		
		verify(this.dao, atLeast(1)).findVo(cliVo.getCliId(), genVo.getGenId());
		verify(this.genPowerDao, atLeast(1)).getAllFor(cliVo.getCliId(), genVo.getGenId());
		verify(this.genPowerDao, atLeast(2)).synchronize(genVo.getPowerCurve());
		verify(this.dao).update(genVo);
		
		//delete
		this.service.delete(genVo, userData);
		
		verify(this.dao, atLeast(2)).findVo(cliVo.getCliId(), genVo.getGenId());
		verify(this.docGeneratorDao).deleteAllForGenerator(cliVo.getCliId(), genId);
		verify(this.genStatisticDao).deleteAllForGenerator(cliVo.getCliId(), genId);
		verify(this.genDataDao).deleteAllForGenerator(cliVo.getCliId(), genId);
		verify(this.dao).delete(genVo);

		//getNeighbors
		Collection<LocationVo> result = this.service.getNeighbors(userData);
		
		assertNotNull(result);
		assertEquals(1, CollectionUtil.size(result));
		
		LocationVo aLocVo = result.iterator().next();
		assertNotNull(aLocVo);
		assertNotNull(aLocVo.getGenerators());
		assertEquals(1, CollectionUtil.size(aLocVo.getGenerators()));
		
		GeneratorVo aGenVo = aLocVo.getGenerators().iterator().next();
		assertNotNull(aGenVo);
		assertNotNull(aGenVo.getNeighbours());
		assertEquals(1, CollectionUtil.size(aGenVo.getNeighbours()));
	}
}
