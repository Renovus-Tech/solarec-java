package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocGenAlarmDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocGenAlarmDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocGenAlarmRowWrapper;
import tech.renovus.solarec.db.data.vo.LocGenAlarmVo;

@Repository
public class LocGenAlarmDaoImpl extends BaseLocGenAlarmDao implements LocGenAlarmDao {
	
	//--- Private properties --------------------
	private final static String SQL_FIND_ALL_FOR_CLIENT_LOCATION				= "SELECT * FROM loc_gen_alarm WHERE cli_id = :cliId AND loc_id = :locId";
	
	//--- Constructors --------------------------
	@Autowired public LocGenAlarmDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<LocGenAlarmVo> findAll(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_FIND_ALL_FOR_CLIENT_LOCATION,
				new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("locId", locId),
				LocGenAlarmRowWrapper.getInstance()
			);
	}  

}
