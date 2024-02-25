package tech.renovus.solarec.vo.db.data;

import java.util.Date;

import tech.renovus.solarec.util.interfaces.IData;
import tech.renovus.solarec.vo.db.relation.DbGenDataVo;

public class GenDataVo extends DbGenDataVo implements IData {

	//--- Private properties --------------------
	private double amount = 0;
	private double value = 0;
	
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
	
	//--- Public methods ------------------------
	public void add(double value) {
		this.amount ++;
		this.value += value;
	}
	
	public void aggregate() {
		this.setDataValue(Double.valueOf(this.amount == 0 ? 0 : this.value / this.amount));
	}
}