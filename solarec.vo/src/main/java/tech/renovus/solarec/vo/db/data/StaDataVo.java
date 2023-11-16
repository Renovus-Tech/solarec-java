package tech.renovus.solarec.vo.db.data;

import java.util.Date;

import tech.renovus.solarec.util.interfaces.IData;
import tech.renovus.solarec.vo.db.relation.DbStaDataVo;

public class StaDataVo extends DbStaDataVo implements IData {

	//--- Constructors --------------------------
	public StaDataVo() {
	}

	public StaDataVo(Integer cliId, Integer staId, java.util.Date staDate, Integer dataTypeId) {
		this.setPk(cliId, staId, staDate, dataTypeId);
	}

	public StaDataVo(Date dataDate, Integer dataTypeId, Double dataValue) {
		super();
		
		this.setDataDate(dataDate);
		this.setDataTypeId(dataTypeId);
		this.setDataValue(dataValue);
	}
	
	//--- Overridden methods --------------------
	@Override public int compareTo(IData data) { return this.getDataDate().compareTo(data.getDataDate()); }

	//--- Public methods ------------------------
	public StaDataVo copy() {
		StaDataVo result = new StaDataVo();
		result.setDataDate(this.getDataDate());
		result.setDataTypeId(this.getDataTypeId());
		result.setDataProId(this.getDataProId());
		result.setDataValue(this.getDataValue());
		result.setDataDateAdded(this.getDataDateAdded());
		
		return result;
	}
}