package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.relation.DbSettingsVo;

public class SettingsVo extends DbSettingsVo {

	//--- Flags ---------------------------------
	public static final int FLAG_FOR_USER	= 0;
	public static final int FLAG_FOR_CLIENT	= 1;
	public static final int FLAG_VISIBLE	= 2;
	
	//--- Public constants ----------------------
	public static final String FISCAL_YEAR_END_MONTH 					= "fiscalYearEndMonth";
	public static final String CETIFICATE_SOLD_PORCENTAGE				= "certSoldPorcentage";
	public static final String CERTIFICATE_PRICE						= "certPrice";
	public static final String PREFER_LANGUAGE							= "language";
	public static final String ALERT_DATA_AVAILABILITY_LOWER_THAN		= "alertDataAvailabilityLowerThan";
	public static final String ALERT_NEGATIVE_CHANGE_EXCEEDING			= "alertNegativeChangeExceeding";
	public static final String ALERT_TIME_BASED_AVAILABILITY_LOWER_THAN	= "alertTimeBasedAvailabilityLowerThan";

	//--- Constructors --------------------------
	public SettingsVo() {
	}

	public SettingsVo(String setName) {
		this.setPk(setName);
	}
	
	//--- Public methods ------------------------
	public double doubleValue() {
		return StringUtil.isEmpty(this.getSetValueDefault()) ? 0 : Double.valueOf(this.getSetValueDefault()).doubleValue();
	}

}