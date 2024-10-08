package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.FunctionalityRowWrapper;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseFunctionalityDao <T extends FunctionalityVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM functionality";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM functionality WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_INSERT					= "INSERT INTO functionality (fnc_order, fnc_title, fnc_flags, fnc_url, fnc_description, fnc_name) VALUES (:fnc_order, :fnc_title, :fnc_flags, :fnc_url, :fnc_description, :fnc_name)";
	protected String SQL_UPDATE					= "UPDATE functionality SET fnc_order = :fnc_order, fnc_title = :fnc_title, fnc_flags = :fnc_flags, fnc_url = :fnc_url, fnc_description = :fnc_description, fnc_name = :fnc_name WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_DELETE					= "DELETE FROM functionality WHERE fnc_id_auto = :fnc_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (fnc_id_auto) DO UPDATE SET fnc_order = EXCLUDED.fnc_order, fnc_title = EXCLUDED.fnc_title, fnc_flags = EXCLUDED.fnc_flags, fnc_url = EXCLUDED.fnc_url, fnc_description = EXCLUDED.fnc_description, fnc_name = EXCLUDED.fnc_name";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"fnc_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseFunctionalityDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(FunctionalityVo.COLUMN_FNC_ID, vo.getFncId())
			.addValue(FunctionalityVo.COLUMN_FNC_ORDER, vo.getFncOrder())
			.addValue(FunctionalityVo.COLUMN_FNC_TITLE, vo.getFncTitle())
			.addValue(FunctionalityVo.COLUMN_FNC_FLAGS, vo.getFncFlags())
			.addValue(FunctionalityVo.COLUMN_FNC_URL, vo.getFncUrl())
			.addValue(FunctionalityVo.COLUMN_FNC_DESCRIPTION, vo.getFncDescription())
			.addValue(FunctionalityVo.COLUMN_FNC_NAME, vo.getFncName());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(FunctionalityVo.COLUMN_FNC_ORDER, vo.getFncOrder())
			.addValue(FunctionalityVo.COLUMN_FNC_TITLE, vo.getFncTitle())
			.addValue(FunctionalityVo.COLUMN_FNC_FLAGS, vo.getFncFlags())
			.addValue(FunctionalityVo.COLUMN_FNC_URL, vo.getFncUrl())
			.addValue(FunctionalityVo.COLUMN_FNC_DESCRIPTION, vo.getFncDescription())
			.addValue(FunctionalityVo.COLUMN_FNC_NAME, vo.getFncName())
			.addValue(FunctionalityVo.COLUMN_FNC_ID, vo.getFncId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getFncId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer fncId) {
		return new MapSqlParameterSource()
			.addValue(FunctionalityVo.COLUMN_FNC_ID, fncId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, FunctionalityRowWrapper.getInstance()); }
	public FunctionalityVo findVo(Integer fncId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(fncId), FunctionalityRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setFncId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getFncId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
