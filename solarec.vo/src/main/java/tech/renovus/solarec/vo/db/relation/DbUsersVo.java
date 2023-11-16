package tech.renovus.solarec.vo.db.relation;

import java.util.Collection;

import tech.renovus.solarec.util.interfaces.ISynchronizable;
import tech.renovus.solarec.vo.db.base.BaseUsersVo;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;
import tech.renvous.solarec.util.CollectionUtil;
import tech.renvous.solarec.util.db.BaseDbUtil;
import tech.renvous.solarec.util.db.BaseDbVo;

public class DbUsersVo extends BaseUsersVo implements ISynchronizable<DbUsersVo> {

	//--- Private properties --------------------
	private Collection<UsrSettingVo> settings;
	
	//--- Constructors --------------------------
	public DbUsersVo() {
	}

	public DbUsersVo(Integer usrId) {
		this.setPk(usrId);
	}

	//--- Private methods -----------------------
	private void setChildrensId(Collection<? extends BaseDbVo> col) {
		if (CollectionUtil.isEmpty(col)) return;
		
		for (BaseDbVo obj : col) {
			if (obj instanceof UsrSettingVo) {
				((UsrSettingVo) obj).setUsrId(this.getUsrId());
			}
		}
	}

	//--- Implemented methods ISynchronizable ---
	@Override public void setChildrensId() {
		this.setChildrensId(this.settings);
	}

	@Override public void synchronize(DbUsersVo dbVo) {
		this.setChildrensId();
		
		this.settings	= BaseDbUtil.compareCollections(this.settings,	(dbVo != null)?dbVo.settings:null,		BaseDbVo.SYNC_INSERT, BaseDbVo.SYNC_DELETE);
	}

	@Override public void synchronizeForce(int syncType) {
		BaseDbUtil.setAll(this.settings, syncType);
	}

	public Collection<UsrSettingVo> getSettings() {
		return settings;
	}

	public void setSettings(Collection<UsrSettingVo> settings) {
		this.settings = settings;
	}

}