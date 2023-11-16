package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.relation.DbLocUsrRepTypeVo;

public class LocUsrRepTypeVo extends DbLocUsrRepTypeVo implements Comparable<LocUsrRepTypeVo> {

	//--- Flags ---------------------------------
	public static final int FLAG_SELECTED = 0;
	
	//--- Constructors --------------------------
	public LocUsrRepTypeVo() {
	}

	public LocUsrRepTypeVo(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) {
		this.setPk(cliId, locId, usrId, repTypeId);
	}

	//--- Constructors --------------------------
	public LocUsrRepTypeVo withFlag(int flag, boolean value) {
		FlagUtil.setFlagValue(this, flag, value);
		return this;
	}
	
	//--- Overridden methods --------------------
	@Override public int compareTo(LocUsrRepTypeVo obj) {
		int result = 0;
		if (result == 0) result = this.getCliId().compareTo(obj.getCliId());
		if (result == 0) result = this.getLocId().compareTo(obj.getLocId());
		if (result == 0) result = this.getUsrId().compareTo(obj.getUsrId());
		if (result == 0) result = this.getRepTypeId().compareTo(obj.getRepTypeId());
		
		return result;
	}
}