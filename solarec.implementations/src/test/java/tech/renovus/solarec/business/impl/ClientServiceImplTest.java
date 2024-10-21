package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.SettingsDao;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.Setting;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

	@Mock private ClientDao clientDao;
	@Mock private SettingsDao settingsDao;
	@Mock private CliSettingDao cliSettingDao;
	
	@InjectMocks ClientServiceImpl service = new ClientServiceImpl();
	
	@Test 
	public void test() {
		ClientVo cliVo = new ClientVo();
		cliVo.setCliId(Integer.valueOf(1));
		
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		
		Collection<CliSettingVo> cliSettingsVo = new ArrayList<>();
		Collection<String> settingsName = Arrays.asList("name1", "name2");
		
		when(this.clientDao.findVo(cliVo.getCliId())).thenReturn(cliVo);
		when(this.cliSettingDao.findAllFor(cliVo.getCliId())).thenReturn(cliSettingsVo);
		when(this.settingsDao.getAllNamesForClient()).thenReturn(settingsName);
		
		ClientVo curCliVo = this.service.getCurrent(userData);
		verify(this.clientDao).findVo(cliVo.getCliId());
		assertEquals(cliVo.getCliId(), curCliVo.getCliId());
		assertEquals(cliSettingsVo, curCliVo.getSettings());
		
		Collection<Setting> settings = new ArrayList<>();
		Setting set1 = new Setting();
		set1.setName("name1");
		set1.setValue("value1");
		Setting set2 = new Setting();
		set2.setName("name2");
		set2.setValue("value2");
		
		settings.add(set1);
		settings.add(set2);
		
		Client client = new Client();
		client.setSettings(settings);
		
		this.service.saveToCurrent(client, userData);
		
		verify(this.cliSettingDao).deleteAllFor(cliVo.getCliId());
		verify(this.cliSettingDao).synchronize(any(Collection.class));
	}
}
