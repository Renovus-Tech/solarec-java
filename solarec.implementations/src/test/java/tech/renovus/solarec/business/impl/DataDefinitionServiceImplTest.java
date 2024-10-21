package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefTriggerDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

@RunWith(MockitoJUnitRunner.class)
public class DataDefinitionServiceImplTest {

	@Mock private DataDefinitionDao dao;
	@Mock private CliDataDefTriggerDao triggerDao;
	
	@InjectMocks DataDefinitionServiceImpl service = new DataDefinitionServiceImpl();
	
	@Test 
	public void test() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		Collection<DataDefinitionVo> definitions = new ArrayList<>();
		Collection<CliDataDefTriggerVo> triggers = new ArrayList<>();
		
		when(this.dao.findAll()).thenReturn(definitions);
		when(this.triggerDao.findAllFor(any())).thenReturn(triggers);
		
		assertEquals(definitions, this.service.findAll(userData));
		assertEquals(triggers, this.service.findAllTriggers(userData));

		verify(this.dao).findAll();
		verify(this.triggerDao).findAllFor(cliVo.getCliId());
	}
}
