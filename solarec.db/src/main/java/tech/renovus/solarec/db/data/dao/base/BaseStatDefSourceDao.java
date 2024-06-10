package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefSourceRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefSourceVo;

public abstract class BaseStatDefSourceDao <T extends StatDefSourceVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_def_source";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_def_source WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_def_source (stat_def_id, data_type_id) VALUES (:stat_def_id, :data_type_id)";
	protected String SQL_UPDATE					= "UPDATE stat_def_source SET  WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_def_source WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id, data_type_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatDefSourceDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefSourceVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(StatDefSourceVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefSourceVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(StatDefSourceVo.COLUMN_DATA_TYPE_ID, vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId(), vo.getDataTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue(StatDefSourceVo.COLUMN_STAT_DEF_ID, statDefId)
			.addValue(StatDefSourceVo.COLUMN_DATA_TYPE_ID, dataTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StatDefSourceRowWrapper.getInstance()); }
	public StatDefSourceVo findVo(Integer statDefId, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId, dataTypeId), StatDefSourceRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStatDefId(), vo.getDataTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
