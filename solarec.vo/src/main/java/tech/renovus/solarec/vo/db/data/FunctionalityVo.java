package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbFunctionalityVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renvous.solarec.util.FlagUtil;

public class FunctionalityVo extends DbFunctionalityVo implements Comparable<FunctionalityVo> {

	//--- Flags ---------------------------------
	public static final int FLAG_SYSTEM	= 0;
	public static final int FLAG_WIND	= 1;
	public static final int FLAG_SOLAR	= 2;
	
	//--- Constructors --------------------------
	public FunctionalityVo() {
	}

	public FunctionalityVo(Integer fncId) {
		this.setPk(fncId);
	}

	//--- Public methods ------------------------
	public boolean isValidFor(String locType) {
		return 
			FlagUtil.getFlagValue(this, FLAG_SYSTEM) ||
			(ClassUtil.equals(locType, LocationVo.TYPE_SOLAR) && FlagUtil.getFlagValue(this, FLAG_SOLAR)) ||
			(ClassUtil.equals(locType, LocationVo.TYPE_WIND) && FlagUtil.getFlagValue(this, FLAG_WIND));
	}

	@Override
	public int compareTo(FunctionalityVo o) {
		int compare = this.getFncOrder().compareTo(o.getFncOrder());
		if (compare == 0) compare = this.getFncId().compareTo(o.getFncId());
		return compare;
	}

}