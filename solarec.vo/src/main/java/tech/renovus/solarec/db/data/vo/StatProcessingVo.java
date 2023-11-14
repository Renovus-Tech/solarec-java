package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStatProcessingVo;

public class StatProcessingVo extends DbStatProcessingVo {

	//--- Public constants ----------------------
	public static final Integer RESULT_PROCESSING_OK			= Integer.valueOf(200); //HttpServletResponse.SC_OK
	public static final Integer RESULT_PROCESSING_ERROR			= Integer.valueOf(500); //HttpServletResponse.SC_INTERNAL_SERVER_ERROR
	public static final Integer RESULT_PROCESSING_PENDING	= Integer.valueOf(-1);
	
	//--- Constructors --------------------------
	public StatProcessingVo() {
	}

	public StatProcessingVo(Integer statProId) {
		this.setPk(statProId);
	}

}