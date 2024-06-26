package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.relation.DbFunctionalityVo;

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
			(ClassUtil.equals(locType, LocationVo.TYPE_SOLAR) && FlagUtil.getFlagValue(this, FLAG_SOLAR));
	}

	@Override
	public int compareTo(FunctionalityVo o) {
		int compare = this.getFncOrder().compareTo(o.getFncOrder());
		if (compare == 0) compare = this.getFncId().compareTo(o.getFncId());
		return compare;
	}

}