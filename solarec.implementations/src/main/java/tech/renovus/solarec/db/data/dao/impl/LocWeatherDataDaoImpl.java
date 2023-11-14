package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocWeatherDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocWeatherDataDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocWeatherDataRowWrapper;
import tech.renovus.solarec.db.data.vo.LocWeatherDataVo;

@Repository
public class LocWeatherDataDaoImpl extends BaseLocWeatherDataDao implements LocWeatherDataDao {
	
	//--- Private properties --------------------
	protected final String SQL_FIND_LAST_FOR		= " SELECT * FROM loc_weather_data WHERE cli_id = :cli_id AND loc_id = :loc_id ORDER BY loc_wea_data_date DESC LIMIT 1";
	
	//--- Constructors --------------------------
	@Autowired public LocWeatherDataDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}

	//--- Overridden methods --------------------
	@Override
	public LocWeatherDataVo getLastFor(Integer cliId, Integer locId) {
		try {
			return 
				this.jdbc.queryForObject(
						SQL_FIND_LAST_FOR,
					new MapSqlParameterSource()
						.addValue("cli_id", cliId)
						.addValue("loc_id", locId),
						LocWeatherDataRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	} 
}
