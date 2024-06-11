package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocDataRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataVo;

public abstract class BaseLocDataDao <T extends LocDataVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM loc_data";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO loc_data (cli_id, loc_id, data_date, data_type_id, data_pro_id, data_value, data_date_added) VALUES (:cli_id, :loc_id, :data_date, :data_type_id, :data_pro_id, :data_value, :data_date_added)";
	protected String SQL_UPDATE					= "UPDATE loc_data SET data_pro_id = :data_pro_id, data_value = :data_value, data_date_added = :data_date_added WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM loc_data WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_date = :data_date AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, data_date, data_type_id) DO UPDATE SET data_pro_id = EXCLUDED.data_pro_id, data_value = EXCLUDED.data_value, data_date_added = EXCLUDED.data_date_added";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseLocDataDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocDataVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(LocDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId())
			.addValue(LocDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(LocDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(LocDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocDataVo.COLUMN_DATA_PRO_ID, vo.getDataProId())
			.addValue(LocDataVo.COLUMN_DATA_VALUE, vo.getDataValue())
			.addValue(LocDataVo.COLUMN_DATA_DATE_ADDED, vo.getDataDateAdded())
			.addValue(LocDataVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(LocDataVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(LocDataVo.COLUMN_DATA_DATE, vo.getDataDate())
			.addValue(LocDataVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getDataDate(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(LocDataVo.COLUMN_CLI_ID, cliId)
			.addValue(LocDataVo.COLUMN_LOC_ID, locId)
			.addValue(LocDataVo.COLUMN_DATA_DATE, dataDate)
			.addValue(LocDataVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocDataRowWrapper.getInstance()); }
	public LocDataVo findVo(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, dataDate, dataTypeId), LocDataRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getDataDate(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
