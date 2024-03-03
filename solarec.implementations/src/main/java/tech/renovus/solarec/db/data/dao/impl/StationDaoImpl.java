package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseStationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.db.data.dao.wrapper.StationRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.UsersRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.custom.GeneratorMaxDataDate;
import tech.renovus.solarec.vo.db.data.StationVo;

@Repository
public class StationDaoImpl extends BaseStationDao implements StationDao {
	
	//--- Private constants ---------------------
	private final static String SQL_SELECT_ALL_FOR_CLIENT		= "SELECT * FROM station WHERE cli_id = :cliId ";
	private final static String SQL_SELECT_ALL_FOR_LOCATION		= "SELECT * FROM station WHERE cli_id = :cliId AND loc_id = :locId";
	private final static String SQL_FIND_DEFAULT				= "SELECT * FROM station WHERE cli_id = :cliId AND loc_id = :locId AND sta_flags like '1%'";

	private final static String SQL_UPDATE_DATA_DATE_MAX 		= "update station set sta_data_date_max = (select max(data_date) from sta_data where sta_data.cli_id = station.cli_id and sta_data.sta_id = station.sta_id_auto)";
	private final static String SQL_UPDATE_DATA_DATE_MIN 		= "update station set sta_data_date_min = (select min(data_date) from sta_data where sta_data.cli_id = station.cli_id and sta_data.sta_id = station.sta_id_auto)";
	
	private final static String SQL_GET_MAX_DATA_DATE_FOR_CLIENT			= "SELECT max(sta_data_date_max) FROM station WHERE cli_id = :cliId AND sta_data_date_max <= :staDataDateMax" ;

	//--- Constructors --------------------------
	@Autowired public StationDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public Collection<StationVo> findAll(Integer cliId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_CLIENT, 
				new MapSqlParameterSource()
					.addValue("cliId", cliId),
				StationRowWrapper.getInstance()
			);
	}
	
	@Override public Collection<StationVo> findAll(Integer cliId, Integer locId) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_LOCATION, 
				new MapSqlParameterSource()
				.addValue("cliId", cliId)
				.addValue("locId", locId),
				StationRowWrapper.getInstance()
			);
	}
	
	@Override public StationVo findDefault(Integer cliId, Integer locId) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_DEFAULT, 
					new MapSqlParameterSource()
						.addValue("cliId", cliId)
						.addValue("locId", locId),
					StationRowWrapper.getInstance()
				);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public void updateDataDateMaxMin() {
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MAX, new MapSqlParameterSource() );
		this.jdbc.update( SQL_UPDATE_DATA_DATE_MIN, new MapSqlParameterSource() );
	}
	
	@Override public Date getMaxDataDate(Integer cliId, Date beforeDate) {
		try { 
			return this.jdbc.queryForObject(
					SQL_GET_MAX_DATA_DATE_FOR_CLIENT, 
					new MapSqlParameterSource()
					.addValue("cliId", cliId)
					.addValue("staDataDateMax", beforeDate), 
					GeneratorMaxDataDate.getInstance()
					);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
