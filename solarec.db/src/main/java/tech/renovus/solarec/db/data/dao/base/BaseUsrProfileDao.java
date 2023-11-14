package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.UsrProfileRowWrapper;
import tech.renovus.solarec.db.data.vo.UsrProfileVo;

public abstract class BaseUsrProfileDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM usr_profile";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM usr_profile WHERE usr_id = :usr_id AND prf_id = :prf_id AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO usr_profile (usr_id,prf_id,cli_id,usr_prf_date_added) VALUES (:usr_id,:prf_id,:cli_id,:usr_prf_date_added)";
	protected String SQL_UPDATE					= "UPDATE usr_profile SET usr_prf_date_added = :usr_prf_date_added WHERE usr_id = :usr_id AND prf_id = :prf_id AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM usr_profile WHERE usr_id = :usr_id AND prf_id = :prf_id AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (usr_id, prf_id, cli_id) DO UPDATE SET usr_prf_date_added = EXCLUDED.usr_prf_date_added";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseUsrProfileDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(UsrProfileVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_id", vo.getUsrId())
			.addValue("prf_id", vo.getPrfId())
			.addValue("cli_id", vo.getCliId())
			.addValue("usr_prf_date_added", vo.getUsrPrfDateAdded());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(UsrProfileVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_prf_date_added", vo.getUsrPrfDateAdded())
			.addValue("usr_id", vo.getUsrId())
			.addValue("prf_id", vo.getPrfId())
			.addValue("cli_id", vo.getCliId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(UsrProfileVo vo) {
		return this.createPkMapSqlParameterSource(vo.getUsrId(), vo.getPrfId(), vo.getCliId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer usrId, Integer prfId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("usr_id", usrId)
			.addValue("prf_id", prfId)
			.addValue("cli_id", cliId);
	}

	//--- Public methods ------------------------
	public Collection<UsrProfileVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, UsrProfileRowWrapper.getInstance()); }
	public UsrProfileVo findVo(Integer usrId, Integer prfId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(usrId, prfId, cliId), UsrProfileRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(UsrProfileVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(UsrProfileVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(UsrProfileVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(UsrProfileVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case UsrProfileVo.SYNC_INSERT: this.insert(vo); break;
			case UsrProfileVo.SYNC_UPDATE: this.update(vo); break;
			case UsrProfileVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<UsrProfileVo> vos) {
		if (vos == null) return;
		for (UsrProfileVo vo : vos) this.synchronize(vo);
}


}

