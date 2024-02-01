package tech.renovus.solarec.db.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tech.renovus.solarec.db.data.dao.base.BaseEmberCountryOverviewDao;
import tech.renovus.solarec.db.data.dao.interfaces.EmberCountryOverviewDao;
import tech.renovus.solarec.db.data.dao.wrapper.EmberCountryOverviewRowWrapper;
import tech.renovus.solarec.vo.db.data.EmberCountryOverviewVo;

@Repository
public class EmberCountryOverviewDaoImpl extends BaseEmberCountryOverviewDao implements EmberCountryOverviewDao {
	
	//--- Private properties --------------------
	protected final String SQL_FIND_LAST_FROM		= "SELECT * FROM ember_country_overview WHERE country_or_region = :country_or_region AND year <= :year ORDER BY year DESC LIMIT 1";
	protected final String SQL_FIND_FIRST_FROM		= "SELECT * FROM ember_country_overview WHERE country_or_region = :country_or_region AND year >= :year ORDER BY year ASC LIMIT 1";
	
	//--- Constructors --------------------------
	@Autowired public EmberCountryOverviewDaoImpl(NamedParameterJdbcTemplate jdbc) {
		super(jdbc);
	}  
	
	//--- Overridden methods --------------------
	public EmberCountryOverviewVo findFirstFrom(String countryOrRegion, Integer year) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_FIRST_FROM, 
					new MapSqlParameterSource()
						.addValue("country_or_region", countryOrRegion)
						.addValue("year", year),
					EmberCountryOverviewRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public EmberCountryOverviewVo findLastFrom(String countryOrRegion, Integer year) {
		try {
			return this.jdbc.queryForObject(
					SQL_FIND_LAST_FROM, 
					new MapSqlParameterSource()
						.addValue("country_or_region", countryOrRegion)
						.addValue("year", year),
					EmberCountryOverviewRowWrapper.getInstance());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
