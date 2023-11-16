package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.interfaces.IAlert;
import tech.renovus.solarec.vo.db.relation.DbStaAlertVo;

public class StaAlertVo extends DbStaAlertVo implements IAlert {

	//--- Constructors --------------------------
	public StaAlertVo() {
	}

	public StaAlertVo(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.setPk(cliId, staId, alertDefId, alertDateAdded);
	}

}