package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocEstimationVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbLocEstimationVo extends BaseLocEstimationVo implements ISynchronizable<DbLocEstimationVo> {

	//--- Private properties --------------------
	private LocationVo locationVo;
	
	//--- Constructors --------------------------
	public DbLocEstimationVo() {
	}

	public DbLocEstimationVo(Integer cliId, Integer locId, Integer locEstId) {
		this.setPk(cliId, locId, locEstId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		//this.setChildrensId(this.xxx);
	}

	@Override public void synchronize(DbLocEstimationVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}

}