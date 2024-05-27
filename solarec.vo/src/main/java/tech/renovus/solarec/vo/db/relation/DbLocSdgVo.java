package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocSdgVo;
import tech.renovus.solarec.vo.db.data.SdgVo;

public class DbLocSdgVo extends BaseLocSdgVo implements ISynchronizable<DbLocSdgVo> {

	//--- Private properties --------------------
	private SdgVo sdgVo;
	
	//--- Constructors --------------------------
	public DbLocSdgVo() {
	}

	public DbLocSdgVo(Integer cliId, Integer locId, Integer sdgId) {
		this.setPk(cliId, locId, sdgId);
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

	@Override public void synchronize(DbLocSdgVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- getters and Setters -------------------
	public SdgVo getSdgVo() {
		return sdgVo;
	}

	public void setSdgVo(SdgVo sdgVo) {
		this.sdgVo = sdgVo;
	}

}