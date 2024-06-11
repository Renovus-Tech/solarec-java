package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaDataRowWrapper;
import tech.renovus.solarec.vo.db.data.StaDataVo;

public abstract class BaseStaDataDao <T extends StaDataVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM sta_data";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO sta_data (cli_id, sta_id, data_date, data_type_id, data_pro_id, data_value, data_date_added) VALUES (:cli_id, :sta_id, :data_date, :data_type_id, :data_pro_id, :data_value, :data_date_added)";
	protected String SQL_UPDATE					= "UPDATE sta_data SET data_pro_id = :data_pro_id, data_value = :data_value, data_date_added = :data_date_added WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM sta_data WHERE cli_id = :cli_id AND sta_id = :sta_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseStaDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaDataVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(StaDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId())
			.addValue(StaDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(StaDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(StaDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(StaDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(StaDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(StaDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaDataVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(StaDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getDataDate(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(StaDataVo.COLUMN_CLI_ID, cliId)
			.addValue(StaDataVo.COLUMN_STA_ID, staId)
			.addValue(StaDataVo.COLUMN_DATA_DATE, dataDate)
			.addValue(StaDataVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StaDataRowWrapper.getInstance()); }
	public StaDataVo findVo(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, dataDate, dataTypeId), StaDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getStaId(), vo.getDataDate(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
