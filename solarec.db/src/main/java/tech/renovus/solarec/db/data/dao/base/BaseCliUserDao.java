package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliUserRowWrapper;
import tech.renovus.solarec.vo.db.data.CliUserVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseCliUserDao <T extends CliUserVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_user";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_user WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_INSERT					= "INSERT INTO cli_user (cli_id, usr_id, cli_user_date_added, cli_user_date_access) VALUES (:cli_id, :usr_id, :cli_user_date_added, :cli_user_date_access)";
	protected String SQL_UPDATE					= "UPDATE cli_user SET cli_user_date_added = :cli_user_date_added, cli_user_date_access = :cli_user_date_access WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_DELETE					= "DELETE FROM cli_user WHERE cli_id = :cli_id AND usr_id = :usr_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, usr_id) DO UPDATE SET cli_user_date_added = EXCLUDED.cli_user_date_added, cli_user_date_access = EXCLUDED.cli_user_date_access";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliUserDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliUserVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliUserVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliUserVo.COLUMN_CLI_USER_DATE_ADDED, vo.getCliUserDateAdded())
			.addValue(CliUserVo.COLUMN_CLI_USER_DATE_ACCESS, vo.getCliUserDateAccess());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliUserVo.COLUMN_CLI_USER_DATE_ADDED, vo.getCliUserDateAdded())
			.addValue(CliUserVo.COLUMN_CLI_USER_DATE_ACCESS, vo.getCliUserDateAccess())
			.addValue(CliUserVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliUserVo.COLUMN_USR_ID, vo.getUsrId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getUsrId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer usrId) {
		return new MapSqlParameterSource()
			.addValue(CliUserVo.COLUMN_CLI_ID, cliId)
			.addValue(CliUserVo.COLUMN_USR_ID, usrId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliUserRowWrapper.getInstance()); }
	public CliUserVo findVo(Integer cliId, Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, usrId), CliUserRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getUsrId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
