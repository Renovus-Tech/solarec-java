package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseGeneratorVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.FrequencyVo;
import tech.renovus.solarec.vo.db.data.GenAlertVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GenMetadataVo;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;
import tech.renovus.solarec.vo.db.data.GenPowerVo;

public class DbGeneratorVo extends BaseGeneratorVo implements ISynchronizable<DbGeneratorVo> {

	//--- Protected properties ------------------
	protected DataDefinitionVo dataDefinitionVo;
	protected FrequencyVo frequencyVo;

	protected Collection<GenPowerVo> powerCurve;
	
	protected Collection<GenDataVo> datas;
	protected Collection<GenAlertVo> alerts;
	protected Collection<GenNeighbourVo> neighbours;
	protected Collection<GenDataDefParameterVo> dataDefParameters; 
	protected Collection<GenMetadataVo> metadata; 

	//--- Constructors --------------------------
	public DbGeneratorVo() {
	}

	public DbGeneratorVo(Integer cliId, Integer genId) {
		this.setPk(cliId, genId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof GenDataVo) {
				((GenDataVo) obj).setCliId(this.getCliId());
				((GenDataVo) obj).setGenId(this.getGenId());

			} else if (obj instanceof GenAlertVo) {
				((GenAlertVo) obj).setCliId(this.getCliId());
				((GenAlertVo) obj).setGenId(this.getGenId());

			} else if (obj instanceof GenPowerVo) {
				((GenPowerVo) obj).setCliId(this.getCliId());
				((GenPowerVo) obj).setGenId(this.getGenId());
				
			} else if (obj instanceof GenNeighbourVo) {
				((GenNeighbourVo) obj).setCliId(this.getCliId());
				((GenNeighbourVo) obj).setGenId(this.getGenId());
				
			} else if (obj instanceof GenDataDefParameterVo) {
				((GenDataDefParameterVo) obj).setCliId(this.getCliId());
				((GenDataDefParameterVo) obj).setGenId(this.getGenId());
				((GenDataDefParameterVo) obj).setDataDefId(this.getDataDefId());
				
			} else if (obj instanceof GenMetadataVo) {
				((GenMetadataVo) obj).setCliId(this.getCliId());
				((GenMetadataVo) obj).setGenId(this.getGenId());
			}
			
			if (obj instanceof ISynchronizable) ((ISynchronizable) obj).setChildrensId();
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.datas);
		this.setChildrensId(this.alerts);
		this.setChildrensId(this.powerCurve);
		this.setChildrensId(this.neighbours);
		this.setChildrensId(this.dataDefParameters);
		this.setChildrensId(this.metadata);
	}

	@Override public void synchronize(DbGeneratorVo dbVo) {
		this.setChildrensId();
		
		this.powerCurve			= BaseDbUtil.compareCollections(this.powerCurve,		(dbVo != null)?dbVo.powerCurve:null,			BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.neighbours			= BaseDbUtil.compareCollections(this.neighbours,		(dbVo != null)?dbVo.neighbours:null,			BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.dataDefParameters	= BaseDbUtil.compareCollections(this.dataDefParameters,	(dbVo != null)?dbVo.dataDefParameters:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.metadata			= BaseDbUtil.compareCollections(this.metadata,			(dbVo != null)?dbVo.metadata:null,				BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.powerCurve, syncType);
		BaseDbUtil.setAll(this.neighbours, syncType);
		BaseDbUtil.setAll(this.dataDefParameters, syncType);
		BaseDbUtil.setAll(this.metadata, syncType);
	}

	//--- Public methods ------------------------
	public void add(GenDataVo vo) {
		if (vo == null) return;
		if (this.datas == null) this.datas = new ArrayList<>(1);
		this.datas.add(vo);
	}
	
	public void add(GenAlertVo vo) {
		if (vo == null) return;
		if (this.alerts == null) this.alerts = new ArrayList<>(1);
		this.alerts.add(vo);
	}

	public void add(GenNeighbourVo vo) {
		if (vo == null) return;
		if (this.neighbours == null) this.neighbours = new ArrayList<>(1);
		this.neighbours.add(vo);
	}
	
	public void add(GenDataDefParameterVo vo) {
		if (vo == null) return;
		if (this.dataDefParameters == null) this.dataDefParameters = new ArrayList<>(1);
		this.dataDefParameters.add(vo);
	}

	public void add(GenMetadataVo vo) {
		if (vo == null) return;
		if (this.metadata == null) this.metadata = new ArrayList<>(1);
		this.metadata.add(vo);
	}
	
	//--- Getters and Setters -------------------
	public Collection<GenDataVo> getDatas() {
		return datas;
	}
	public void setDatas(Collection<GenDataVo> datas) {
		this.datas = datas;
	}
	public DataDefinitionVo getDataDefinitionVo() {
		return dataDefinitionVo;
	}
	public void setDataDefinitionVo(DataDefinitionVo dataDefinitionVo) {
		this.dataDefinitionVo = dataDefinitionVo;
	}
	public Collection<GenPowerVo> getPowerCurve() {
		return powerCurve;
	}
	public void setPowerCurve(Collection<GenPowerVo> powerCurve) {
		this.powerCurve = powerCurve;
	}
	public Collection<GenAlertVo> getAlerts() {
		return alerts;
	}
	public void setAlerts(Collection<GenAlertVo> alerts) {
		this.alerts = alerts;
	}
	public Collection<GenNeighbourVo> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(Collection<GenNeighbourVo> neighbours) {
		this.neighbours = neighbours;
	}
	public Collection<GenDataDefParameterVo> getDataDefParameters() {
		return dataDefParameters;
	}
	public void setDataDefParameters(Collection<GenDataDefParameterVo> dataDefParameters) {
		this.dataDefParameters = dataDefParameters;
	}
	public Collection<GenMetadataVo> getMetadata() {
		return metadata;
	}
	public void setMetadata(Collection<GenMetadataVo> metadata) {
		this.metadata = metadata;
	}
	public FrequencyVo getFrequencyVo() {
		return frequencyVo;
	}
	public void setFrequencyVo(FrequencyVo frequencyVo) {
		this.frequencyVo = frequencyVo;
	}
}