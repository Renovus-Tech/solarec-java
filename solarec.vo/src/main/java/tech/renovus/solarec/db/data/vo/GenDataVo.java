package tech.renovus.solarec.db.data.vo;

import java.util.Date;

import tech.renovus.solarec.db.data.vo.db.DbGenDataVo;
import tech.renovus.solarec.util.interfaces.IData;

public class GenDataVo extends DbGenDataVo implements IData {

	//--- Constructors --------------------------
	public GenDataVo() {
	}

	public GenDataVo(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId) {
		this.setPk(cliId, genId, dataDate, dataTypeId);
	}

	public GenDataVo(Date dataDate, Integer dataTypeId, Double dataValue) {
		super();
		
		this.setDataDate(dataDate);
		this.setDataTypeId(dataTypeId);
		this.setDataValue(dataValue);
	}

	//--- Overridden methods --------------------
	@Override public int compareTo(IData data) { return this.getDataDate().compareTo(data.getDataDate()); }
}