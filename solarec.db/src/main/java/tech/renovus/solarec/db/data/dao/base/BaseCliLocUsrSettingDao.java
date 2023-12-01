package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.CliLocUsrSettingVo;
import tech.renovus.solarec.db.data.dao.wrapper.CliLocUsrSettingRowWrapper;

public abstract class BaseCliLocUsrSettingDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_usr_setting";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_usr_setting WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_id";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_usr_setting (cli_id, loc_id, usr_id, cli_loc_alert_flags) VALUES (:cli_id, :loc_id, :usr_id, :cli_loc_alert_flags)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_usr_setting SET cli_loc_alert_flags = :cli_loc_alert_flags WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_id";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_usr_setting WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id) SET cli_loc_alert_flags = EXCLUDED.cli_loc_alert_flags";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliLocUsrSettingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliLocUsrSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_loc_alert_flags", vo.getCliLocAlertFlags());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliLocUsrSettingVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_loc_alert_flags", vo.getCliLocAlertFlags())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliLocUsrSettingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("usr_id", usrId);
	}
	//--- Public methods ------------------------
	public Collection<CliLocUsrSettingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliLocUsrSettingRowWrapper.getInstance()); }
	public CliLocUsrSettingVo findVo(Integer cliId, Integer locId, Integer usrId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId), CliLocUsrSettingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliLocUsrSettingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(CliLocUsrSettingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliLocUsrSettingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliLocUsrSettingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliLocUsrSettingVo.SYNC_INSERT: this.insert(vo); break;
			case CliLocUsrSettingVo.SYNC_UPDATE: this.update(vo); break;
			case CliLocUsrSettingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliLocUsrSettingVo> vos) {
		if (vos == null) return;
		for (CliLocUsrSettingVo vo : vos) this.synchronize(vo);
	}
}
