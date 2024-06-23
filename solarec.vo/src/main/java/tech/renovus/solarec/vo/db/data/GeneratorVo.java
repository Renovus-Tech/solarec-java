package tech.renovus.solarec.vo.db.data;

import java.util.Date;
import java.util.Optional;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbGeneratorVo;

public class GeneratorVo extends DbGeneratorVo implements IDataContainer {

	//--- Flags ---------------------------------
	public static final int FLAG_ENABLED						= 0;
	public static final int FLAG_ENABLED_FOR_EMAIL_ALERT		= 1;
	public static final int FLAG_REGISTERED_AT_CERT_PROVIDER	= 2;
	
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

	public GenDataDefParameterVo getDataDefParameterVo(String name) {
		if (CollectionUtil.isEmpty(this.dataDefParameters)) {
			return null;
		}
		Optional<GenDataDefParameterVo> option =  this.dataDefParameters.stream().filter(x -> ClassUtil.equals(name, x.getDataDefParameter().getDataDefParName())).findFirst();
		return option.isPresent() ? option.get() : null;

	}
	
	public GenMetadataVo getMetadataVo(String name) {
		if (CollectionUtil.isEmpty(this.metadata)) {
			return null;
		}
		Optional<GenMetadataVo> option = this.metadata.stream().filter(x -> ClassUtil.equals(name, x.getMetadataCode())).findFirst();
		return option.isPresent() ? option.get() :  null;
	}
}