package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbWeaCallVo;

public class WeaCallVo extends DbWeaCallVo {

	//--- Private properties --------------------
	private WeaDefinitionVo weatherDefinitionVo;
	
	//--- Constructors --------------------------
	public WeaCallVo() {
	}

	public WeaCallVo(Integer cliId, Integer weaId, Integer weaCallId) {
		this.setPk(cliId, weaId, weaCallId);
	}

	//--- Private methods -----------------------
	public WeaDefinitionVo getWeatherDefinitionVo() {
		return weatherDefinitionVo;
	}
	public void setWeatherDefinitionVo(WeaDefinitionVo weatherDefinitionVo) {
		this.weatherDefinitionVo = weatherDefinitionVo;
	}

}