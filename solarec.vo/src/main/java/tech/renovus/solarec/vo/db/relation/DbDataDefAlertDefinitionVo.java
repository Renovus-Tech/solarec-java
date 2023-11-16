package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseDataDefAlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;

public class DbDataDefAlertDefinitionVo extends BaseDataDefAlertDefinitionVo implements ISynchronizable<DbDataDefAlertDefinitionVo> {

	//--- Private properties --------------------
	private AlertDefinitionVo alertDefVo;
	
	//--- Constructors --------------------------
	public DbDataDefAlertDefinitionVo() {
	}

	public DbDataDefAlertDefinitionVo(Integer dataDefId, Integer alertDefId) {
		this.setPk(dataDefId, alertDefId);
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

	@Override public void synchronize(DbDataDefAlertDefinitionVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public AlertDefinitionVo getAlertDefVo() {
		return alertDefVo;
	}

	public void setAlertDefVo(AlertDefinitionVo alertDefinitionVo) {
		this.alertDefVo = alertDefinitionVo;
	}

}