package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.UsrSettingRowWrapper;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;

public abstract class BaseUsrSettingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM usr_setting";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM usr_setting WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_INSERT					= "INSERT INTO usr_setting (usr_id,usr_set_name,usr_set_value) VALUES (:usr_id,:usr_set_name,:usr_set_value)";
	protected String SQL_UPDATE					= "UPDATE usr_setting SET usr_set_value = :usr_set_value WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_DELETE					= "DELETE FROM usr_setting WHERE usr_id = :usr_id AND usr_set_name = :usr_set_name";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (usr_id, usr_set_name) DO UPDATE SET usr_set_value = EXCLUDED.usr_set_value";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseUsrSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(UsrSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_id", vo.getUsrId())
			.addValue("usr_set_name", vo.getUsrSetName())
			.addValue("usr_set_value", vo.getUsrSetValue());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(UsrSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("usr_set_value", vo.getUsrSetValue())
			.addValue("usr_id", vo.getUsrId())
			.addValue("usr_set_name", vo.getUsrSetName());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(UsrSettingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getUsrId(), vo.getUsrSetName());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer usrId, String usrSetName) {
		return new MapSqlParameterSource()
			.addValue("usr_id", usrId)
			.addValue("usr_set_name", usrSetName);
	}

	//--- Public methods ------------------------
	public Collection<UsrSettingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, UsrSettingRowWrapper.getInstance()); }
	public UsrSettingVo findVo(Integer usrId, String usrSetName) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(usrId, usrSetName), UsrSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(UsrSettingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(UsrSettingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(UsrSettingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(UsrSettingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case UsrSettingVo.SYNC_INSERT: this.insert(vo); break;
			case UsrSettingVo.SYNC_UPDATE: this.update(vo); break;
			case UsrSettingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<UsrSettingVo> vos) {
		if (vos == null) return;
		for (UsrSettingVo vo : vos) this.synchronize(vo);
}


}

