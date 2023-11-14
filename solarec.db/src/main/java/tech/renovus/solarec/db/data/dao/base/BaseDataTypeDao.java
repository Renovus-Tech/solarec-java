package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.DataTypeVo;

public abstract class BaseDataTypeDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_type WHERE data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO data_type (data_type_id,data_type_name,data_type_units,data_type_description) VALUES (:data_type_id,:data_type_name,:data_type_units,:data_type_description)";
	protected String SQL_UPDATE					= "UPDATE data_type SET data_type_name = :data_type_name,data_type_units = :data_type_units,data_type_description = :data_type_description WHERE data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM data_type WHERE data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_type_id) DO UPDATE SET data_type_name = EXCLUDED.data_type_name, data_type_units = EXCLUDED.data_type_units, data_type_description = EXCLUDED.data_type_description";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DataTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_type_id", vo.getDataTypeId())
			.addValue("data_type_name", vo.getDataTypeName())
			.addValue("data_type_units", vo.getDataTypeUnits())
			.addValue("data_type_description", vo.getDataTypeDescription());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DataTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_type_name", vo.getDataTypeName())
			.addValue("data_type_units", vo.getDataTypeUnits())
			.addValue("data_type_description", vo.getDataTypeDescription())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DataTypeVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<DataTypeVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DataTypeRowWrapper.getInstance()); }
	public DataTypeVo findVo(Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataTypeId), DataTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DataTypeVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DataTypeVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DataTypeVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DataTypeVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DataTypeVo.SYNC_INSERT: this.insert(vo); break;
			case DataTypeVo.SYNC_UPDATE: this.update(vo); break;
			case DataTypeVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DataTypeVo> vos) {
		if (vos == null) return;
		for (DataTypeVo vo : vos) this.synchronize(vo);
}


}

