package tech.renovus.solarec.db.data.vo;

import java.util.Date;

import tech.renovus.solarec.db.data.vo.db.DbLocWeaDataHourVo;

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