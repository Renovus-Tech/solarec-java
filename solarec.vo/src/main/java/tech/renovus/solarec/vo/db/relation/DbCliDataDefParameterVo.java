package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseCliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class DbCliDataDefParameterVo extends BaseCliDataDefParameterVo implements ISynchronizable<DbCliDataDefParameterVo> {

	//--- Private properties --------------------
	protected DataDefParameterVo dataDefParameter;
	
	//--- Constructors --------------------------
	public DbCliDataDefParameterVo() {
	}

	public DbCliDataDefParameterVo(Integer cliId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, dataDefId, dataDefParId);
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

	@Override public void synchronize(DbCliDataDefParameterVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public DataDefParameterVo getDataDefParameter() {
		return dataDefParameter;
	}

	public void setDataDefParameter(DataDefParameterVo dataDefParameter) {
		this.dataDefParameter = dataDefParameter;
	}

}