package tech.renovus.solarec.db.data.vo.db;

import tech.renovus.solarec.db.data.vo.DataDefinitionVo;
import tech.renovus.solarec.db.data.vo.StatDefinitionVo;
import tech.renovus.solarec.db.data.vo.base.BaseDataDefStatDefinitionVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;

public class DbDataDefStatDefinitionVo extends BaseDataDefStatDefinitionVo implements ISynchronizable<DbDataDefStatDefinitionVo> {

	//-- Private properties ---------------------
	private DataDefinitionVo dataDefVo;
	private StatDefinitionVo statDefVo;
	
	//--- Constructors --------------------------
	public DbDataDefStatDefinitionVo() {
	}

	public DbDataDefStatDefinitionVo(Integer dataDefId, Integer statDefId) {
		this.setPk(dataDefId, statDefId);
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

	@Override public void synchronize(DbDataDefStatDefinitionVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public DataDefinitionVo getDataDefVo() {
		return dataDefVo;
	}
	public void setDataDefVo(DataDefinitionVo dataDefVo) {
		this.dataDefVo = dataDefVo;
	}
	public StatDefinitionVo getStatDefVo() {
		return statDefVo;
	}
	public void setStatDefVo(StatDefinitionVo statDefVo) {
		this.statDefVo = statDefVo;
	}

}