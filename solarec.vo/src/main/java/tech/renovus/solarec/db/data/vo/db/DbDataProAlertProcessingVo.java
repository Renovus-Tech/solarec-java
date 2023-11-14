package tech.renovus.solarec.db.data.vo.db;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.base.BaseDataProAlertProcessingVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbDataProAlertProcessingVo extends BaseDataProAlertProcessingVo implements ISynchronizable<DbDataProAlertProcessingVo> {

	//--- Constructors --------------------------
	public DbDataProAlertProcessingVo() {
	}

	public DbDataProAlertProcessingVo(Integer dataProId, Integer alertProId) {
		this.setPk(dataProId, alertProId);
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

	@Override public void synchronize(DbDataProAlertProcessingVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

}