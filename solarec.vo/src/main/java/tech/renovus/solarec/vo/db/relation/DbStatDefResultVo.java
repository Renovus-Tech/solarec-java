package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseStatDefResultVo;
import tech.renovus.solarec.vo.db.data.StatTypeVo;

public class DbStatDefResultVo extends BaseStatDefResultVo implements ISynchronizable<DbStatDefResultVo> {

	//--- Private properties --------------------
	private StatTypeVo statTypevo;
	
	//--- Constructors --------------------------
	public DbStatDefResultVo() {
	}

	public DbStatDefResultVo(Integer statDefId, Integer statTypeId) {
		this.setPk(statDefId, statTypeId);
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

	@Override public void synchronize(DbStatDefResultVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public StatTypeVo getStatTypevo() {
		return statTypevo;
	}
	public void setStatTypevo(StatTypeVo statTypevo) {
		this.statTypevo = statTypevo;
	}
}