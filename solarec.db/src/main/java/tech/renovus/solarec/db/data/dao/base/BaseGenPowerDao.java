package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenPowerRowWrapper;
import tech.renovus.solarec.vo.db.data.GenPowerVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseGenPowerDao <T extends GenPowerVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM gen_power";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_INSERT					= "INSERT INTO gen_power (cli_id, gen_id, pwr_wind_speed, pwr_air_density, gen_power) VALUES (:cli_id, :gen_id, :pwr_wind_speed, :pwr_air_density, :gen_power)";
	protected String SQL_UPDATE					= "UPDATE gen_power SET gen_power = :gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_DELETE					= "DELETE FROM gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, pwr_wind_speed, pwr_air_density) DO UPDATE SET gen_power = EXCLUDED.gen_power";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseGenPowerDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenPowerVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenPowerVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenPowerVo.COLUMN_PWR_WIND_SPEED, vo.getPwrWindSpeed())
			.addValue(GenPowerVo.COLUMN_PWR_AIR_DENSITY, vo.getPwrAirDensity())
			.addValue(GenPowerVo.COLUMN_GEN_POWER, vo.getGenPower());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenPowerVo.COLUMN_GEN_POWER, vo.getGenPower())
			.addValue(GenPowerVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenPowerVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenPowerVo.COLUMN_PWR_WIND_SPEED, vo.getPwrWindSpeed())
			.addValue(GenPowerVo.COLUMN_PWR_AIR_DENSITY, vo.getPwrAirDensity());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getPwrWindSpeed(), vo.getPwrAirDensity());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) {
		return new MapSqlParameterSource()
			.addValue(GenPowerVo.COLUMN_CLI_ID, cliId)
			.addValue(GenPowerVo.COLUMN_GEN_ID, genId)
			.addValue(GenPowerVo.COLUMN_PWR_WIND_SPEED, pwrWindSpeed)
			.addValue(GenPowerVo.COLUMN_PWR_AIR_DENSITY, pwrAirDensity);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenPowerRowWrapper.getInstance()); }
	public GenPowerVo findVo(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, pwrWindSpeed, pwrAirDensity), GenPowerRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getPwrWindSpeed(), vo.getPwrAirDensity()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}
