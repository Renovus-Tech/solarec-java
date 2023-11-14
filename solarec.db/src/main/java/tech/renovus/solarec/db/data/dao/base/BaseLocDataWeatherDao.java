package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocDataWeatherRowWrapper;
import tech.renovus.solarec.db.data.vo.LocDataWeatherVo;

public abstract class BaseLocDataWeatherDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_data_weather";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_data_weather WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_data_weather (cli_id,loc_id,data_date_added,data_date,data_type_id,data_value) VALUES (:cli_id,:loc_id,:data_date_added,:data_date,:data_type_id,:data_value)";
	protected String SQL_UPDATE					= "UPDATE loc_data_weather SET data_value = :data_value WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_data_weather WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, data_date_added, data_date, data_type_id) DO UPDATE SET data_value = EXCLUDED.data_value";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocDataWeatherDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocDataWeatherVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_value", vo.getDataValue());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocDataWeatherVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_value", vo.getDataValue())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_date_added", vo.getDataDateAdded())
			.addValue("data_date", vo.getDataDate())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocDataWeatherVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getDataDateAdded(), vo.getDataDate(), vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("data_date_added", dataDateAdded)
			.addValue("data_date", dataDate)
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<LocDataWeatherVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocDataWeatherRowWrapper.getInstance()); }
	public LocDataWeatherVo findVo(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, dataDateAdded, dataDate, dataTypeId), LocDataWeatherRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocDataWeatherVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocDataWeatherVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocDataWeatherVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocDataWeatherVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocDataWeatherVo.SYNC_INSERT: this.insert(vo); break;
			case LocDataWeatherVo.SYNC_UPDATE: this.update(vo); break;
			case LocDataWeatherVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocDataWeatherVo> vos) {
		if (vos == null) return;
		for (LocDataWeatherVo vo : vos) this.synchronize(vo);
}


}

