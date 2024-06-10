package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.UsrSettingRowWrapper;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;

public abstract class BaseUsrSettingDao <T extends UsrSettingVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM usr_setting";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM usr_setting WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_INSERT					= "INSERT INTO usr_setting (usr_id, usr_set_name, usr_set_value) VALUES (:usr_id, :usr_set_name, :usr_set_value)";
	protected String SQL_UPDATE					= "UPDATE usr_setting SET usr_set_value = :usr_set_value WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_DELETE					= "DELETE FROM usr_setting WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (usr_id, usr_set_name) DO UPDATE SET usr_set_value = EXCLUDED.usr_set_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseUsrSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(UsrSettingVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(UsrSettingVo.COLUMN_USR_SET_NAME, vo.getUsrSetName())
			.addValue(UsrSettingVo.COLUMN_USR_SET_VALUE, vo.getUsrSetValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(UsrSettingVo.COLUMN_USR_SET_VALUE, vo.getUsrSetValue())
			.addValue(UsrSettingVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(UsrSettingVo.COLUMN_USR_SET_NAME, vo.getUsrSetName());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getUsrId(), vo.getUsrSetName());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer usrId, String usrSetName) {
		return new MapSqlParameterSource()
			.addValue(UsrSettingVo.COLUMN_USR_ID, usrId)
			.addValue(UsrSettingVo.COLUMN_USR_SET_NAME, usrSetName);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, UsrSettingRowWrapper.getInstance()); }
	public UsrSettingVo findVo(Integer usrId, String usrSetName) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(usrId, usrSetName), UsrSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getUsrId(), vo.getUsrSetName()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
