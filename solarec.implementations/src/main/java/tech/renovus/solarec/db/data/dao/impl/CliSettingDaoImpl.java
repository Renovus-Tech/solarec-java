package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.wrapper.CliSettingRowWrapper;
import tech.renovus.solarec.db.data.vo.CliSettingVo;

@Repository
public class CliSettingDaoImpl extends BaseCliSettingDao implements CliSettingDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_ALL_FOR_USER		= "SELECT * FROM cli_setting WHERE cli_id = :cli_id";
	protected final String SQL_DELETE_ALL_FOR_USER		= "DELETE FROM cli_setting WHERE cli_id = :cli_id";
	
	//--- Constructors --------------------------
	@Autowired public CliSettingDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public Collection<CliSettingVo> findAllFor(Integer usrId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_USER,
				new MapSqlParameterSource()
					.addValue("cli_id", usrId),
				CliSettingRowWrapper.getInstance()
			);
	}

	@Override
	public void deleteAllFor(Integer usrId) {
		 this.jdbc.update(SQL_DELETE_ALL_FOR_USER, new MapSqlParameterSource().addValue("cli_id", usrId)); 
	}  

	@Override
	public CliSettingVo findVoOrDefault(Integer cliId, String cliSetName, String defaultValue) {
		CliSettingVo vo = this.findVo(cliId, cliSetName);
		return vo != null ? vo : new CliSettingVo(cliId, cliSetName, defaultValue);
	}
}
