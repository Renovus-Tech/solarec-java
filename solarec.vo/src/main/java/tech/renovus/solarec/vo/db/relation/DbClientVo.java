package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseClientVo;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.CliMetadataVo;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

public class DbClientVo extends BaseClientVo implements ISynchronizable<DbClientVo> {

	//--- Private properties --------------------
	protected Collection<CliSettingVo> settings;
	protected DataDefinitionVo dataDefinitionVo;
	protected Collection<LocationVo> locations;
	protected Collection<CliDataDefParameterVo> dataDefParameters;
	protected Collection<CliMetadataVo> metadata;
	protected CountryVo countryVo;
	
	//--- Constructors --------------------------
	public DbClientVo() {
	}

	public DbClientVo(Integer cliId) {
		this.setPk(cliId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof CliSettingVo) {
				((CliSettingVo) obj).setCliId(this.getCliId());

			} else if (obj instanceof CliDataDefParameterVo) {
				((CliDataDefParameterVo) obj).setCliId(this.getCliId());
				((CliDataDefParameterVo) obj).setDataDefId(this.getDataDefId());
			
			} else if (obj instanceof CliMetadataVo) {
				((CliMetadataVo) obj).setCliId(this.getCliId());
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.settings);
		this.setChildrensId(this.dataDefParameters);
		this.setChildrensId(this.metadata);
	}

	@Override public void synchronize(DbClientVo dbVo) {
		this.setChildrensId();
		
		this.settings			= BaseDbUtil.compareCollections(this.settings,			(dbVo != null)?dbVo.settings:null,				BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.dataDefParameters	= BaseDbUtil.compareCollections(this.dataDefParameters,	(dbVo != null)?dbVo.dataDefParameters:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.metadata			= BaseDbUtil.compareCollections(this.metadata,			(dbVo != null)?dbVo.metadata:null,				BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.settings, syncType);
		BaseDbUtil.setAll(this.dataDefParameters, syncType);
		BaseDbUtil.setAll(this.metadata, syncType);
	}
	
	//--- Public methods ------------------------
	public void add(LocationVo vo) {
		if (vo == null) return;
		if (this.locations == null) this.locations = new ArrayList<>(1);
		this.locations.add(vo);
	}

	public void add(CliDataDefParameterVo vo) {
		if (vo == null) return;
		if (this.dataDefParameters == null) this.dataDefParameters = new ArrayList<>(1);
		this.dataDefParameters.add(vo);
	}
	
	public void add(CliMetadataVo vo) {
		if (vo == null) return;
		if (this.metadata == null) this.metadata = new ArrayList<>(1);
		this.metadata.add(vo);
	}
	
	//--- Public methods ------------------------
	public Collection<LocationVo> getLocations() {
		return locations;
	}
	public void setLocations(Collection<LocationVo> locations) {
		this.locations = locations;
	}

	public DataDefinitionVo getDataDefinitionVo() {
		return dataDefinitionVo;
	}

	public void setDataDefinitionVo(DataDefinitionVo dataDefinitionVo) {
		this.dataDefinitionVo = dataDefinitionVo;
	}

	public Collection<CliSettingVo> getSettings() {
		return settings;
	}

	public void setSettings(Collection<CliSettingVo> settings) {
		this.settings = settings;
	}

	public Collection<CliDataDefParameterVo> getDataDefParameters() {
		return dataDefParameters;
	}

	public void setDataDefParameters(Collection<CliDataDefParameterVo> dataDefParameters) {
		this.dataDefParameters = dataDefParameters;
	}

	public Collection<CliMetadataVo> getMetadata() {
		return metadata;
	}

	public void setMetadata(Collection<CliMetadataVo> metadata) {
		this.metadata = metadata;
	}

	public CountryVo getCountryVo() {
		return countryVo;
	}

	public void setCountryVo(CountryVo countryVo) {
		this.countryVo = countryVo;
	}
}