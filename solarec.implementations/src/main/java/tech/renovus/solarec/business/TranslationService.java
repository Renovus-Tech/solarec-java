package tech.renovus.solarec.business;

import java.util.Locale;

import tech.renovus.solarec.UserData;

public interface TranslationService {

	Locale getLocale(UserData userData);
	
	String forLabel(Locale locale, String labe, Object[] params);
	String forSetting(Locale locale, String setting);
	String forSettingCategory(Locale locale, String settingCateotgyr);
	String forAlert(Locale locale, String alert, Object[] params);
}
