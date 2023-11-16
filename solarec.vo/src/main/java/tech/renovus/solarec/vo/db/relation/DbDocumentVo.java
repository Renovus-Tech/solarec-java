package tech.renovus.solarec.vo.db.relation;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseDocumentVo;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;
import tech.renovus.solarec.vo.db.data.DocLocationVo;
import tech.renovus.solarec.vo.db.data.DocStationVo;
import tech.renovus.solarec.vo.db.data.DocTypeVo;

public class DbDocumentVo extends BaseDocumentVo implements ISynchronizable<DbDocumentVo> {

	//--- Protected properties ------------------
	protected Collection<DocLocationVo> locations;
	protected Collection<DocGeneratorVo> generators;
	protected Collection<DocStationVo> stations;
	
	protected DocTypeVo docTypeVo;
	
	//--- Constructors --------------------------
	public DbDocumentVo() {
	}

	public DbDocumentVo(Integer cliId, Integer docId) {
		this.setPk(cliId, docId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof DocLocationVo) {
				((DocLocationVo) obj).setCliId(this.getCliId());
				((DocLocationVo) obj).setDocId(this.getDocId());
			
			} else if (obj instanceof DocGeneratorVo) {
				((DocGeneratorVo) obj).setCliId(this.getCliId());
				((DocGeneratorVo) obj).setDocId(this.getDocId());
			
			} else if (obj instanceof DocStationVo) {
				((DocStationVo) obj).setCliId(this.getCliId());
				((DocStationVo) obj).setDocId(this.getDocId());
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.locations);
		this.setChildrensId(this.generators);
		this.setChildrensId(this.stations);
	}

	@Override public void synchronize(DbDocumentVo dbVo) {
		this.setChildrensId();
		
		this.locations	= BaseDbUtil.compareCollections(this.locations,		(dbVo != null)?dbVo.locations:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.generators	= BaseDbUtil.compareCollections(this.generators,	(dbVo != null)?dbVo.generators:null,	BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.stations	= BaseDbUtil.compareCollections(this.stations,		(dbVo != null)?dbVo.stations:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.locations, syncType);
		BaseDbUtil.setAll(this.generators, syncType);
		BaseDbUtil.setAll(this.stations, syncType);
	}

	//--- Public methods ------------------------
	public void add(DocGeneratorVo aVo) {
		if (aVo == null) return;
		if (this.generators == null) this.generators = new ArrayList<>();
		this.generators.add(aVo);
	}
	
	public void add(DocLocationVo aVo) {
		if (aVo == null) return;
		if (this.locations == null) this.locations = new ArrayList<>();
		this.locations.add(aVo);
	}

	public void add(DocStationVo aVo) {
		if (aVo == null) return;
		if (this.stations == null) this.stations = new ArrayList<>();
		this.stations.add(aVo);
	}
	
	//--- Getters and Setters -------------------
	public Collection<DocLocationVo> getLocations() {
		return locations;
	}
	public void setLocations(Collection<DocLocationVo> locations) {
		this.locations = locations;
	}
	public Collection<DocGeneratorVo> getGenerators() {
		return generators;
	}
	public void setGenerators(Collection<DocGeneratorVo> generators) {
		this.generators = generators;
	}
	public Collection<DocStationVo> getStations() {
		return stations;
	}
	public void setStations(Collection<DocStationVo> stations) {
		this.stations = stations;
	}

	public DocTypeVo getDocTypeVo() {
		return docTypeVo;
	}

	public void setDocTypeVo(DocTypeVo docTypeVo) {
		this.docTypeVo = docTypeVo;
	}
}