package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliLocUsrSettingRowWrapper;
import tech.renovus.solarec.vo.db.data.CliLocUsrSettingVo;

public abstract class BaseCliLocUsrSettingDao <T extends CliLocUsrSettingVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_usr_setting";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_usr_setting WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_usr_setting (cli_id, loc_id, usr_id, cli_loc_alert_flags) VALUES (:cli_id, :loc_id, :usr_id, :cli_loc_alert_flags)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_usr_setting SET cli_loc_alert_flags = :cli_loc_alert_flags WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_usr_setting WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id) DO UPDATE SET cli_loc_alert_flags = EXCLUDED.cli_loc_alert_flags";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliLocUsrSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrSettingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliLocUsrSettingVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocUsrSettingVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliLocUsrSettingVo.COLUMN_CLI_LOC_ALERT_FLAGS, vo.getCliLocAlertFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrSettingVo.COLUMN_CLI_LOC_ALERT_FLAGS, vo.getCliLocAlertFlags())
			.addValue(CliLocUsrSettingVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliLocUsrSettingVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocUsrSettingVo.COLUMN_USR_ID, vo.getUsrId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrSettingVo.COLUMN_CLI_ID, cliId)
			.addValue(CliLocUsrSettingVo.COLUMN_LOC_ID, locId)
			.addValue(CliLocUsrSettingVo.COLUMN_USR_ID, usrId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliLocUsrSettingRowWrapper.getInstance()); }
	public CliLocUsrSettingVo findVo(Integer cliId, Integer locId, Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId), CliLocUsrSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getUsrId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
