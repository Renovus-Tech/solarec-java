package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.EmberCountryOverviewRowWrapper;
import tech.renovus.solarec.db.data.vo.EmberCountryOverviewVo;

public abstract class BaseEmberCountryOverviewDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM ember_country_overview";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM ember_country_overview WHERE country_or_region = :country_or_region AND year = :year";
	protected String SQL_INSERT					= "INSERT INTO ember_country_overview (country_or_region,country_code,year,demand_twh,demand_mwh_per_capita,emissions_intensity_gco2_per_kwh,continent,ember_region,eu_flag,g20_flag,g7_flag,oecd_flag,world_demand_rank,region_demand_rank,oecd_demand_rank,eu_demand_rank,latest_year,coal_deadline,clean_deadline) VALUES (:country_or_region,:country_code,:year,:demand_twh,:demand_mwh_per_capita,:emissions_intensity_gco2_per_kwh,:continent,:ember_region,:eu_flag,:g20_flag,:g7_flag,:oecd_flag,:world_demand_rank,:region_demand_rank,:oecd_demand_rank,:eu_demand_rank,:latest_year,:coal_deadline,:clean_deadline)";
	protected String SQL_UPDATE					= "UPDATE ember_country_overview SET country_code = :country_code,demand_twh = :demand_twh,demand_mwh_per_capita = :demand_mwh_per_capita,emissions_intensity_gco2_per_kwh = :emissions_intensity_gco2_per_kwh,continent = :continent,ember_region = :ember_region,eu_flag = :eu_flag,g20_flag = :g20_flag,g7_flag = :g7_flag,oecd_flag = :oecd_flag,world_demand_rank = :world_demand_rank,region_demand_rank = :region_demand_rank,oecd_demand_rank = :oecd_demand_rank,eu_demand_rank = :eu_demand_rank,latest_year = :latest_year,coal_deadline = :coal_deadline,clean_deadline = :clean_deadline WHERE country_or_region = :country_or_region AND year = :year";
	protected String SQL_DELETE					= "DELETE FROM ember_country_overview WHERE country_or_region = :country_or_region AND year = :year";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (country_or_region, year) DO UPDATE SET country_code = EXCLUDED.country_code, demand_twh = EXCLUDED.demand_twh, demand_mwh_per_capita = EXCLUDED.demand_mwh_per_capita, emissions_intensity_gco2_per_kwh = EXCLUDED.emissions_intensity_gco2_per_kwh, continent = EXCLUDED.continent, ember_region = EXCLUDED.ember_region, eu_flag = EXCLUDED.eu_flag, g20_flag = EXCLUDED.g20_flag, g7_flag = EXCLUDED.g7_flag, oecd_flag = EXCLUDED.oecd_flag, world_demand_rank = EXCLUDED.world_demand_rank, region_demand_rank = EXCLUDED.region_demand_rank, oecd_demand_rank = EXCLUDED.oecd_demand_rank, eu_demand_rank = EXCLUDED.eu_demand_rank, latest_year = EXCLUDED.latest_year, coal_deadline = EXCLUDED.coal_deadline, clean_deadline = EXCLUDED.clean_deadline";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseEmberCountryOverviewDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(EmberCountryOverviewVo vo) {
		return new MapSqlParameterSource()
			.addValue("country_or_region", vo.getCountryOrRegion())
			.addValue("country_code", vo.getCountryCode())
			.addValue("year", vo.getYear())
			.addValue("demand_twh", vo.getDemandTwh())
			.addValue("demand_mwh_per_capita", vo.getDemandMwhPerCapita())
			.addValue("emissions_intensity_gco2_per_kwh", vo.getEmissionsIntensityGco2PerKwh())
			.addValue("continent", vo.getContinent())
			.addValue("ember_region", vo.getEmberRegion())
			.addValue("eu_flag", vo.getEuFlag())
			.addValue("g20_flag", vo.getG20Flag())
			.addValue("g7_flag", vo.getG7Flag())
			.addValue("oecd_flag", vo.getOecdFlag())
			.addValue("world_demand_rank", vo.getWorldDemandRank())
			.addValue("region_demand_rank", vo.getRegionDemandRank())
			.addValue("oecd_demand_rank", vo.getOecdDemandRank())
			.addValue("eu_demand_rank", vo.getEuDemandRank())
			.addValue("latest_year", vo.getLatestYear())
			.addValue("coal_deadline", vo.getCoalDeadline())
			.addValue("clean_deadline", vo.getCleanDeadline());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(EmberCountryOverviewVo vo) {
		return new MapSqlParameterSource()
			.addValue("country_code", vo.getCountryCode())
			.addValue("demand_twh", vo.getDemandTwh())
			.addValue("demand_mwh_per_capita", vo.getDemandMwhPerCapita())
			.addValue("emissions_intensity_gco2_per_kwh", vo.getEmissionsIntensityGco2PerKwh())
			.addValue("continent", vo.getContinent())
			.addValue("ember_region", vo.getEmberRegion())
			.addValue("eu_flag", vo.getEuFlag())
			.addValue("g20_flag", vo.getG20Flag())
			.addValue("g7_flag", vo.getG7Flag())
			.addValue("oecd_flag", vo.getOecdFlag())
			.addValue("world_demand_rank", vo.getWorldDemandRank())
			.addValue("region_demand_rank", vo.getRegionDemandRank())
			.addValue("oecd_demand_rank", vo.getOecdDemandRank())
			.addValue("eu_demand_rank", vo.getEuDemandRank())
			.addValue("latest_year", vo.getLatestYear())
			.addValue("coal_deadline", vo.getCoalDeadline())
			.addValue("clean_deadline", vo.getCleanDeadline())
			.addValue("country_or_region", vo.getCountryOrRegion())
			.addValue("year", vo.getYear());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(EmberCountryOverviewVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCountryOrRegion(), vo.getYear());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(String countryOrRegion, Integer year) {
		return new MapSqlParameterSource()
			.addValue("country_or_region", countryOrRegion)
			.addValue("year", year);
	}

	//--- Public methods ------------------------
	public Collection<EmberCountryOverviewVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, EmberCountryOverviewRowWrapper.getInstance()); }
	public EmberCountryOverviewVo findVo(String countryOrRegion, Integer year) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(countryOrRegion, year), EmberCountryOverviewRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(EmberCountryOverviewVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(EmberCountryOverviewVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(EmberCountryOverviewVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(EmberCountryOverviewVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case EmberCountryOverviewVo.SYNC_INSERT: this.insert(vo); break;
			case EmberCountryOverviewVo.SYNC_UPDATE: this.update(vo); break;
			case EmberCountryOverviewVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<EmberCountryOverviewVo> vos) {
		if (vos == null) return;
		for (EmberCountryOverviewVo vo : vos) this.synchronize(vo);
}


}

