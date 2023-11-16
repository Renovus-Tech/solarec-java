package tech.renovus.solarec.vo.db.data;

import java.util.Date;

import tech.renovus.solarec.vo.db.relation.DbLocWeaDataHourVo;

public class LocWeaDataHourVo extends DbLocWeaDataHourVo {

	//--- Constructors --------------------------
	public LocWeaDataHourVo() {
	}

	public LocWeaDataHourVo(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) {
		this.setPk(cliId, locId, locWeaDataId, locWeaDataHour);
	}

	public LocWeaDataHourVo(Date hour, String response, Date retrieve) {
		this.setLocWeaDataHour(hour);
		this.setLocWeaDataResponse(response);
		this.setLocWeaDataRetrieve(retrieve);
	}

}