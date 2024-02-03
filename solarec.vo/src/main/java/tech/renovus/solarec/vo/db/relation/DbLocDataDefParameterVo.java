package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class DbLocDataDefParameterVo extends BaseLocDataDefParameterVo implements ISynchronizable<DbLocDataDefParameterVo> {

	//--- Private properties --------------------
	protected DataDefParameterVo dataDefParameter;

	//--- Constructors --------------------------
	public DbLocDataDefParameterVo() {
	}

	public DbLocDataDefParameterVo(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, locId, dataDefId, dataDefParId);
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

	@Override public void synchronize(DbLocDataDefParameterVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}
	
	//--- Getters and setters -------------------
	public DataDefParameterVo getDataDefParameter() {
		return dataDefParameter;
	}

	public void setDataDefParameter(DataDefParameterVo dataDefParameter) {
		this.dataDefParameter = dataDefParameter;
	}

}