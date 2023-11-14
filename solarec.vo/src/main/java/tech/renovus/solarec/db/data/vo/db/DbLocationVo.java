package tech.renovus.solarec.db.data.vo.db;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataDefinitionVo;
import tech.renovus.solarec.db.data.vo.GeneratorVo;
import tech.renovus.solarec.db.data.vo.LocAlertVo;
import tech.renovus.solarec.db.data.vo.LocDataVo;
import tech.renovus.solarec.db.data.vo.LocEstimationVo;
import tech.renovus.solarec.db.data.vo.StationVo;
import tech.renovus.solarec.db.data.vo.base.BaseLocationVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbLocationVo extends BaseLocationVo implements ISynchronizable<DbLocationVo> {

	//--- Private properties --------------------
	
	//--- Protected properties ------------------
	protected DataDefinitionVo dataDefinitionVo;
	
	protected Collection<StationVo> stations;
	protected Collection<GeneratorVo> generators;
	
	protected Collection<LocDataVo> datas;
	protected Collection<LocAlertVo> alerts;
	protected Collection<LocEstimationVo> estimations;
	
	//--- Constructors --------------------------
	public DbLocationVo() {
	}

	public DbLocationVo(Integer cliId, Integer locId) {
		this.setPk(cliId, locId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof LocDataVo) {
				((LocDataVo) obj).setCliId(this.getCliId());
				((LocDataVo) obj).setLocId(this.getLocId());
				
			} else if (obj instanceof LocAlertVo) {
				((LocAlertVo) obj).setCliId(this.getCliId());
				((LocAlertVo) obj).setLocId(this.getLocId());
				
			} else if (obj instanceof LocEstimationVo) {
				((LocEstimationVo) obj).setCliId(this.getCliId());
				((LocEstimationVo) obj).setLocId(this.getLocId());
			}
			
			if (obj instanceof ISynchronizable) ((ISynchronizable) obj).setChildrensId();
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.datas);
		this.setChildrensId(this.alerts);
		this.setChildrensId(this.estimations);
	}

	@Override public void synchronize(DbLocationVo dbVo) {
		this.setChildrensId();
		
		this.estimations	= BaseDbUtil.compareCollections(this.estimations,	(dbVo != null)?dbVo.estimations:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.estimations, syncType);
	}
	
	//--- Public methods ------------------------
	public void add(StationVo vo) {
		if (vo == null) return;
		if (this.stations == null) this.stations = new ArrayList<>(1);
		this.stations.add(vo);
	}
	
	public void add(GeneratorVo vo) {
		if (vo == null) return;
		if (this.generators == null) this.generators = new ArrayList<>(1);
		this.generators.add(vo);
	}
	
	public void add(LocDataVo vo) {
		if (vo == null) return;
		if (this.datas == null) this.datas = new ArrayList<>(1);
		this.datas.add(vo);
	}
	
	public void add(LocAlertVo vo) {
		if (vo == null) return;
		if (this.alerts == null) this.alerts = new ArrayList<>(1);
		this.alerts.add(vo);
	}

	public void add(LocEstimationVo vo) {
		if (vo == null) return;
		if (this.estimations == null) this.estimations = new ArrayList<>(1);
		this.estimations.add(vo);
	}
	
	public void addGenerators(Collection<GeneratorVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (GeneratorVo vo : vos) this.add(vo);
	}
	
	public void addStations(Collection<StationVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (StationVo vo : vos) this.add(vo);
	}
	
	public void addEstimations(Collection<LocEstimationVo> vos) {
		if (CollectionUtil.isEmpty(vos)) return;
		for (LocEstimationVo vo : vos) this.add(vo);
	}
	
	//--- Getters and Setters -------------------
	public Collection<StationVo> getStations() {
		return stations;
	}
	public void setStations(Collection<StationVo> stations) {
		this.stations = stations;
	}
	public Collection<GeneratorVo> getGenerators() {
		return generators;
	}
	public void setGenerators(Collection<GeneratorVo> generators) {
		this.generators = generators;
	}
	public Collection<LocDataVo> getDatas() {
		return datas;
	}
	public void setDatas(Collection<LocDataVo> datas) {
		this.datas = datas;
	}
	public DataDefinitionVo getDataDefinitionVo() {
		return dataDefinitionVo;
	}
	public void setDataDefinitionVo(DataDefinitionVo dataDefinitionVo) {
		this.dataDefinitionVo = dataDefinitionVo;
	}
	public Collection<LocAlertVo> getAlerts() {
		return alerts;
	}
	public void setAlerts(Collection<LocAlertVo> alerts) {
		this.alerts = alerts;
	}
	public Collection<LocEstimationVo> getEstimations() {
		return estimations;
	}
	public void setEstimations(Collection<LocEstimationVo> estimations) {
		this.estimations = estimations;
	}
}