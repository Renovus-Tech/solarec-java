package tech.renovus.solarec.db.data.vo.db;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.base.BaseDocLocationVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbDocLocationVo extends BaseDocLocationVo implements ISynchronizable<DbDocLocationVo> {

	//--- Constructors --------------------------
	public DbDocLocationVo() {
	}

	public DbDocLocationVo(Integer cliId, Integer docId, Integer locId) {
		this.setPk(cliId, docId, locId);
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

	@Override public void synchronize(DbDocLocationVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

}