package tech.renovus.solarec.db.data.vo;

import java.util.Date;

import tech.renovus.solarec.db.data.vo.db.DbLocDataVo;
import tech.renovus.solarec.util.interfaces.IData;

public class LocDataVo extends DbLocDataVo implements IData {

	//--- Constructors --------------------------
	public LocDataVo() {
	}

	public LocDataVo(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId) {
		this.setPk(cliId, locId, dataDate, dataTypeId);
	}

	public LocDataVo(Date dataDate, Integer dataTypeId, Double dataValue) {
		super();
		
		this.setDataDate(dataDate);
		this.setDataTypeId(dataTypeId);
		this.setDataValue(dataValue);
	}
	
	//--- Overridden methods --------------------
	@Override public int compareTo(IData data) { return this.getDataDate().compareTo(data.getDataDate()); }

}