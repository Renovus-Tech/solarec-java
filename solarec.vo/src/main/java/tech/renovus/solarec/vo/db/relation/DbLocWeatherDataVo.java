package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseLocWeatherDataVo;
import tech.renovus.solarec.vo.db.data.LocWeaDataHourVo;

public class DbLocWeatherDataVo extends BaseLocWeatherDataVo implements ISynchronizable<DbLocWeatherDataVo> {

	//--- Private properties --------------------
	private Collection<LocWeaDataHourVo> hours;
	
	//--- Constructors --------------------------
	public DbLocWeatherDataVo() {
	}

	public DbLocWeatherDataVo(Integer cliId, Integer locId, Integer locWeaDataId) {
		this.setPk(cliId, locId, locWeaDataId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof LocWeaDataHourVo) {
				((LocWeaDataHourVo) obj).setCliId(this.getCliId());
				((LocWeaDataHourVo) obj).setLocId(this.getLocId());
				((LocWeaDataHourVo) obj).setLocWeaDataId(this.getLocWeaDataId());
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.hours);
	}

	@Override public void synchronize(DbLocWeatherDataVo dbVo) {
		this.setChildrensId();
		
		this.hours	= BaseDbUtil.compareCollections(this.hours,	(dbVo != null)?dbVo.hours:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.hours, syncType);
	}

	//--- Public methods ------------------------
	public void add(LocWeaDataHourVo vo) {
		if (this.hours == null) this.hours = new ArrayList<>(1);
		this.hours.add(vo);
	}
	
	//--- Getters and Setters -------------------
	public Collection<LocWeaDataHourVo> getHours() {
		return hours;
	}

	public void setHours(Collection<LocWeaDataHourVo> hours) {
		this.hours = hours;
	}

}