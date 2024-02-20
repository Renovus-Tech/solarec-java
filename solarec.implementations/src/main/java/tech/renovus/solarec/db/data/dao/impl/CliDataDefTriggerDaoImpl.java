package tech.renovus.solarec.db.data.dao.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliDataDefTriggerDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefTriggerDao;
import tech.renovus.solarec.db.data.dao.wrapper.custom.CliDataDefTriggerFullRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;

@Repository
public class CliDataDefTriggerDaoImpl extends BaseCliDataDefTriggerDao implements CliDataDefTriggerDao {
	
	//--- Private properties --------------------
	protected final String SQL_SELECT_JOINS							= "select * from cli_data_def_trigger cddt left join data_definition dd on cddt.data_def_id = dd.data_def_id_auto left join client c on cddt.cli_id = c.cli_id_auto left outer join location l on l.cli_id = cddt.cli_id and l.loc_id_auto = cddt.loc_id left outer join generator g on g.cli_id = cddt.cli_id and g.gen_id_auto = cddt.gen_id left outer join station s on s.cli_id = cddt.cli_id and s.sta_id_auto = cddt.sta_id";
	protected final String SQL_SELECT_FULL_BY_ID					= SQL_SELECT_JOINS + " WHERE cddt.tri_id_auto = :tri_id_auto";
	protected final String SQL_SELECT_FULL_BY_SOURCE				= SQL_SELECT_JOINS + " WHERE cddt.tri_source = :tri_source";
	protected final String SQL_SELECT_FULL_BY_SOURCE_ENABLED		= SQL_SELECT_JOINS + " WHERE cddt.tri_source = :tri_source AND cddt.tri_flags like '1%'";
	protected final String SQL_SELECT_FULL_BY_SOURCE_ENABLED_DEV	= SQL_SELECT_JOINS + " WHERE cddt.tri_source = :tri_source AND cddt.tri_flags like '_1%'";
	protected final String SQL_SELECT_FULL_BY_SOURCE_VALUE			= SQL_SELECT_JOINS + " WHERE cddt.tri_source = :tri_source AND cddt.tri_value = :tri_value";
	protected final String SQL_SELECT_FULL_ALL_FOR_CLIENT			= SQL_SELECT_JOINS + " WHERE cddt.cli_id = :cli_id";

	@Autowired private Environment environment;
	
	//--- Constructors --------------------------
	@Autowired public CliDataDefTriggerDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public CliDataDefTriggerVo findFullVo(Integer triId) {
		try {
			return 
				this.jdbc.queryForObject(
					SQL_SELECT_FULL_BY_ID,
					new MapSqlParameterSource()
						.addValue("tri_id_auto", triId),
					CliDataDefTriggerFullRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public CliDataDefTriggerVo findFullVo(Integer triSource, String triValue) {
		try {
			return 
				this.jdbc.queryForObject(
					SQL_SELECT_FULL_BY_SOURCE_VALUE,
					new MapSqlParameterSource()
						.addValue("tri_source", triSource)
						.addValue("tri_value", triValue),
					CliDataDefTriggerFullRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public Collection<CliDataDefTriggerVo> findAllFor(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_FULL_ALL_FOR_CLIENT, 
				new MapSqlParameterSource().addValue("cli_id", cliId),
				CliDataDefTriggerFullRowWrapper.getInstance()
			);
	}

	@Override
	public Collection<CliDataDefTriggerVo> getAllForSource(Integer triSource, boolean forceEnabled) {
		boolean atDev	= Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals);
		String sql		= atDev ? SQL_SELECT_FULL_BY_SOURCE_ENABLED_DEV : SQL_SELECT_FULL_BY_SOURCE_ENABLED;
		
		return 
			this.jdbc.query(
					forceEnabled ? sql : SQL_SELECT_FULL_BY_SOURCE,
				new MapSqlParameterSource()
					.addValue("tri_source", triSource),
				CliDataDefTriggerFullRowWrapper.getInstance()
			);
	}
}
