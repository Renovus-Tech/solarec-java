package tech.renovus.solarec.db.data.vo.db;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataDefinitionVo;
import tech.renovus.solarec.db.data.vo.GenAlertVo;
import tech.renovus.solarec.db.data.vo.GenDataVo;
import tech.renovus.solarec.db.data.vo.GenNeighbourVo;
import tech.renovus.solarec.db.data.vo.GenPowerVo;
import tech.renovus.solarec.db.data.vo.base.BaseGeneratorVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbGeneratorVo extends BaseGeneratorVo implements ISynchronizable<DbGeneratorVo> {

	//--- Protected properties ------------------
	protected DataDefinitionVo dataDefinitionVo;
	protected Collection<GenPowerVo> powerCurve;
	
	protected Collection<GenDataVo> datas;
	protected Collection<GenAlertVo> alerts;
	protected Collection<GenNeighbourVo> neighbours;
	
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
	}

	@Override public void synchronize(DbGeneratorVo dbVo) {
		this.setChildrensId();
		
		this.powerCurve	= BaseDbUtil.compareCollections(this.powerCurve,	(dbVo != null)?dbVo.powerCurve:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.neighbours	= BaseDbUtil.compareCollections(this.neighbours,	(dbVo != null)?dbVo.neighbours:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.powerCurve, syncType);
		BaseDbUtil.setAll(this.neighbours, syncType);
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

}