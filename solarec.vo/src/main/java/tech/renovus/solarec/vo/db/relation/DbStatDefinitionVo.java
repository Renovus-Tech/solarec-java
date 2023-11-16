package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseStatDefinitionVo;
import tech.renovus.solarec.vo.db.data.StatDefResultVo;
import tech.renovus.solarec.vo.db.data.StatDefSourceVo;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbStatDefinitionVo extends BaseStatDefinitionVo implements ISynchronizable<DbStatDefinitionVo> {

	//--- Private properties --------------------
	private Collection<StatDefSourceVo> sources;
	private Collection<StatDefResultVo> results;
	
	//--- Constructors --------------------------
	public DbStatDefinitionVo() {
	}

	public DbStatDefinitionVo(Integer statDefId) {
		this.setPk(statDefId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof StatDefSourceVo) ((StatDefSourceVo) obj).setStatDefId(this.getStatDefId());
			else if (obj instanceof StatDefResultVo) ((StatDefResultVo) obj).setStatDefId(this.getStatDefId());
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.sources);
		this.setChildrensId(this.results);
	}

	@Override public void synchronize(DbStatDefinitionVo dbVo) {
		this.setChildrensId();
		
		this.sources	= BaseDbUtil.compareCollections(this.sources,	(dbVo != null)?dbVo.sources:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
		this.results	= BaseDbUtil.compareCollections(this.results,	(dbVo != null)?dbVo.results:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.sources, syncType);
		BaseDbUtil.setAll(this.results, syncType);
	}

	//--- Getters and Setters -------------------
	public Collection<StatDefSourceVo> getSources() {
		return sources;
	}

	public void setSources(Collection<StatDefSourceVo> sources) {
		this.sources = sources;
	}

	public Collection<StatDefResultVo> getResults() {
		return results;
	}

	public void setResults(Collection<StatDefResultVo> results) {
		this.results = results;
	}

}