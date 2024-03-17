package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseStationVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.StaAlertVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StaMetadataVo;

public class DbStationVo extends BaseStationVo implements ISynchronizable<DbStationVo> {

	//--- Protected properties ------------------
	protected DataDefinitionVo dataDefinitionVo;
	protected Collection<StaDataVo> datas;
	protected Collection<StaAlertVo> alerts;
	protected Collection<StaMetadataVo> metadata;
	
	//--- Constructors --------------------------
	public DbStationVo() {
	}

	public DbStationVo(Integer cliId, Integer staId) {
		this.setPk(cliId, staId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof StaDataVo) {
				((StaDataVo) obj).setCliId(this.getCliId());
				((StaDataVo) obj).setStaId(this.getStaId());
				
			} else if (obj instanceof StaAlertVo) {
				((StaAlertVo) obj).setCliId(this.getCliId());
				((StaAlertVo) obj).setStaId(this.getStaId());
			}
			
			if (obj instanceof ISynchronizable) ((ISynchronizable) obj).setChildrensId();
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.datas);
		this.setChildrensId(this.alerts);
		this.setChildrensId(this.metadata);
	}

	@Override public void synchronize(DbStationVo dbVo) {
		this.setChildrensId();
		
		this.metadata	= BaseDbUtil.compareCollections(this.metadata,	(dbVo != null)?dbVo.metadata:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.metadata, syncType);
	}
	
	//--- Public methods ------------------------
	public void add(StaDataVo vo) {
		if (vo == null) return;
		if (this.datas == null) this.datas = new ArrayList<>(1);
		this.datas.add(vo);
	}

	public void add(StaAlertVo vo) {
		if (vo == null) return;
		if (this.alerts == null) this.alerts = new ArrayList<>(1);
		this.alerts.add(vo);
	}
	
	public void add(StaMetadataVo vo) {
		if (vo == null) return;
		if (this.metadata == null) this.metadata = new ArrayList<>(1);
		this.metadata.add(vo);
	}
	
	//--- Getters and Setters -------------------
	public Collection<StaDataVo> getDatas() {
		return datas;
	}
	public void setDatas(Collection<StaDataVo> datas) {
		this.datas = datas;
	}
	public DataDefinitionVo getDataDefinitionVo() {
		return dataDefinitionVo;
	}
	public void setDataDefinitionVo(DataDefinitionVo dataDefinitionVo) {
		this.dataDefinitionVo = dataDefinitionVo;
	}
	public Collection<StaAlertVo> getAlerts() {
		return alerts;
	}
	public void setAlerts(Collection<StaAlertVo> alerts) {
		this.alerts = alerts;
	}
	public Collection<StaMetadataVo> getMetadata() {
		return metadata;
	}
	public void setMetadata(Collection<StaMetadataVo> metadata) {
		this.metadata = metadata;
	}
}