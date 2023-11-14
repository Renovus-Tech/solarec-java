package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStaAlertVo;
import tech.renovus.solarec.util.interfaces.IAlert;

public class StaAlertVo extends DbStaAlertVo implements IAlert {

	//--- Constructors --------------------------
	public StaAlertVo() {
	}

	public StaAlertVo(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.setPk(cliId, staId, alertDefId, alertDateAdded);
	}

}