package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocMetadataVo;

public class DbLocMetadataVo extends BaseLocMetadataVo implements ISynchronizable<DbLocMetadataVo> {

	//--- Constructors --------------------------
	public DbLocMetadataVo() {
	}

	public DbLocMetadataVo(Integer cliId, Integer locId, String metadataName) {
		this.setPk(cliId, locId, metadataName);
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

	@Override public void synchronize(DbLocMetadataVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

}