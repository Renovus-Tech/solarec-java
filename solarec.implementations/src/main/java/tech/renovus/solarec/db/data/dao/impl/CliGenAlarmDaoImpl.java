package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseCliGenAlarmDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlarmDao;
import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlarmRowWrapper;
import tech.renovus.solarec.db.data.vo.CliGenAlarmVo;

@Repository
public class CliGenAlarmDaoImpl extends BaseCliGenAlarmDao implements CliGenAlarmDao {
	
	//--- Private constants ---------------------
	private final static String SQL_FIND_ALL_FOR_CLIENT				= "SELECT * FROM cli_gen_alarm WHERE cli_id = :cliId ";
	
	//--- Constructors --------------------------
	@Autowired public CliGenAlarmDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<CliGenAlarmVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_CLIENT,
				new MapSqlParameterSource()
					.addValue("cliId", cliId),
				CliGenAlarmRowWrapper.getInstance()
			);
	}  
	

}
