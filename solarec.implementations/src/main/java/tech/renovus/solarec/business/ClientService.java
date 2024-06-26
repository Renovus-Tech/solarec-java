package tech.renovus.solarec.business;

import java.util.Collection;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.rest.entity.Client;

public interface ClientService {

	ClientVo getCurrent(UserData userData);
	void saveToCurrent(Client client, UserData userData);
}
