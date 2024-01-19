package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseUsrSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsrSettingDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.UsrSettingAndSettingsRowWrapper;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;

@Repository
public class UsrSettingDaoImpl extends BaseUsrSettingDao implements UsrSettingDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_ALL_FOR_USER		= "SELECT * FROM vw_usr_setting WHERE usr_id = :usr_id";
	protected final String SQL_DELETE_ALL_FOR_USER		= "DELETE FROM usr_setting WHERE usr_id = :usr_id";
	
	//--- Constructors --------------------------
	@Autowired public UsrSettingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<UsrSettingVo> findAllFor(Integer usrId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_USER,
				new MapSqlParameterSource()
					.addValue("usr_id", usrId),
				UsrSettingAndSettingsRowWrapper.getInstance()
			);
	}

	@Override
	public void deleteAllFor(Integer usrId) {
		 this.jdbc.update(SQL_DELETE_ALL_FOR_USER, new MapSqlParameterSource().addValue("usr_id", usrId)); 
	}  
}
