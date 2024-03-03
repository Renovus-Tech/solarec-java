package tech.renovus.solarec.db.data.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseEmberCountryOverviewDao;
import tech.renovus.solarec.db.data.dao.interfaces.EmberCountryOverviewDao;
import tech.renovus.solarec.db.data.dao.wrapper.EmberCountryOverviewRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.custom.CliSettingAndSettingsRowWrapper;
import tech.renovus.solarec.vo.db.data.EmberCountryOverviewVo;

@Repository
public class EmberCountryOverviewDaoImpl extends BaseEmberCountryOverviewDao implements EmberCountryOverviewDao {
	
	//--- Private properties --------------------
	protected final String SQL_FIND_LAST_FROM		= "SELECT * FROM ember_country_overview WHERE country_or_region = :country_or_region AND year <= :year ORDER BY year DESC LIMIT :limit";
	protected final String SQL_FIND_FIRST_FROM		= "SELECT * FROM ember_country_overview WHERE country_or_region = :country_or_region AND year >= :year ORDER BY year ASC LIMIT :limit";
	
	//--- Constructors --------------------------
	@Autowired public EmberCountryOverviewDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	@Override public EmberCountryOverviewVo findFirstFrom(String countryOrRegion, Integer year) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_FIRST_FROM, 
					new MapSqlParameterSource()
						.addValue("country_or_region", countryOrRegion)
						.addValue("year", year)
						.addValue("limit", Integer.valueOf(1)),
					EmberCountryOverviewRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override public EmberCountryOverviewVo findLastFrom(String countryOrRegion, Integer year) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_LAST_FROM, 
					new MapSqlParameterSource()
						.addValue("country_or_region", countryOrRegion)
						.addValue("year", year)
						.addValue("limit", Integer.valueOf(1)),
					EmberCountryOverviewRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override public Collection<EmberCountryOverviewVo> findAllFirstFrom(String countryOrRegion, Integer year) {
		return this.jdbc.query(
				SQL_FIND_FIRST_FROM,
				new MapSqlParameterSource()
					.addValue("country_or_region", countryOrRegion)
					.addValue("year", year)
					.addValue("limit", Integer.valueOf(3)),
				EmberCountryOverviewRowWrapper.getInstance()
			);
	}

	@Override public Collection<EmberCountryOverviewVo> findAllLatFrom(String countryOrRegion, Integer year) {
		return this.jdbc.query(
				SQL_FIND_LAST_FROM,
				new MapSqlParameterSource()
					.addValue("country_or_region", countryOrRegion)
					.addValue("year", year)
					.addValue("limit", Integer.valueOf(3)),
				EmberCountryOverviewRowWrapper.getInstance()
			);
	}
}
