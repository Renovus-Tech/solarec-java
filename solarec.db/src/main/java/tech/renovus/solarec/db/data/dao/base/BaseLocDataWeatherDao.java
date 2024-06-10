package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocDataWeatherRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataWeatherVo;

public abstract class BaseLocDataWeatherDao <T extends LocDataWeatherVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_data_weather";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_data_weather WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_data_weather (cli_id, loc_id, data_date_added, data_date, data_type_id, data_pro_id, data_value) VALUES (:cli_id, :loc_id, :data_date_added, :data_date, :data_type_id, :data_pro_id, :data_value)";
	protected String SQL_UPDATE					= "UPDATE loc_data_weather SET data_pro_id = :data_pro_id, data_value = :data_value WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_data_weather WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date_added = :data_date_added AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, data_date_added, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocDataWeatherDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocDataWeatherVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocDataWeatherVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(LocDataWeatherVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId())
			.addValue(LocDataWeatherVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(LocDataWeatherVo.COLUMN_DATA_VALUE, vo.getDataValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocDataWeatherVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(LocDataWeatherVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(LocDataWeatherVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocDataWeatherVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(LocDataWeatherVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getDataDateAdded(), vo.getDataDate(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(LocDataWeatherVo.COLUMN_CLI_ID, cliId)
			.addValue(LocDataWeatherVo.COLUMN_LOC_ID, locId)
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE_ADDED, dataDateAdded)
			.addValue(LocDataWeatherVo.COLUMN_DATA_DATE, dataDate)
			.addValue(LocDataWeatherVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocDataWeatherRowWrapper.getInstance()); }
	public LocDataWeatherVo findVo(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, dataDateAdded, dataDate, dataTypeId), LocDataWeatherRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getDataDateAdded(), vo.getDataDate(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
