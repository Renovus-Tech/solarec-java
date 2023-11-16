package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbRepTypeVo;

public class RepTypeVo extends DbRepTypeVo {

	//--- Flags ---------------------------------
	public static final int FLAG_ENABLED				= 0;
	public static final int FLAG_REQUIRES_DATE			= 1;
	public static final int FLAG_REQUIRES_RANGE			= 2;
	public static final int FLAG_ENABLED_FOR_SETTING	= 3;
	public static final int FLAG_REQUIRES_WEEK			= 4;
	public static final int FLAG_REQUIRES_MONTH			= 5;
	
	//--- Constructors --------------------------
	public RepTypeVo() {
	}

	public RepTypeVo(Integer repTypeId) {
		this.setPk(repTypeId);
	}

}