package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbWeaDefinitionVo;

public class WeaDefinitionVo extends DbWeaDefinitionVo {

	//--- Flags ---------------------------------
	public static final int FLAG_UPDATE_ON_BACKGROUND	= 0;
	
	//--- Public constants ----------------------
	public static final int TYPE_LIGHTNING	= 1;
	public static final int TYPE_FORECAST	= 2;
	
	//--- Constructors --------------------------
	public WeaDefinitionVo() {
	}

	public WeaDefinitionVo(Integer cliId, Integer weaId) {
		this.setPk(cliId, weaId);
	}

	//--- Public methods ------------------------
	public String getType() {
		if (this.getWeaCheckType() == null) return null;
		switch (this.getWeaCheckType().intValue()) {
			case TYPE_LIGHTNING:	return "lighting";
			case TYPE_FORECAST:		return "forecast";
		}
		return null;
	}
}