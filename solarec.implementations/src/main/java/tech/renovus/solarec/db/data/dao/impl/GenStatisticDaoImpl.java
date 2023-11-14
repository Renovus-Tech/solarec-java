package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.interfaces.GenStatisticDao;
import tech.renovus.solarec.db.data.dao.base.BaseGenStatisticDao;

@Repository
public class GenStatisticDaoImpl extends BaseGenStatisticDao implements GenStatisticDao {
	
	//--- Private properties --------------------
	private final String SQL_DELETE_ALL_FOR_GENERATOR			= "DELETE FROM gen_statistic WHERE cli_id = :cli_id AND gen_id = :gen_id";
	
	//--- Constructors --------------------------
	@Autowired public GenStatisticDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public void deleteAllForGenerator(Integer cliId, Integer genId) {
		this.jdbc.update(SQL_DELETE_ALL_FOR_GENERATOR,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("gen_id", genId)
			);
	}
}
