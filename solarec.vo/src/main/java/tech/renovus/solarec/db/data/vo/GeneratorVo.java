package tech.renovus.solarec.db.data.vo;

import java.util.Date;

import tech.renovus.solarec.db.data.vo.db.DbGeneratorVo;
import tech.renovus.solarec.util.interfaces.IDataContainer;

public class GeneratorVo extends DbGeneratorVo implements IDataContainer {

	//--- Private properties --------------------
	private boolean required = true;
	
	//--- Constructors --------------------------
	public GeneratorVo() {
	}

	public GeneratorVo(Integer cliId, Integer genId) {
		this.setPk(cliId, genId);
	}

	public GeneratorVo(Integer cliId, Integer locId, Integer genId, String genName) {
		this.setPk(cliId, genId);
		
		this.setGenName(genName);
		this.setLocId(locId);
	}
	
	//--- Overridden methods --------------------
	@Override public String getName() { return this.getGenName(); }
	@Override public Integer getId() { return this.getGenId(); }
	@Override public boolean isRequired() { return required; }
	@Override public void addData(Date date, int typeId, double value) { this.add(new GenDataVo(date, Integer.valueOf(typeId), Double.valueOf(value))); }

	//--- Getters and setters -------------------
	public void setRequired(boolean required) {
		this.required = required;
	}
}