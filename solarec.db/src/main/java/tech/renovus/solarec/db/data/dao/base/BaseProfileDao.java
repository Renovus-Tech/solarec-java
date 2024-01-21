package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ProfileRowWrapper;
import tech.renovus.solarec.vo.db.data.ProfileVo;

public abstract class BaseProfileDao <T extends ProfileVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM profile";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM profile WHERE prf_id_auto = :prf_id_auto";
	protected String SQL_INSERT					= "INSERT INTO profile (prf_name, prf_description, prf_flags) VALUES (:prf_name, :prf_description, :prf_flags)";
	protected String SQL_UPDATE					= "UPDATE profile SET prf_name = :prf_name, prf_description = :prf_description, prf_flags = :prf_flags WHERE prf_id_auto = :prf_id_auto";
	protected String SQL_DELETE					= "DELETE FROM profile WHERE prf_id_auto = :prf_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (prf_id_auto) DO UPDATE SET prf_name = EXCLUDED.prf_name, prf_description = EXCLUDED.prf_description, prf_flags = EXCLUDED.prf_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"prf_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseProfileDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("prf_id_auto", vo.getPrfId())
			.addValue("prf_name", vo.getPrfName())
			.addValue("prf_description", vo.getPrfDescription())
			.addValue("prf_flags", vo.getPrfFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("prf_name", vo.getPrfName())
			.addValue("prf_description", vo.getPrfDescription())
			.addValue("prf_flags", vo.getPrfFlags())
			.addValue("prf_id_auto", vo.getPrfId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getPrfId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer prfId) {
		return new MapSqlParameterSource()
			.addValue("prf_id_auto", prfId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, ProfileRowWrapper.getInstance()); }
	public ProfileVo findVo(Integer prfId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(prfId), ProfileRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setPrfId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
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
