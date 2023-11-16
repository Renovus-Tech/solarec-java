package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.IDataContainer;
import tech.renovus.solarec.vo.db.relation.DbClientVo;

public class ClientVo extends DbClientVo {

	//--- Flags ---------------------------------
	public static final int FLAG_REPORT_ENABLED	= 0;
	
	//--- Constructors --------------------------
	public ClientVo() {
	}

	public ClientVo(Integer cliId) {
		this.setPk(cliId);
	}

	//--- Public methods ------------------------
	public IDataContainer getDataContainer(String code) {
		if (StringUtil.isEmpty(code)) return null;
		
		if (CollectionUtil.notEmpty(this.getLocations())) {
			for (LocationVo locVo : this.getLocations()) {
				IDataContainer result = locVo.getDataContainer(code);
				if (result != null) return result;
			}
		}
		
		return null;
	}
	
}