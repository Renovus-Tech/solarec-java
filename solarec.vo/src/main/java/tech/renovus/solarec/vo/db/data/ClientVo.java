package tech.renovus.solarec.vo.db.data;

import java.util.Optional;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbClientVo;

public class ClientVo extends DbClientVo {

	//--- Flags ---------------------------------
	public static final int FLAG_REPORT_ENABLED				= 0;
	public static final int FLAG_ALERT_CALCULATION_ENABLED	= 1;
	public static final int FLAG_ENABLED_FOR_EMAIL_ALERT	= 2;
	
	//--- Constructors --------------------------
	public ClientVo() {
	}

	public ClientVo(Integer cliId) {
		this.setPk(cliId);
	}

	//--- Public methods ------------------------
	public IDataContainer getDataContainer(String code) {
		if (StringUtil.isEmpty(code)) {
			return null;
		}
		
		if (CollectionUtil.notEmpty(this.getLocations())) {
			for (LocationVo locVo : this.getLocations()) {
				IDataContainer result = locVo.getDataContainer(code);
				if (result != null) {
					return result;
				}
			}
		}
		
		return null;
	}

	public CliDataDefParameterVo getDataDefParameterVo(String name) {
		if (CollectionUtil.isEmpty(this.dataDefParameters)) {
			return null;
		}
		Optional<CliDataDefParameterVo> option =  this.dataDefParameters.stream().filter(x -> ClassUtil.equals(name, x.getDataDefParameter().getDataDefParName())).findFirst();
		return option.isPresent() ? option.get() : null;
	}
	
	public CliMetadataVo getMetadataVo(String name) {
		if (CollectionUtil.isEmpty(this.metadata)) {
			return null;
		}
		Optional<CliMetadataVo> option = this.metadata.stream().filter(x -> ClassUtil.equals(name, x.getMetadataName())).findFirst();
		return option.isPresent() ? option.get() :  null;
	}
	
}