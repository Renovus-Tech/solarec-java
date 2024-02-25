package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataProcessingVo;

public class DataProcessingVo extends DbDataProcessingVo {

	//--- Public constants ----------------------
	public static final Integer RESULT_PROCESSING_PROCESSING	= Integer.valueOf(102); //HttpServletResponse.SC_PROCESSING
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