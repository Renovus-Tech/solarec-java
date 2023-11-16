package tech.renovus.solarec.business;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.rest.entity.Client;

public interface ClientService {

	ClientVo getCurrent(UserData userData);
	void setToCurrent(Client client, UserData userData);
}
