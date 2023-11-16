package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseCliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public class DbCliDataDefTriggerVo extends BaseCliDataDefTriggerVo implements ISynchronizable<DbCliDataDefTriggerVo> {

	//--- Private properties --------------------
	private ClientVo clientVo;
	private LocationVo locationVo;
	private GeneratorVo generatorVo;
	private StationVo stationVo;
	private DataDefinitionVo dataDefinitionVo;
	
	//--- Constructors --------------------------
	public DbCliDataDefTriggerVo() {
	}

	public DbCliDataDefTriggerVo(Integer triId) {
		this.setPk(triId);
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

	@Override public void synchronize(DbCliDataDefTriggerVo dbVo) {
		this.setChildrensId();
		
		//this.xxxx	= BaseDbUtil.compareCollections(this.xxxx,	(dbVo != null)?dbVo.xxxx:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		//BaseDbUtil.setAll(this.xxxx, syncType);
	}

	//--- Getters and Setters -------------------
	public ClientVo getClientVo() {
		return clientVo;
	}

	public void setClientVo(ClientVo clientVo) {
		this.clientVo = clientVo;
	}

	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}

	public GeneratorVo getGeneratorVo() {
		return generatorVo;
	}

	public void setGeneratorVo(GeneratorVo generatorVo) {
		this.generatorVo = generatorVo;
	}

	public StationVo getStationVo() {
		return stationVo;
	}

	public void setStationVo(StationVo stationcVo) {
		this.stationVo = stationcVo;
	}

	public DataDefinitionVo getDataDefinitionVo() {
		return dataDefinitionVo;
	}

	public void setDataDefinitionVo(DataDefinitionVo dataDefinitionVo) {
		this.dataDefinitionVo = dataDefinitionVo;
	}

}