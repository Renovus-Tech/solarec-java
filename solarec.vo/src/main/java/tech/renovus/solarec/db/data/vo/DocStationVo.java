package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDocStationVo;

public class DocStationVo extends DbDocStationVo {

	//--- Private properties --------------------
	private StationVo stationVo;
	
	//--- Constructors --------------------------
	public DocStationVo() {
	}

	public DocStationVo(Integer cliId, Integer docId, Integer staId) {
		this.setPk(cliId, docId, staId);
	}
	
	public DocStationVo(DocumentVo docVo, StationVo staVo) {
		this.setPk(docVo.getCliId(), docVo.getDocId(), staVo.getStaId());
		this.stationVo = staVo;
	}

	//--- Getters and Setters -------------------
	public StationVo getStationVo() {
		return stationVo;
	}
	public void setStationVo(StationVo stationVo) {
		this.stationVo = stationVo;
	}

}