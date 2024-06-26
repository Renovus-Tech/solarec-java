package tech.renovus.solarec.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ClientService;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.SettingsDao;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.SettingsVo;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.Setting;

@Service
public class ClientServiceImpl implements ClientService {

	//--- Resources -----------------------------
	@Resource ClientDao clientDao;
	@Resource SettingsDao settingsDao;
	@Resource CliSettingDao cliSettingDao;

	//--- Implemented methods -------------------
	@Override
	public ClientVo getCurrent(UserData userData) {
		ClientVo vo = this.clientDao.findVo(userData.getCliId());
		
		if (vo != null) {
			Collection<CliSettingVo> dbSettings = this.cliSettingDao.findAllFor(vo.getCliId());
			dbSettings = dbSettings.stream().filter(x -> FlagUtil.getFlagValue(x.getSettingVo(), SettingsVo.FLAG_VISIBLE)).collect(Collectors.toList());
			vo.setSettings(dbSettings);
		}
		return vo;
	}

	@Override
	public void saveToCurrent(Client client, UserData userData) {
		Collection<CliSettingVo> settings = new ArrayList<>();
		
		if (client != null && CollectionUtil.notEmpty(client.getSettings())) {
			Collection<String> settingsNames = this.settingsDao.getAllNamesForClient();
			for (Setting setting : client.getSettings()) {
				if (! settingsNames.contains(setting.getName())) continue;
				
				CliSettingVo cluSetVo = new CliSettingVo(userData.getCliId(), setting.getName(), setting.getValue());
				cluSetVo.setSyncType(CliSettingVo.SYNC_INSERT);
				settings.add(cluSetVo);
			}
			
			this.cliSettingDao.deleteAllFor(userData.getCliId());
			this.cliSettingDao.synchronize(settings);
		}
	}

}
