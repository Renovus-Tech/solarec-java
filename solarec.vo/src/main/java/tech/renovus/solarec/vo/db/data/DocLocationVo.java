package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDocLocationVo;

public class DocLocationVo extends DbDocLocationVo {

	//--- Private properties --------------------
	private LocationVo locationVo;
	
	//--- Constructors --------------------------
	public DocLocationVo() {
	}

	public DocLocationVo(Integer cliId, Integer docId, Integer locId) {
		this.setPk(cliId, docId, locId);
	}
	
	public DocLocationVo(DocumentVo docVo, LocationVo locVo) {
		this.setPk(docVo.getCliId(), docVo.getDocId(), locVo.getLocId());
		this.locationVo = locVo;
	}

	//--- Getters and Setters -------------------
	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}

}