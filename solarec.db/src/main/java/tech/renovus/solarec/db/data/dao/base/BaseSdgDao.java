package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.SdgRowWrapper;
import tech.renovus.solarec.vo.db.data.SdgVo;

public abstract class BaseSdgDao <T extends SdgVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sdg";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sdg WHERE sdg_id_auto = :sdg_id_auto";
	protected String SQL_INSERT					= "INSERT INTO sdg (sdg_code, sdg_name) VALUES (:sdg_code, :sdg_name)";
	protected String SQL_UPDATE					= "UPDATE sdg SET sdg_code = :sdg_code, sdg_name = :sdg_name WHERE sdg_id_auto = :sdg_id_auto";
	protected String SQL_DELETE					= "DELETE FROM sdg WHERE sdg_id_auto = :sdg_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (sdg_id_auto) DO UPDATE SET sdg_code = EXCLUDED.sdg_code, sdg_name = EXCLUDED.sdg_name";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"sdg_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseSdgDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("sdg_id_auto", vo.getSdgId())
			.addValue("sdg_code", vo.getSdgCode())
			.addValue("sdg_name", vo.getSdgName());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("sdg_code", vo.getSdgCode())
			.addValue("sdg_name", vo.getSdgName())
			.addValue("sdg_id_auto", vo.getSdgId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getSdgId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer sdgId) {
		return new MapSqlParameterSource()
			.addValue("sdg_id_auto", sdgId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, SdgRowWrapper.getInstance()); }
	public SdgVo findVo(Integer sdgId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(sdgId), SdgRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setSdgId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getSdgId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
