package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.ProfileRowWrapper;
import tech.renovus.solarec.db.data.vo.ProfileVo;

public abstract class BaseProfileDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM profile";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM profile WHERE prf_id_auto = :prf_id_auto";
	protected String SQL_INSERT					= "INSERT INTO profile (prf_name,prf_description,prf_flags) VALUES (:prf_name,:prf_description,:prf_flags)";
	protected String SQL_UPDATE					= "UPDATE profile SET prf_name = :prf_name,prf_description = :prf_description,prf_flags = :prf_flags WHERE prf_id_auto = :prf_id_auto";
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
	private MapSqlParameterSource createInsertMapSqlParameterSource(ProfileVo vo) {
		return new MapSqlParameterSource()
			.addValue("prf_name", vo.getPrfName())
			.addValue("prf_description", vo.getPrfDescription())
			.addValue("prf_flags", vo.getPrfFlags());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(ProfileVo vo) {
		return new MapSqlParameterSource()
			.addValue("prf_name", vo.getPrfName())
			.addValue("prf_description", vo.getPrfDescription())
			.addValue("prf_flags", vo.getPrfFlags())
			.addValue("prf_id_auto", vo.getPrfId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(ProfileVo vo) {
		return this.createPkMapSqlParameterSource(vo.getPrfId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer prfId) {
		return new MapSqlParameterSource()
			.addValue("prf_id_auto", prfId);
	}

	//--- Public methods ------------------------
	public Collection<ProfileVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, ProfileRowWrapper.getInstance()); }
	public ProfileVo findVo(Integer prfId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(prfId), ProfileRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(ProfileVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setPrfId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(ProfileVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(ProfileVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(ProfileVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case ProfileVo.SYNC_INSERT: this.insert(vo); break;
			case ProfileVo.SYNC_UPDATE: this.update(vo); break;
			case ProfileVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<ProfileVo> vos) {
		if (vos == null) return;
		for (ProfileVo vo : vos) this.synchronize(vo);
}


}

