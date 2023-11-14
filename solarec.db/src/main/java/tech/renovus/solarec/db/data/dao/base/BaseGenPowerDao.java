package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenPowerRowWrapper;
import tech.renovus.solarec.db.data.vo.GenPowerVo;

public abstract class BaseGenPowerDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_power";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_INSERT					= "INSERT INTO gen_power (cli_id,gen_id,pwr_wind_speed,pwr_air_density,gen_power) VALUES (:cli_id,:gen_id,:pwr_wind_speed,:pwr_air_density,:gen_power)";
	protected String SQL_UPDATE					= "UPDATE gen_power SET gen_power = :gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_DELETE					= "DELETE FROM gen_power WHERE cli_id = :cli_id AND gen_id = :gen_id AND pwr_wind_speed = :pwr_wind_speed AND pwr_air_density = :pwr_air_density";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, pwr_wind_speed, pwr_air_density) DO UPDATE SET gen_power = EXCLUDED.gen_power";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenPowerDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GenPowerVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("pwr_wind_speed", vo.getPwrWindSpeed())
			.addValue("pwr_air_density", vo.getPwrAirDensity())
			.addValue("gen_power", vo.getGenPower());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenPowerVo vo) {
		return new MapSqlParameterSource()
			.addValue("gen_power", vo.getGenPower())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("pwr_wind_speed", vo.getPwrWindSpeed())
			.addValue("pwr_air_density", vo.getPwrAirDensity());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenPowerVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getPwrWindSpeed(), vo.getPwrAirDensity());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("pwr_wind_speed", pwrWindSpeed)
			.addValue("pwr_air_density", pwrAirDensity);
	}

	//--- Public methods ------------------------
	public Collection<GenPowerVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenPowerRowWrapper.getInstance()); }
	public GenPowerVo findVo(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, pwrWindSpeed, pwrAirDensity), GenPowerRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenPowerVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenPowerVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenPowerVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenPowerVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenPowerVo.SYNC_INSERT: this.insert(vo); break;
			case GenPowerVo.SYNC_UPDATE: this.update(vo); break;
			case GenPowerVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenPowerVo> vos) {
		if (vos == null) return;
		for (GenPowerVo vo : vos) this.synchronize(vo);
}


}

