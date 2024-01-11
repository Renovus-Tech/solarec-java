package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseCliGenUsrAlertVo;

public class DbCliGenUsrAlertVo extends BaseCliGenUsrAlertVo implements ISynchronizable<DbCliGenUsrAlertVo> {

	//--- Constructors --------------------------
	public DbCliGenUsrAlertVo() {
	}

	public DbCliGenUsrAlertVo(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) {
		this.setPk(cliId, genId, usrId, cliGenAlertId);
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

	@Override public void synchronize(DbCliGenUsrAlertVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

}