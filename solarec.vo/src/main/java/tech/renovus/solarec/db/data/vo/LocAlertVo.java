package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocAlertVo;
import tech.renovus.solarec.util.interfaces.IAlert;

public class LocAlertVo extends DbLocAlertVo implements IAlert {

	//--- Constructors --------------------------
	public LocAlertVo() {
	}

	public LocAlertVo(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.setPk(cliId, locId, alertDefId, alertDateAdded);
	}

}