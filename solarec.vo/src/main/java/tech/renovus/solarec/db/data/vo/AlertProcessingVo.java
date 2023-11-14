package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbAlertProcessingVo;

public class AlertProcessingVo extends DbAlertProcessingVo {

	//--- Public constants ----------------------
	public static final Integer RESULT_PROCESSING_OK		= Integer.valueOf(200); //javax.servlet.http.HttpServletResponse.SC_OK
	public static final Integer RESULT_PROCESSING_ERROR		= Integer.valueOf(500); //javax.servlet.httpHttpServletResponse.SC_INTERNAL_SERVER_ERROR

	//--- Constructors --------------------------
	public AlertProcessingVo() {
	}

	public AlertProcessingVo(Integer alertProId) {
		this.setPk(alertProId);
	}

}