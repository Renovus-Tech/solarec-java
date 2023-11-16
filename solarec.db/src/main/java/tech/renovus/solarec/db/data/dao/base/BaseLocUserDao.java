package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocUserRowWrapper;
import tech.renovus.solarec.vo.db.data.LocUserVo;

public abstract class BaseLocUserDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_user";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_user WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_INSERT					= "INSERT INTO loc_user (cli_id,loc_id,usr_id,cli_user_date_added,cli_user_date_access) VALUES (:cli_id,:loc_id,:usr_id,:cli_user_date_added,:cli_user_date_access)";
	protected String SQL_UPDATE					= "UPDATE loc_user SET cli_user_date_added = :cli_user_date_added,cli_user_date_access = :cli_user_date_access WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_DELETE					= "DELETE FROM loc_user WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id) DO UPDATE SET cli_user_date_added = EXCLUDED.cli_user_date_added, cli_user_date_access = EXCLUDED.cli_user_date_access";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocUserDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocUserVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_user_date_added", vo.getCliUserDateAdded())
			.addValue("cli_user_date_access", vo.getCliUserDateAccess());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocUserVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_user_date_added", vo.getCliUserDateAdded())
			.addValue("cli_user_date_access", vo.getCliUserDateAccess())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocUserVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("usr_id", usrId);
	}

	//--- Public methods ------------------------
	public Collection<LocUserVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocUserRowWrapper.getInstance()); }
	public LocUserVo findVo(Integer cliId, Integer locId, Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId), LocUserRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocUserVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocUserVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocUserVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocUserVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocUserVo.SYNC_INSERT: this.insert(vo); break;
			case LocUserVo.SYNC_UPDATE: this.update(vo); break;
			case LocUserVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocUserVo> vos) {
		if (vos == null) return;
		for (LocUserVo vo : vos) this.synchronize(vo);
}


}

