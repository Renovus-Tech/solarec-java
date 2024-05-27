package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.IFlags;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseCliLocAlertVo;

public class DbCliLocAlertVo extends BaseCliLocAlertVo implements ISynchronizable<DbCliLocAlertVo>, IFlags {

	//--- Constructors --------------------------
	public DbCliLocAlertVo() {
	}

	public DbCliLocAlertVo(Integer cliId, Integer locId, Integer cliLocAlertId) {
		this.setPk(cliId, locId, cliLocAlertId);
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

	@Override public void synchronize(DbCliLocAlertVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	@Override public String getFlags() { return this.getCliLocAlertFlags(); }

	@Override public void setFlags(String flags) { this.setCliLocAlertFlags(flags); }

}