package tech.renovus.solarec.db.data.vo.db;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataTypeVo;
import tech.renovus.solarec.db.data.vo.base.BaseStatDefSourceVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbStatDefSourceVo extends BaseStatDefSourceVo implements ISynchronizable<DbStatDefSourceVo> {

	//--- Private properties --------------------
	private DataTypeVo vo;
	
	//--- Constructors --------------------------
	public DbStatDefSourceVo() {
	}

	public DbStatDefSourceVo(Integer statDefId, Integer dataTypeId) {
		this.setPk(statDefId, dataTypeId);
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

	@Override public void synchronize(DbStatDefSourceVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public DataTypeVo getVo() {
		return vo;
	}

	public void setVo(DataTypeVo vo) {
		this.vo = vo;
	}

}