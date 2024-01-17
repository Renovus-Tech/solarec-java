package tech.renovus.solarec.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ClientService;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.Setting;

@Service
public class ClientServiceImpl implements ClientService {

	//--- Resources -----------------------------
	@Resource ClientDao clientDao;
	@Resource CliSettingDao cliSettingDao;

	//--- Implemented methods -------------------
	@Override
	public ClientVo getCurrent(UserData userData) {
		ClientVo vo = this.clientDao.findVo(userData.getCliId());
		
		if (vo != null) {
			Collection<CliSettingVo> dbSettings = this.cliSettingDao.findAllFor(vo.getCliId());
			vo.setSettings(this.populateCliSettings(vo, dbSettings));
			
		}
		return vo;
	}

	@Override
	public Collection<CliSettingVo> populateCliSettings(ClientVo vo, Collection<CliSettingVo> dbSettings) {
		Map<String, CliSettingVo> allSettings = new HashMap<>();
		if (CollectionUtil.notEmpty(dbSettings)) {
			for (CliSettingVo setVo : dbSettings) {
				allSettings.put(setVo.getCliSetName(), setVo);
			}
		}
		
		allSettings.computeIfAbsent(CliSettingVo.FISCAL_YEAR_END_MONTH 						, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_FISCAL_YEAR_END_MONTH)); 
		allSettings.computeIfAbsent(CliSettingVo.D_RECS_SOLD_PORCENTAGE						, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_D_RECS_SOLD_PORCENTAGE)); 
		allSettings.computeIfAbsent(CliSettingVo.D_RECS_PRICE								, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_D_RECS_PRICE)); 
		allSettings.computeIfAbsent(CliSettingVo.PREFER_LANGUAGE							, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_PREFER_LANGUAGE)); 
		allSettings.computeIfAbsent(CliSettingVo.ALERT_DATA_AVAILABILITY_LOWER_THAN			, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_ALERT_DATA_AVAILABILITY_LOWER_THAN)); 
		allSettings.computeIfAbsent(CliSettingVo.ALERT_NEGATIVE_CHANGE_EXCEEDING			, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_ALERT_NEGATIVE_CHANGE_EXCEEDING)); 
		allSettings.computeIfAbsent(CliSettingVo.ALERT_TIME_BASED_AVAILABILITY_LOWER_THAN	, x -> new CliSettingVo(vo.getCliId(), x, CliSettingVo.DEFAULT_VALUE_ALERT_TIME_BASED_AVAILABILITY_LOWER_THAN));
		
		return allSettings.values();
	}

	@Override
	public void setToCurrent(Client client, UserData userData) {
		Collection<CliSettingVo> settings = new ArrayList<>();
		
		if (client != null && CollectionUtil.notEmpty(client.getSettings())) {
			for (Setting setting : client.getSettings()) {
				if (! CliSettingVo.validName(setting.getName())) continue;
				CliSettingVo cluSetVo = new CliSettingVo(userData.getCliId(), setting.getName(), setting.getValue());
				cluSetVo.setSyncType(CliSettingVo.SYNC_INSERT);
				settings.add(cluSetVo);
			}
			
			this.cliSettingDao.deleteAllFor(userData.getCliId());
			this.cliSettingDao.synchronize(settings);
		}
	}

}
