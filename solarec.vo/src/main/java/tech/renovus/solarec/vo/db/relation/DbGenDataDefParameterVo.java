package tech.renovus.solarec.vo.db.relation;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseGenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class DbGenDataDefParameterVo extends BaseGenDataDefParameterVo implements ISynchronizable<DbGenDataDefParameterVo> {

	//--- Private properties --------------------
	protected DataDefParameterVo dataDefParameter;

	//--- Constructors --------------------------
	public DbGenDataDefParameterVo() {
	}

	public DbGenDataDefParameterVo(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, genId, dataDefId, dataDefParId);
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

	@Override public void synchronize(DbGenDataDefParameterVo dbVo) {
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