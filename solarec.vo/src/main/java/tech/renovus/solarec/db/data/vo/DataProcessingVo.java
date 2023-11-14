package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataProcessingVo;

public class DataProcessingVo extends DbDataProcessingVo {

	//--- Public constants ----------------------
	public static final Integer RESULT_PROCESSING_OK			= Integer.valueOf(200); //HttpServletResponse.SC_OK
	public static final Integer RESULT_PROCESSING_OK_PARTIAL	= Integer.valueOf(206); //HttpServletResponse.SC_PARTIAL_CONTENT
	public static final Integer RESULT_PROCESSING_ERROR			= Integer.valueOf(500); //HttpServletResponse.SC_INTERNAL_SERVER_ERROR

	//--- Constructors --------------------------
	public DataProcessingVo() {
	}

	public DataProcessingVo(Integer dataProId) {
		this.setPk(dataProId);
	}

}