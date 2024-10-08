package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.DataTypeVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseDataTypeDao <T extends DataTypeVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM data_type";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM data_type WHERE data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO data_type (data_type_id, data_cat_id, data_type_name, data_type_units, data_type_description) VALUES (:data_type_id, :data_cat_id, :data_type_name, :data_type_units, :data_type_description)";
	protected String SQL_UPDATE					= "UPDATE data_type SET data_cat_id = :data_cat_id, data_type_name = :data_type_name, data_type_units = :data_type_units, data_type_description = :data_type_description WHERE data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM data_type WHERE data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_type_id) DO UPDATE SET data_cat_id = EXCLUDED.data_cat_id, data_type_name = EXCLUDED.data_type_name, data_type_units = EXCLUDED.data_type_units, data_type_description = EXCLUDED.data_type_description";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseDataTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId())
			.addValue(DataTypeVo.COLUMN_DATA_CAT_ID, vo.getDataCatId())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_NAME, vo.getDataTypeName())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_UNITS, vo.getDataTypeUnits())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_DESCRIPTION, vo.getDataTypeDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataTypeVo.COLUMN_DATA_CAT_ID, vo.getDataCatId())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_NAME, vo.getDataTypeName())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_UNITS, vo.getDataTypeUnits())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_DESCRIPTION, vo.getDataTypeDescription())
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(DataTypeVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataTypeRowWrapper.getInstance()); }
	public DataTypeVo findVo(Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataTypeId), DataTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
