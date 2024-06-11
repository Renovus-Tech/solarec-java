package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefStatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

public abstract class BaseDataDefStatDefinitionDao <T extends DataDefStatDefinitionVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM data_def_stat_definition";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM data_def_stat_definition WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_INSERT					= "INSERT INTO data_def_stat_definition (data_def_id, stat_def_id, stat_def_call_order) VALUES (:data_def_id, :stat_def_id, :stat_def_call_order)";
	protected String SQL_UPDATE					= "UPDATE data_def_stat_definition SET stat_def_call_order = :stat_def_call_order WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_DELETE					= "DELETE FROM data_def_stat_definition WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id, stat_def_id) DO UPDATE SET stat_def_call_order = EXCLUDED.stat_def_call_order";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseDataDefStatDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataDefStatDefinitionVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataDefStatDefinitionVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(DataDefStatDefinitionVo.COLUMN_STAT_DEF_CALL_ORDER, vo.getStatDefCallOrder());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataDefStatDefinitionVo.COLUMN_STAT_DEF_CALL_ORDER, vo.getStatDefCallOrder())
			.addValue(DataDefStatDefinitionVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataDefStatDefinitionVo.COLUMN_STAT_DEF_ID, vo.getStatDefId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId(), vo.getStatDefId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId, Integer statDefId) {
		return new MapSqlParameterSource()
			.addValue(DataDefStatDefinitionVo.COLUMN_DATA_DEF_ID, dataDefId)
			.addValue(DataDefStatDefinitionVo.COLUMN_STAT_DEF_ID, statDefId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataDefStatDefinitionRowWrapper.getInstance()); }
	public DataDefStatDefinitionVo findVo(Integer dataDefId, Integer statDefId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId, statDefId), DataDefStatDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataDefId(), vo.getStatDefId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
