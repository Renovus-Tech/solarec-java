package tech.renovus.solarec.business;

import tech.renovus.solarec.UserData;

public interface ParserService {

	String parseAlert(String json, UserData userData);
}
