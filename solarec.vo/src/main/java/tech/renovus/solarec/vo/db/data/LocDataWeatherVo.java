package tech.renovus.solarec.vo.db.data;

import java.util.Date;

import tech.renovus.solarec.vo.db.relation.DbLocDataWeatherVo;

public class LocDataWeatherVo extends DbLocDataWeatherVo {

	//--- Constructors --------------------------
	public LocDataWeatherVo() {
	}

	public LocDataWeatherVo(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) {
		this.setPk(cliId, locId, dataDateAdded, dataDate, dataTypeId);
	}

	public LocDataWeatherVo withSyncType(int syncType) {
		this.setSyncType(syncType);
		return this;
	}
	
	public LocDataWeatherVo withLocation(LocationVo locVo) {
		this.setCliId(locVo.getCliId());
		this.setLocId(locVo.getLocId());
		return this;
	}

	public LocDataWeatherVo withDataDateAdded(Date dataDateAdded) {
		this.setDataDateAdded(dataDateAdded);
		return this;
	}
	public LocDataWeatherVo withDataTypeId(int dataTypeId) {
		this.setDataTypeId(Integer.valueOf(dataTypeId));
		return this;
	}

	public LocDataWeatherVo withDataValue(Double dataValue) {
		this.setDataValue(dataValue);
		return this;
	}

	public LocDataWeatherVo withDataDate(Date dataDate) {
		this.setDataDate(dataDate);
		return this;
	}

}