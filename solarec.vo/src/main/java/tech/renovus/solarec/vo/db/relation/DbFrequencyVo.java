package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseFrequencyVo;

public class DbFrequencyVo extends BaseFrequencyVo implements ISynchronizable<DbFrequencyVo> {
	
	//--- Flags ---------------------------------
	public static final int FLAG_PERIOD_YESTERDAY	= 0;
	public static final int FLAG_PERIOD_MONTH		= 1;
	public static final int FLAG_PERIOD_YEAR		= 2;
	public static final int FLAG_PERIOD_RANGE		= 3;
	public static final int FLAG_GROUP_BY_DAY		= 4;
	public static final int FLAG_GROUP_BY_WEEK		= 5;
	public static final int FLAG_GROUP_BY_MONTH		= 6;
	public static final int FLAG_GROUP_BY_YEAR		= 7;

	public static final String UNIT_MINUTES		= "m";
	public static final String UNIT_HOUR		= "h";
	public static final String UNIT_DAY			= "d";
	public static final String UNIT_WEEK		= "w";
	public static final String UNIT_MONTH		= "t";
	public static final String UNIT_YEAR		= "y";
			
	
	//--- Constructors --------------------------
	public DbFrequencyVo() {
	}

	public DbFrequencyVo(Integer frqId) {
		this.setPk(frqId);
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
	}

	@Override public void synchronize(DbFrequencyVo dbVo) {
	}

	@Override public void synchronizeForce(int syncType) {
	}

}