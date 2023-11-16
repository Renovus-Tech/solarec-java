package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.interfaces.IAlert;
import tech.renovus.solarec.vo.db.relation.DbLocAlertVo;

public class LocAlertVo extends DbLocAlertVo implements IAlert {

	//--- Constructors --------------------------
	public LocAlertVo() {
	}

	public LocAlertVo(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.setPk(cliId, locId, alertDefId, alertDateAdded);
	}

}