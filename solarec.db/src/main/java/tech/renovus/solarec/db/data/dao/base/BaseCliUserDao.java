package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliUserRowWrapper;
import tech.renovus.solarec.db.data.vo.CliUserVo;

public abstract class BaseCliUserDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_user";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_user WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_INSERT					= "INSERT INTO cli_user (cli_id,usr_id,cli_user_date_added,cli_user_date_access) VALUES (:cli_id,:usr_id,:cli_user_date_added,:cli_user_date_access)";
	protected String SQL_UPDATE					= "UPDATE cli_user SET cli_user_date_added = :cli_user_date_added,cli_user_date_access = :cli_user_date_access WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_DELETE					= "DELETE FROM cli_user WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, usr_id) DO UPDATE SET cli_user_date_added = EXCLUDED.cli_user_date_added, cli_user_date_access = EXCLUDED.cli_user_date_access";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliUserDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliUserVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_user_date_added", vo.getCliUserDateAdded())
			.addValue("cli_user_date_access", vo.getCliUserDateAccess());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliUserVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_user_date_added", vo.getCliUserDateAdded())
			.addValue("cli_user_date_access", vo.getCliUserDateAccess())
			.addValue("cli_id", vo.getCliId())
			.addValue("usr_id", vo.getUsrId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliUserVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getUsrId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer usrId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("usr_id", usrId);
	}

	//--- Public methods ------------------------
	public Collection<CliUserVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliUserRowWrapper.getInstance()); }
	public CliUserVo findVo(Integer cliId, Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, usrId), CliUserRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliUserVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(CliUserVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliUserVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliUserVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliUserVo.SYNC_INSERT: this.insert(vo); break;
			case CliUserVo.SYNC_UPDATE: this.update(vo); break;
			case CliUserVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliUserVo> vos) {
		if (vos == null) return;
		for (CliUserVo vo : vos) this.synchronize(vo);
}


}

