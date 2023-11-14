package tech.renovus.solarec.db.data.vo.db;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.db.data.vo.CliSettingVo;
import tech.renovus.solarec.db.data.vo.DataDefinitionVo;
import tech.renovus.solarec.db.data.vo.LocationVo;
import tech.renovus.solarec.db.data.vo.base.BaseClientVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbClientVo extends BaseClientVo implements ISynchronizable<DbClientVo> {

	//--- Private properties --------------------
	private Collection<CliSettingVo> settings;
	protected DataDefinitionVo dataDefinitionVo;
	private Collection<LocationVo> locations;
	
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
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.settings);
	}

	@Override public void synchronize(DbClientVo dbVo) {
		this.setChildrensId();
		
		this.settings	= BaseDbUtil.compareCollections(this.settings,	(dbVo != null)?dbVo.settings:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.settings, syncType);
	}
	
	//--- Public methods ------------------------
	public void add(LocationVo vo) {
		if (vo == null) return;
		if (this.locations == null) this.locations = new ArrayList<>(1);
		this.locations.add(vo);
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
}