package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliLocUsrAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliLocUsrAlertVo;

public abstract class BaseCliLocUsrAlertDao <T extends CliLocUsrAlertVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_usr_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_usr_alert WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_usr_alert (cli_id, loc_id, usr_id, cli_loc_alert_id, cli_loc_usr_alert_send_date, cli_loc_usr_alert_send_result) VALUES (:cli_id, :loc_id, :usr_id, :cli_loc_alert_id, :cli_loc_usr_alert_send_date, :cli_loc_usr_alert_send_result)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_usr_alert SET cli_loc_usr_alert_send_date = :cli_loc_usr_alert_send_date, cli_loc_usr_alert_send_result = :cli_loc_usr_alert_send_result WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_usr_alert WHERE cli_id = :cli_id AND loc_id = :loc_id AND usr_id = :usr_id AND cli_loc_alert_id = :cli_loc_alert_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, usr_id, cli_loc_alert_id) DO UPDATE SET cli_loc_usr_alert_send_date = EXCLUDED.cli_loc_usr_alert_send_date, cli_loc_usr_alert_send_result = EXCLUDED.cli_loc_usr_alert_send_result";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliLocUsrAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliLocUsrAlertVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocUsrAlertVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_ALERT_ID, vo.getCliLocAlertId())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_DATE, vo.getCliLocUsrAlertSendDate())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_RESULT, vo.getCliLocUsrAlertSendResult());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_DATE, vo.getCliLocUsrAlertSendDate())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_RESULT, vo.getCliLocUsrAlertSendResult())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliLocUsrAlertVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocUsrAlertVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_ALERT_ID, vo.getCliLocAlertId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getCliLocAlertId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) {
		return new MapSqlParameterSource()
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_ID, cliId)
			.addValue(CliLocUsrAlertVo.COLUMN_LOC_ID, locId)
			.addValue(CliLocUsrAlertVo.COLUMN_USR_ID, usrId)
			.addValue(CliLocUsrAlertVo.COLUMN_CLI_LOC_ALERT_ID, cliLocAlertId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliLocUsrAlertRowWrapper.getInstance()); }
	public CliLocUsrAlertVo findVo(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, usrId, cliLocAlertId), CliLocUsrAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getUsrId(), vo.getCliLocAlertId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
