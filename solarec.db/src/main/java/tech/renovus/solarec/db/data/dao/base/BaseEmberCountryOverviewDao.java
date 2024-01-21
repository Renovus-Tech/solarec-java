package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.EmberCountryOverviewRowWrapper;
import tech.renovus.solarec.vo.db.data.EmberCountryOverviewVo;

public abstract class BaseEmberCountryOverviewDao <T extends EmberCountryOverviewVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM ember_country_overview";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM ember_country_overview WHERE year = :year AND country_or_region = :country_or_region";
	protected String SQL_INSERT					= "INSERT INTO ember_country_overview (clean_deadline, oecd_demand_rank, eu_demand_rank, latest_year, coal_deadline, year, demand_twh, demand_mwh_per_capita, emissions_intensity_gco2_per_kwh, eu_flag, g20_flag, g7_flag, oecd_flag, world_demand_rank, region_demand_rank, country_code, country_or_region, continent, ember_region) VALUES (:clean_deadline, :oecd_demand_rank, :eu_demand_rank, :latest_year, :coal_deadline, :year, :demand_twh, :demand_mwh_per_capita, :emissions_intensity_gco2_per_kwh, :eu_flag, :g20_flag, :g7_flag, :oecd_flag, :world_demand_rank, :region_demand_rank, :country_code, :country_or_region, :continent, :ember_region)";
	protected String SQL_UPDATE					= "UPDATE ember_country_overview SET clean_deadline = :clean_deadline, oecd_demand_rank = :oecd_demand_rank, eu_demand_rank = :eu_demand_rank, latest_year = :latest_year, coal_deadline = :coal_deadline, demand_twh = :demand_twh, demand_mwh_per_capita = :demand_mwh_per_capita, emissions_intensity_gco2_per_kwh = :emissions_intensity_gco2_per_kwh, eu_flag = :eu_flag, g20_flag = :g20_flag, g7_flag = :g7_flag, oecd_flag = :oecd_flag, world_demand_rank = :world_demand_rank, region_demand_rank = :region_demand_rank, country_code = :country_code, continent = :continent, ember_region = :ember_region WHERE year = :year AND country_or_region = :country_or_region";
	protected String SQL_DELETE					= "DELETE FROM ember_country_overview WHERE year = :year AND country_or_region = :country_or_region";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (year, country_or_region) DO UPDATE SET clean_deadline = EXCLUDED.clean_deadline, oecd_demand_rank = EXCLUDED.oecd_demand_rank, eu_demand_rank = EXCLUDED.eu_demand_rank, latest_year = EXCLUDED.latest_year, coal_deadline = EXCLUDED.coal_deadline, demand_twh = EXCLUDED.demand_twh, demand_mwh_per_capita = EXCLUDED.demand_mwh_per_capita, emissions_intensity_gco2_per_kwh = EXCLUDED.emissions_intensity_gco2_per_kwh, eu_flag = EXCLUDED.eu_flag, g20_flag = EXCLUDED.g20_flag, g7_flag = EXCLUDED.g7_flag, oecd_flag = EXCLUDED.oecd_flag, world_demand_rank = EXCLUDED.world_demand_rank, region_demand_rank = EXCLUDED.region_demand_rank, country_code = EXCLUDED.country_code, continent = EXCLUDED.continent, ember_region = EXCLUDED.ember_region";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseEmberCountryOverviewDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("clean_deadline", vo.getCleanDeadline())
			.addValue("oecd_demand_rank", vo.getOecdDemandRank())
			.addValue("eu_demand_rank", vo.getEuDemandRank())
			.addValue("latest_year", vo.getLatestYear())
			.addValue("coal_deadline", vo.getCoalDeadline())
			.addValue("year", vo.getYear())
			.addValue("demand_twh", vo.getDemandTwh())
			.addValue("demand_mwh_per_capita", vo.getDemandMwhPerCapita())
			.addValue("emissions_intensity_gco2_per_kwh", vo.getEmissionsIntensityGco2PerKwh())
			.addValue("eu_flag", vo.getEuFlag())
			.addValue("g20_flag", vo.getG20Flag())
			.addValue("g7_flag", vo.getG7Flag())
			.addValue("oecd_flag", vo.getOecdFlag())
			.addValue("world_demand_rank", vo.getWorldDemandRank())
			.addValue("region_demand_rank", vo.getRegionDemandRank())
			.addValue("country_code", vo.getCountryCode())
			.addValue("country_or_region", vo.getCountryOrRegion())
			.addValue("continent", vo.getContinent())
			.addValue("ember_region", vo.getEmberRegion());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("clean_deadline", vo.getCleanDeadline())
			.addValue("oecd_demand_rank", vo.getOecdDemandRank())
			.addValue("eu_demand_rank", vo.getEuDemandRank())
			.addValue("latest_year", vo.getLatestYear())
			.addValue("coal_deadline", vo.getCoalDeadline())
			.addValue("demand_twh", vo.getDemandTwh())
			.addValue("demand_mwh_per_capita", vo.getDemandMwhPerCapita())
			.addValue("emissions_intensity_gco2_per_kwh", vo.getEmissionsIntensityGco2PerKwh())
			.addValue("eu_flag", vo.getEuFlag())
			.addValue("g20_flag", vo.getG20Flag())
			.addValue("g7_flag", vo.getG7Flag())
			.addValue("oecd_flag", vo.getOecdFlag())
			.addValue("world_demand_rank", vo.getWorldDemandRank())
			.addValue("region_demand_rank", vo.getRegionDemandRank())
			.addValue("country_code", vo.getCountryCode())
			.addValue("continent", vo.getContinent())
			.addValue("ember_region", vo.getEmberRegion())
			.addValue("year", vo.getYear())
			.addValue("country_or_region", vo.getCountryOrRegion());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getYear(), vo.getCountryOrRegion());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer year, String countryOrRegion) {
		return new MapSqlParameterSource()
			.addValue("year", year)
			.addValue("country_or_region", countryOrRegion);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, EmberCountryOverviewRowWrapper.getInstance()); }
	public EmberCountryOverviewVo findVo(Integer year, String countryOrRegion) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(year, countryOrRegion), EmberCountryOverviewRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}
