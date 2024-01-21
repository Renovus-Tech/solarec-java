package tech.renovus.solarec.business.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.TranslationService;

public class TranslationServiceImpl implements TranslationService {

	//--- Private constants ---------------------
	private static final String PREFIX_SETTING			= "setting.";
	private static final String PREFIX_SETTING_CATEGORY	= "setting.category.";
	
	//--- Private properties --------------------
	@Autowired private MessageSource messageSource;
	
	//--- Implemented methods -------------------
	@Override public Locale getLocale(UserData userData) {
		return userData == null || userData.getLocale() == null ? Locale.ENGLISH : null;
	}
	
	@Override public String forLabel(Locale locale, String labe, Object[] params)		{ return this.messageSource.getMessage(labe, params, locale); }
	@Override public String forSetting(Locale locale, String setting)					{ return this.forLabel(locale, PREFIX_SETTING + setting, null); }
	@Override public String forSettingCategory(Locale locale, String settingCategory)	{ return this.forLabel(locale, PREFIX_SETTING_CATEGORY + settingCategory, null); }

	@Override
	public String forAlert(Locale locale, String alert, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
