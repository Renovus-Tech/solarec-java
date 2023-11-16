package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenAlarmDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenAlarmDao;
import tech.renovus.solarec.db.data.dao.wrapper.GenAlarmRowWrapper;
import tech.renovus.solarec.vo.db.data.GenAlarmVo;

@Repository
public class GenAlarmDaoImpl extends BaseGenAlarmDao implements GenAlarmDao {
	
	//--- Private constants ---------------------
	private final static String SQL_FIND_ALL_FOR_CLIENT				= "SELECT * FROM gen_alarm WHERE cli_id = :cliId ";
	
	//--- Constructors --------------------------
	@Autowired public GenAlarmDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<GenAlarmVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_CLIENT,
				new MapSqlParameterSource()
					.addValue("cliId", cliId),
				GenAlarmRowWrapper.getInstance()
			);
	}  
}
