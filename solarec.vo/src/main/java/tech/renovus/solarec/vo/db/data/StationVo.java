package tech.renovus.solarec.vo.db.data;

import java.util.Date;

import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbStationVo;

public class StationVo extends DbStationVo implements IDataContainer {

	//--- Public constants ----------------------
	public static int FLAG_DEFAULT	= 0;
	
	//--- Private properties --------------------
//	private Map<Integer, Collection<Date>> typeDates;
	private boolean required = true;
	private Integer locId;
	
	//--- Constructors --------------------------
	public StationVo() {
	}

	public StationVo(Integer cliId, Integer staId) {
		this.setPk(cliId, staId);
	}

	//--- Overridden methods --------------------
	@Override public String getName() { return this.getStaName(); }
	@Override public Integer getId() { return this.getStaId(); }
	@Override public boolean isRequired() { return required; }
	@Override public void addData(Date date, int typeId, double value) { this.add(new StaDataVo(date, Integer.valueOf(typeId), Double.valueOf(value))); }
	
	//--- Getters and setters -------------------
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}
}