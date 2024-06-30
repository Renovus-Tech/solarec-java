package tech.renovus.solarec.business.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.CustomFlowInterface;
import tech.renovus.solarec.vo.rest.security.Authentication;

@Service
@ConditionalOnProperty(name = "solarec.service.custom_flow", havingValue = "")
public class DefaultCustomFlow implements CustomFlowInterface {

	@Override
	public boolean afterAuthentication(Authentication authentication, UserData userData) {
		return true;
	}

	@Override
	public void beforeSendingToHomepage(UserData userData) {
	}

	@Override
	public void beforeLogOut(UserData userData) {
	}

}
