package tech.renovus.solarec.db.data.vo.db;

import tech.renovus.solarec.db.data.vo.base.BaseStatProcessingVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;

public class DbStatProcessingVo extends BaseStatProcessingVo implements ISynchronizable<DbStatProcessingVo> {

	//--- Constructors --------------------------
	public DbStatProcessingVo() {
	}

	public DbStatProcessingVo(Integer statProId) {
		this.setPk(statProId);
	}

	//--- Private methods -----------------------
//	private void setChildrensId(Collection<? extends BaseDbVo> col) {
//		if (CollectionUtil.isEmpty(col)) return;
//		
//		for (BaseDbVo obj : col) {
//		}
//	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		//this.setChildrensId(this.xxx);
	}

	@Override public void synchronize(DbStatProcessingVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

}