package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocTypeRowWrapper;
import tech.renovus.solarec.vo.db.data.LocTypeVo;

public abstract class BaseLocTypeDao <T extends LocTypeVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM loc_type";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_type WHERE loc_type_id_auto = :loc_type_id_auto";
	protected String SQL_INSERT					= "INSERT INTO loc_type (loc_type_code, loc_type_text) VALUES (:loc_type_code, :loc_type_text)";
	protected String SQL_UPDATE					= "UPDATE loc_type SET loc_type_code = :loc_type_code, loc_type_text = :loc_type_text WHERE loc_type_id_auto = :loc_type_id_auto";
	protected String SQL_DELETE					= "DELETE FROM loc_type WHERE loc_type_id_auto = :loc_type_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (loc_type_id_auto) DO UPDATE SET loc_type_code = EXCLUDED.loc_type_code, loc_type_text = EXCLUDED.loc_type_text";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"loc_type_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseLocTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_ID, vo.getLocTypeId())
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_CODE, vo.getLocTypeCode())
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_TEXT, vo.getLocTypeText());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_CODE, vo.getLocTypeCode())
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_TEXT, vo.getLocTypeText())
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_ID, vo.getLocTypeId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getLocTypeId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer locTypeId) {
		return new MapSqlParameterSource()
			.addValue(LocTypeVo.COLUMN_LOC_TYPE_ID, locTypeId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocTypeRowWrapper.getInstance()); }
	public LocTypeVo findVo(Integer locTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(locTypeId), LocTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setLocTypeId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getLocTypeId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
