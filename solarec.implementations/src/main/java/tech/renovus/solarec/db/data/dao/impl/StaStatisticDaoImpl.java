package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.StaStatisticDao;
import tech.renovus.solarec.db.data.dao.base.BaseStaStatisticDao;

@Repository
public class StaStatisticDaoImpl extends BaseStaStatisticDao implements StaStatisticDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_STATION			= "DELETE FROM sta_statistic WHERE cli_id = :cli_id AND sta_id = :sta_id";
	
	//--- Constructors --------------------------
	@Autowired public StaStatisticDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void deleteAllForStation(Integer cliId, Integer staId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_STATION,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("sta_id", staId)
			);
	}
}
