package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.CliLocUsrAlertVo;
import tech.renovus.solarec.db.data.dao.wrapper.CliLocUsrAlertRowWrapper;

public abstract class BaseCliLocUsrAlertDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_usr_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_usr_alert WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_idAND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_usr_alert (cli_id, loc_id, usr_id, cli_loc_alert_id, cli_loc_usr_alert_send_date, cli_loc_usr_alert_send_result) VALUES (:cli_id, :loc_id, :usr_id, :cli_loc_alert_id, :cli_loc_usr_alert_send_date, :cli_loc_usr_alert_send_result)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_usr_alert SET cli_loc_usr_alert_send_date = :cli_loc_usr_alert_send_dateAND cli_loc_usr_alert_send_result = :cli_loc_usr_alert_send_result WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_idAND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_usr_alert WHERE cli_id = :cli_idAND loc_id = :loc_idAND usr_id = :usr_idAND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id, cli_loc_alert_id) SET cli_loc_usr_alert_send_date = EXCLUDED.cli_loc_usr_alert_send_date, cli_loc_usr_alert_send_result = EXCLUDED.cli_loc_usr_alert_send_result";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliLocUsrAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliLocUsrAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_loc_alert_id", vo.getCliLocAlertId())
			.addValue("cli_loc_usr_alert_send_date", vo.getCliLocUsrAlertSendDate())
			.addValue("cli_loc_usr_alert_send_result", vo.getCliLocUsrAlertSendResult());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliLocUsrAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_loc_usr_alert_send_date", vo.getCliLocUsrAlertSendDate())
			.addValue("cli_loc_usr_alert_send_result", vo.getCliLocUsrAlertSendResult())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_loc_alert_id", vo.getCliLocAlertId());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliLocUsrAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getCliLocAlertId());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("usr_id", usrId)
			.addValue("cli_loc_alert_id", cliLocAlertId);
	}
	//--- Public methods ------------------------
	public Collection<CliLocUsrAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliLocUsrAlertRowWrapper.getInstance()); }
	public CliLocUsrAlertVo findVo(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId, cliLocAlertId), CliLocUsrAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliLocUsrAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(CliLocUsrAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliLocUsrAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliLocUsrAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliLocUsrAlertVo.SYNC_INSERT: this.insert(vo); break;
			case CliLocUsrAlertVo.SYNC_UPDATE: this.update(vo); break;
			case CliLocUsrAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliLocUsrAlertVo> vos) {
		if (vos == null) return;
		for (CliLocUsrAlertVo vo : vos) this.synchronize(vo);
	}
}
