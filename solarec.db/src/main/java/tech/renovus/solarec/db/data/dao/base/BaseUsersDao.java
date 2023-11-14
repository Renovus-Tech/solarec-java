package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.UsersRowWrapper;
import tech.renovus.solarec.db.data.vo.UsersVo;

public abstract class BaseUsersDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM users";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM users WHERE usr_id_auto = :usr_id_auto";
	protected String SQL_INSERT					= "INSERT INTO users (usr_email,usr_name,usr_flags,usr_password,usr_date_login,usr_date_added,usr_date_locked,usr_comments,usr_pwd_reset_requested,usr_pwd_reset_uuid) VALUES (:usr_email,:usr_name,:usr_flags,:usr_password,:usr_date_login,:usr_date_added,:usr_date_locked,:usr_comments,:usr_pwd_reset_requested,:usr_pwd_reset_uuid)";
	protected String SQL_UPDATE					= "UPDATE users SET usr_email = :usr_email,usr_name = :usr_name,usr_flags = :usr_flags,usr_password = :usr_password,usr_date_login = :usr_date_login,usr_date_added = :usr_date_added,usr_date_locked = :usr_date_locked,usr_comments = :usr_comments,usr_pwd_reset_requested = :usr_pwd_reset_requested,usr_pwd_reset_uuid = :usr_pwd_reset_uuid WHERE usr_id_auto = :usr_id_auto";
	protected String SQL_DELETE					= "DELETE FROM users WHERE usr_id_auto = :usr_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (usr_id_auto) DO UPDATE SET usr_email = EXCLUDED.usr_email, usr_name = EXCLUDED.usr_name, usr_flags = EXCLUDED.usr_flags, usr_password = EXCLUDED.usr_password, usr_date_login = EXCLUDED.usr_date_login, usr_date_added = EXCLUDED.usr_date_added, usr_date_locked = EXCLUDED.usr_date_locked, usr_comments = EXCLUDED.usr_comments, usr_pwd_reset_requested = EXCLUDED.usr_pwd_reset_requested, usr_pwd_reset_uuid = EXCLUDED.usr_pwd_reset_uuid";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"usr_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseUsersDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(UsersVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_email", vo.getUsrEmail())
			.addValue("usr_name", vo.getUsrName())
			.addValue("usr_flags", vo.getUsrFlags())
			.addValue("usr_password", vo.getUsrPassword())
			.addValue("usr_date_login", vo.getUsrDateLogin())
			.addValue("usr_date_added", vo.getUsrDateAdded())
			.addValue("usr_date_locked", vo.getUsrDateLocked())
			.addValue("usr_comments", vo.getUsrComments())
			.addValue("usr_pwd_reset_requested", vo.getUsrPwdResetRequested())
			.addValue("usr_pwd_reset_uuid", vo.getUsrPwdResetUuid());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(UsersVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_email", vo.getUsrEmail())
			.addValue("usr_name", vo.getUsrName())
			.addValue("usr_flags", vo.getUsrFlags())
			.addValue("usr_password", vo.getUsrPassword())
			.addValue("usr_date_login", vo.getUsrDateLogin())
			.addValue("usr_date_added", vo.getUsrDateAdded())
			.addValue("usr_date_locked", vo.getUsrDateLocked())
			.addValue("usr_comments", vo.getUsrComments())
			.addValue("usr_pwd_reset_requested", vo.getUsrPwdResetRequested())
			.addValue("usr_pwd_reset_uuid", vo.getUsrPwdResetUuid())
			.addValue("usr_id_auto", vo.getUsrId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(UsersVo vo) {
		return this.createPkMapSqlParameterSource(vo.getUsrId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer usrId) {
		return new MapSqlParameterSource()
			.addValue("usr_id_auto", usrId);
	}

	//--- Public methods ------------------------
	public Collection<UsersVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, UsersRowWrapper.getInstance()); }
	public UsersVo findVo(Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(usrId), UsersRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(UsersVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setUsrId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(UsersVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(UsersVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(UsersVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case UsersVo.SYNC_INSERT: this.insert(vo); break;
			case UsersVo.SYNC_UPDATE: this.update(vo); break;
			case UsersVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<UsersVo> vos) {
		if (vos == null) return;
		for (UsersVo vo : vos) this.synchronize(vo);
}


}

