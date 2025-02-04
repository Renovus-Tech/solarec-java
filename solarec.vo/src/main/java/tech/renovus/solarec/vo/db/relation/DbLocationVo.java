package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocationVo;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.FrequencyVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocAlertVo;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.LocDataVo;
import tech.renovus.solarec.vo.db.data.LocEstimationVo;
import tech.renovus.solarec.vo.db.data.LocMetadataVo;
import tech.renovus.solarec.vo.db.data.LocSdgVo;
import tech.renovus.solarec.vo.db.data.LocTypeVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public class DbLocationVo extends BaseLocationVo implements ISynchronizable<DbLocationVo> {

	//--- Private properties --------------------
	
	//--- Protected properties ------------------
	protected DataDefinitionVo dataDefinitionVo;
	protected FrequencyVo frequencyVo;
	
	protected CountryVo countryVo;
	protected LocTypeVo locTypeVo;
	
	protected Collection<StationVo> stations;
	protected Collection<GeneratorVo> generators;
	
	protected Collection<LocDataVo> datas;
	protected Collection<LocAlertVo> alerts;
	protected Collection<LocEstimationVo> estimations;
	protected Collection<LocDataDefParameterVo> dataDefParameters;
	protected Collection<LocMetadataVo> metadata;
	protected Collection<LocSdgVo> sdgs;
	
	protected Collection<GenDataVo> generatorData;

	//--- Constructors --------------------------
	public DbLocationVo() {
	}

	public DbLocationVo(Integer cliId, Integer locId) {
		this.setPk(cliId, locId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) {
			return;
		}
		
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
				
			} else if (obj instanceof LocDataDefParameterVo) {
				((LocDataDefParameterVo) obj).setCliId(this.getCliId());
				((LocDataDefParameterVo) obj).setLocId(this.getLocId());
				((LocDataDefParameterVo) obj).setDataDefId(this.getDataDefId());
				
			} else if (obj instanceof LocMetadataVo) {
				((LocMetadataVo) obj).setCliId(this.getCliId());
				((LocMetadataVo) obj).setLocId(this.getLocId());
				
			} else if (obj instanceof LocSdgVo) {
				((LocSdgVo) obj).setCliId(this.getCliId());
				((LocSdgVo) obj).setLocId(this.getLocId());
			}
			
			if (obj instanceof ISynchronizable) {
				((ISynchronizable) obj).setChildrensId();
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.datas);
		this.setChildrensId(this.alerts);
		this.setChildrensId(this.estimations);
		this.setChildrensId(this.dataDefParameters);
		this.setChildrensId(this.metadata);
		this.setChildrensId(this.sdgs);
	}

	@Override public void synchronize(DbLocationVo dbVo) {
		this.setChildrensId();
		
		this.estimations		= BaseDbUtil.compareCollections(this.estimations,		(dbVo != null)?dbVo.estimations:null,			BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.dataDefParameters	= BaseDbUtil.compareCollections(this.dataDefParameters,	(dbVo != null)?dbVo.dataDefParameters:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.metadata			= BaseDbUtil.compareCollections(this.metadata,			(dbVo != null)?dbVo.metadata:null,				BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.sdgs				= BaseDbUtil.compareCollections(this.sdgs,				(dbVo != null)?dbVo.sdgs:null,					BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.estimations, syncType);
		BaseDbUtil.setAll(this.dataDefParameters, syncType);
		BaseDbUtil.setAll(this.metadata, syncType);
		BaseDbUtil.setAll(this.sdgs, syncType);
	}
	
	//--- Public methods ------------------------
	public void add(StationVo vo) {
		if (vo == null) {
			return;
		}
		if (this.stations == null) {
			this.stations = new ArrayList<>(1);
		}
		this.stations.add(vo);
	}
	
	public void add(GeneratorVo vo) {
		if (vo == null) {
			return;
		}
		if (this.generators == null) {
			this.generators = new ArrayList<>(1);
		}
		this.generators.add(vo);
	}
	
	public void add(LocDataVo vo) {
		if (vo == null) {
			return;
		}
		if (this.datas == null) {
			this.datas = new ArrayList<>(1);
		}
		this.datas.add(vo);
	}
	
	public void add(LocAlertVo vo) {
		if (vo == null) {
			return;
		}
		if (this.alerts == null) {
			this.alerts = new ArrayList<>(1);
		}
		this.alerts.add(vo);
	}

	public void add(LocEstimationVo vo) {
		if (vo == null) {
			return;
		}
		if (this.estimations == null) {
			this.estimations = new ArrayList<>(1);
		}
		this.estimations.add(vo);
	}
	
	public void add(LocDataDefParameterVo vo) {
		if (vo == null) {
			return;
		}
		if (this.dataDefParameters == null) {
			this.dataDefParameters = new ArrayList<>(1);
		}
		this.dataDefParameters.add(vo);
	}
	
	public void add(LocMetadataVo vo) {
		if (vo == null) {
			return;
		}
		if (this.metadata == null) {
			this.metadata = new ArrayList<>(1);
		}
		this.metadata.add(vo);
	}
	
	public void add(LocSdgVo vo) {
		if (vo == null) {
			return;
		}
		if (this.sdgs == null) {
			this.sdgs = new ArrayList<>(0);
		}
		this.sdgs.add(vo);
	}
	
	public void addGenerators(Collection<GeneratorVo> vos) {
		if (CollectionUtil.isEmpty(vos)) {
			return;
		}
		for (GeneratorVo vo : vos) {
			this.add(vo);
		}
	}
	
	public void addStations(Collection<StationVo> vos) {
		if (CollectionUtil.isEmpty(vos)) {
			return;
		}
		for (StationVo vo : vos) {
			this.add(vo);
		}
	}
	
	public void addEstimations(Collection<LocEstimationVo> vos) {
		if (CollectionUtil.isEmpty(vos)) {
			return;
		}
		for (LocEstimationVo vo : vos) {
			this.add(vo);
		}
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
	public Collection<LocDataDefParameterVo> getDataDefParameters() {
		return dataDefParameters;
	}
	public void setDataDefParameters(Collection<LocDataDefParameterVo> dataDefParameters) {
		this.dataDefParameters = dataDefParameters;
	}
	public Collection<LocMetadataVo> getMetadata() {
		return metadata;
	}
	public void setMetadata(Collection<LocMetadataVo> metadata) {
		this.metadata = metadata;
	}
	public CountryVo getCountryVo() {
		return countryVo;
	}
	public void setCountryVo(CountryVo countryVo) {
		this.countryVo = countryVo;
	}
	public Collection<LocSdgVo> getSdgs() {
		return sdgs;
	}
	public void setSdgs(Collection<LocSdgVo> sdgs) {
		this.sdgs = sdgs;
	}
	public LocTypeVo getLocTypeVo() {
		return locTypeVo;
	}
	public void setLocTypeVo(LocTypeVo locTypeVo) {
		this.locTypeVo = locTypeVo;
	}

	public Collection<GenDataVo> getGeneratorData() {
		return generatorData;
	}

	public void setGeneratorData(Collection<GenDataVo> generatorData) {
		this.generatorData = generatorData;
	}
	public FrequencyVo getFrequencyVo() {
		return frequencyVo;
	}
	public void setFrequencyVo(FrequencyVo frequencyVo) {
		this.frequencyVo = frequencyVo;
	}
}