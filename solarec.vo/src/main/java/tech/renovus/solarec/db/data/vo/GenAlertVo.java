package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbGenAlertVo;
import tech.renovus.solarec.util.interfaces.IAlert;

public class GenAlertVo extends DbGenAlertVo implements IAlert {

	//--- Constructors --------------------------
	public GenAlertVo() {
	}

	public GenAlertVo(Integer cliId, Integer genId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.setPk(cliId, genId, alertDefId, alertDateAdded);
	}

	
	 public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	 public static final String COLUMN_ALERT_DATE_ADDED = "alert_date_added";
	 public static final String COLUMN_ALERT_DATE_SEND = "alert_date_send";
	 public static final String COLUMN_ALERT_MESSAGE = "alert_message";
	 public static final String COLUMN_ALERT_PRO_ID = "alert_pro_id";
	
}