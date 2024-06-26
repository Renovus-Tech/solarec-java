package tech.renovus.solarec.vo.db.data;

import java.util.Date;
import java.util.Optional;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbStationVo;

public class StationVo extends DbStationVo implements IDataContainer {

	//--- Public constants ----------------------
	public static final int FLAG_DEFAULT						= 0;
	public static final int FLAG_ENABLED						= 1;
	public static final int FLAG_REGISTERED_AT_CERT_PROVIDER	= 2;

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
	
	//--- Public methods ------------------------
	public StaMetadataVo getMetadataVo(String name) {
		if (CollectionUtil.isEmpty(this.metadata)) {
			return null;
		}
		Optional<StaMetadataVo> option = this.metadata.stream().filter(x -> ClassUtil.equals(name, x.getMetadataName())).findFirst();
		return option.isPresent() ? option.get() :  null;
	}
	
	//--- Getters and setters -------------------
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	@Override
	public Integer getLocId() {
		return locId;
	}

	@Override
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
}