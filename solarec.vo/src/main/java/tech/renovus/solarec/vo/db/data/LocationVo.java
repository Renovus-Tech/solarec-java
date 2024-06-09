package tech.renovus.solarec.vo.db.data;

import java.util.Date;
import java.util.Optional;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbLocationVo;

public class LocationVo extends DbLocationVo implements IDataContainer, Comparable<LocationVo> {

	//--- Flags ---------------------------------
	public static final int FLAG_REPORT_ENABLED				= 0;
	public static final int FLAG_HIDE_FROM_DASHBOARD		= 1;
	public static final int FLAG_ALERT_CALCULATION_ENABLED	= 2;
	public static final int FLAG_ENABLED_FOR_EMAIL_ALERT	= 3;
	public static final int FLAG_CONNECTED_TO_GRID			= 4;

	public static final String TYPE_SOLAR				= "solar";
	
	//--- Private properties --------------------
	private boolean required;
	
	//--- Constructors --------------------------
	public LocationVo() {
	}

	public LocationVo(Integer cliId, Integer locId) {
		this.setPk(cliId, locId);
	}

	//--- Overridden methods --------------------
	@Override public int compareTo(LocationVo arg0) { return arg0 == null ? 1 : this.getLocName().compareTo(arg0.getLocName()); }
	
	@Override public String getName() { return this.getLocName(); }
	@Override public Integer getId() { return this.getLocId(); }
	@Override public boolean isRequired() { return required; }
	@Override public void addData(Date date, int typeId, double value) { this.add(new LocDataVo(date, Integer.valueOf(typeId), Double.valueOf(value))); }
	
	//--- Public methods ------------------------
	public IDataContainer getDataContainer(String name) {
		if (name == null || ClassUtil.equals(this.getLocName(), name)) {
			return this;
		}
		
		if (CollectionUtil.notEmpty(this.generators)) {
			for (GeneratorVo gen : this.generators) {
				if (name.equalsIgnoreCase(gen.getName())) {
					return gen;
				}
			}
		}
		if (CollectionUtil.notEmpty(this.stations)) {
			for (StationVo sta : this.stations) {
				if (name.equalsIgnoreCase(sta.getName())) {
					return sta;
				}
			}
		}
		
		return null;
	}
	
	public LocMetadataVo getMetadataVo(String name) {
		if (CollectionUtil.isEmpty(this.metadata)) {
			return null;
		}
		Optional<LocMetadataVo> option = this.metadata.stream().filter(x -> ClassUtil.equals(name, x.getMetadataName())).findFirst();
		return option.isPresent() ? option.get() :  null;
	}

	//--- Getters and setters -------------------
	public void setRequired(boolean required) {
		this.required = required;
	}

	public LocDataDefParameterVo getDataDefParameterVo(String name) {
		if (CollectionUtil.isEmpty(this.dataDefParameters)) {
			return null;
		}
		Optional<LocDataDefParameterVo> option =  this.dataDefParameters.stream().filter(x -> ClassUtil.equals(name, x.getDataDefParameter().getDataDefParName())).findFirst();
		return option.isPresent() ? option.get() : null;

	}
}