package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseLocDataWeatherDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataWeatherDao;
import tech.renovus.solarec.db.data.dao.wrapper.LocDataWeatherRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataWeatherVo;

@Repository
public class LocDataWeatherDaoImpl extends BaseLocDataWeatherDao implements LocDataWeatherDao {
	
	//--- Private properties --------------------
	private final String SQL_SELECT_ALL_FOR_PERIOD		= "select a.* from loc_data_weather a where a.cli_id = :cli_id and a.loc_id = :loc_id and :from <= data_date and data_date < :to "
			+ "and a.data_date_added = (select max(l.data_date_added) from loc_data_weather l where l.cli_id = :cli_id and l.loc_id = :loc_id and :from <= l.data_date and l.data_date < :to ) "
			+ "order by data_date asc";
	private final String SQL_SELECT_ALL_FOR_TYPE_FOR_PERIOD		= "select a.* from loc_data_weather a where a.cli_id = :cli_id and a.loc_id = :loc_id and data_type_id = :data_type_id and :from <= data_date and data_date < :to "
			+ "and a.data_date_added = (select max(l.data_date_added) from loc_data_weather l where l.cli_id = :cli_id and l.loc_id = :loc_id and :from <= l.data_date and l.data_date < :to ) "
			+ "order by data_date asc";
	
	//--- Constructors --------------------------
	@Autowired public LocDataWeatherDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
		
		this.SQL_INSERT += this.SQL_ON_CONFLICT_PK_UPDATE;
	}  
	
	//--- Overridden methods --------------------
	@Override
	public Collection<LocDataWeatherVo> getForPeriod(Integer cliId, Integer locId, Date from, Date to) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_PERIOD,
				new MapSqlParameterSource()
					.addValue("cli_id", cliId)
					.addValue("loc_id", locId)
					.addValue("from", from)
					.addValue("to", to),
					LocDataWeatherRowWrapper.getInstance()
			);
	} 
	
	@Override
	public Collection<LocDataWeatherVo> getForPeriod(Integer cliId, Integer locId, Integer dataTypeId,  Date from, Date to) {
		return this.jdbc.query(
				SQL_SELECT_ALL_FOR_TYPE_FOR_PERIOD,
				new MapSqlParameterSource()
				.addValue("cli_id", cliId)
				.addValue("loc_id", locId)
				.addValue("data_type_id", dataTypeId)
				.addValue("from", from)
				.addValue("to", to),
				LocDataWeatherRowWrapper.getInstance()
				);
	}  
}
