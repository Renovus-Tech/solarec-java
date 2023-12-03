package tech.renovus.solarec.business.impl;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ParserService;

@Service
public class ParserServiceImpl implements ParserService {

	//--- Overridden methods --------------------
	@Override public String parseAlert(String json, UserData userData) {
		return "Sample messages, needs to implement codes. Require information from python code to read JSON";
	}

}
