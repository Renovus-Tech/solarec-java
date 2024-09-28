package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefResultRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefResultVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseStatDefResultDao <T extends StatDefResultVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM stat_def_result";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_def_result WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_def_result (stat_def_id, stat_type_id) VALUES (:stat_def_id, :stat_type_id)";
	protected String SQL_UPDATE					= "UPDATE stat_def_result SET  WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_def_result WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id, stat_type_id) DO UPDATE SET ";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseStatDefResultDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefResultVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(StatDefResultVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefResultVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(StatDefResultVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId(), vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue(StatDefResultVo.COLUMN_STAT_DEF_ID, statDefId)
			.addValue(StatDefResultVo.COLUMN_STAT_TYPE_ID, statTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StatDefResultRowWrapper.getInstance()); }
	public StatDefResultVo findVo(Integer statDefId, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId, statTypeId), StatDefResultRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStatDefId(), vo.getStatTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
