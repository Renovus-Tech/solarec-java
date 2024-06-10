package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.StatTypeVo;

public abstract class BaseStatTypeDao <T extends StatTypeVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_type WHERE stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_type (stat_type_id, stat_type_name, stat_type_unit, stat_type_description) VALUES (:stat_type_id, :stat_type_name, :stat_type_unit, :stat_type_description)";
	protected String SQL_UPDATE					= "UPDATE stat_type SET stat_type_name = :stat_type_name, stat_type_unit = :stat_type_unit, stat_type_description = :stat_type_description WHERE stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_type WHERE stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_type_id) DO UPDATE SET stat_type_name = EXCLUDED.stat_type_name, stat_type_unit = EXCLUDED.stat_type_unit, stat_type_description = EXCLUDED.stat_type_description";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_NAME, vo.getStatTypeName())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_UNIT, vo.getStatTypeUnit())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_DESCRIPTION, vo.getStatTypeDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_NAME, vo.getStatTypeName())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_UNIT, vo.getStatTypeUnit())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_DESCRIPTION, vo.getStatTypeDescription())
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_ID, vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStatTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue(StatTypeVo.COLUMN_STAT_TYPE_ID, statTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StatTypeRowWrapper.getInstance()); }
	public StatTypeVo findVo(Integer statTypeId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statTypeId), StatTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStatTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
