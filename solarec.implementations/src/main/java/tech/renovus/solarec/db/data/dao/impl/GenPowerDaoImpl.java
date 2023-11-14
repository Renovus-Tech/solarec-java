package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseGenPowerDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenPowerDao;
import tech.renovus.solarec.db.data.dao.wrapper.GenPowerRowWrapper;
import tech.renovus.solarec.db.data.vo.GenPowerVo;

@Repository
public class GenPowerDaoImpl extends BaseGenPowerDao implements GenPowerDao {
	
	//--- Private properties --------------------
	private final static String SQL_SELECT_ALL_FOR_GENERATOR		= "SELECT * FROM gen_power WHERE cli_id = :cliId AND gen_id = :genId ORDER BY pwr_wind_speed, pwr_air_density";
	
	//--- Constructors --------------------------
	@Autowired public GenPowerDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override public Collection<GenPowerVo> getAllFor(Integer cliId, Integer genId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_GENERATOR, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("genId", genId),
				GenPowerRowWrapper.getInstance()
			);
	}  


}
