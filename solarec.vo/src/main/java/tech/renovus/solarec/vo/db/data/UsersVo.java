package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.relation.DbUsersVo;

public class UsersVo extends DbUsersVo {
	
	//--- Flags ---------------------------------
	public static final int FLAG_RECEIVE_REPORT_BY_EMAIL			= 0;
	public static final int FLAG_RECEIVE_REPORT_BY_EMAIL_BCC		= 1;
	public static final int FLAG_RECEIVE_ALERT_LOCATION_BY_EMAIL	= 2;
	public static final int FLAG_RECEIVE_ALERT_GENERATOR_NBY_EMAIL	= 3;
	public static final int FLAG_ENABLED							= 4;
	public static final int FLAG_FIRST_LOGIN_DONE					= 5;
	public static final int FLAG_ACCEPTED_TERMS_AND_CONDITIONS		= 6;
	public static final int FLAG_REGISTERED_AT_CERT_PROVIDER		= 7;

	//--- Constructors --------------------------
	public UsersVo() {
	}

	public UsersVo(Integer usrId) {
		this.setPk(usrId);
	}

	//--- Public methods ------------------------
	public UsrSettingVo getSetting(String setName) {
		if (StringUtil.isEmpty(setName) || CollectionUtil.isEmpty(this.getSettings())) {
			return null;
		}
		
		for (UsrSettingVo setVo : this.getSettings()) {
			if (ClassUtil.equals(setVo.getName(), setName)) {
				return setVo;
			}
		}
		
		return null;
	}
}