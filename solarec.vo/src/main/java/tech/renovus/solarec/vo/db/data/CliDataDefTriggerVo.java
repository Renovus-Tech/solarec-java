package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliDataDefTriggerVo;

public class CliDataDefTriggerVo extends DbCliDataDefTriggerVo {

	//--- Public constants ----------------------
	public static final int FLAG_ENABLED		= 0;
	public static final int FLAG_ENABLED_DEV	= 1;
	
	public static final int SOURCE_EMAIL	= 1;
	public static final int SOURCE_MANUAL	= 2;
	public static final int SOURCE_DAILY	= 3;
	public static final int SOURCE_INVERTER	= 4;
	
	public static final String SOURCE_AS_EMAIL	= "email";
	public static final String SOURCE_AS_MANUAL	= "manual";
	
	public static final String SOURCE_AS_NA	= "n/a";
	
	//--- Constructors --------------------------
	public CliDataDefTriggerVo() {
	}

	public CliDataDefTriggerVo(Integer triId) {
		this.setPk(triId);
	}

	//--- Public methods ------------------------
	public String getTriSourceText() {
		if (this.getTriSource() == null) return null;
		
		switch (this.getTriSource().intValue()) {
			case SOURCE_EMAIL: 	return SOURCE_AS_EMAIL;
			case SOURCE_MANUAL:	return SOURCE_AS_MANUAL;
			default:			return SOURCE_AS_NA;
		}
	}

}