package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliGenUsrAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliGenUsrAlertVo;

public abstract class BaseCliGenUsrAlertDao <T extends CliGenUsrAlertVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_usr_alert";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_usr_alert WHERE cli_id = :cli_id AND gen_id = :gen_id AND usr_id = :usr_id AND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_usr_alert (cli_id, gen_id, usr_id, cli_gen_alert_id, cli_gen_usr_alert_send_date, cli_gen_usr_alert_send_result) VALUES (:cli_id, :gen_id, :usr_id, :cli_gen_alert_id, :cli_gen_usr_alert_send_date, :cli_gen_usr_alert_send_result)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_usr_alert SET cli_gen_usr_alert_send_date = :cli_gen_usr_alert_send_date, cli_gen_usr_alert_send_result = :cli_gen_usr_alert_send_result WHERE cli_id = :cli_id AND gen_id = :gen_id AND usr_id = :usr_id AND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_usr_alert WHERE cli_id = :cli_id AND gen_id = :gen_id AND usr_id = :usr_id AND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, usr_id, cli_gen_alert_id) DO UPDATE SET cli_gen_usr_alert_send_date = EXCLUDED.cli_gen_usr_alert_send_date, cli_gen_usr_alert_send_result = EXCLUDED.cli_gen_usr_alert_send_result";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliGenUsrAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliGenUsrAlertVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliGenUsrAlertVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_ALERT_ID, vo.getCliGenAlertId())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_DATE, vo.getCliGenUsrAlertSendDate())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_RESULT, vo.getCliGenUsrAlertSendResult());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_DATE, vo.getCliGenUsrAlertSendDate())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_RESULT, vo.getCliGenUsrAlertSendResult())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliGenUsrAlertVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliGenUsrAlertVo.COLUMN_USR_ID, vo.getUsrId())
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_ALERT_ID, vo.getCliGenAlertId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getUsrId(), vo.getCliGenAlertId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) {
		return new MapSqlParameterSource()
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_ID, cliId)
			.addValue(CliGenUsrAlertVo.COLUMN_GEN_ID, genId)
			.addValue(CliGenUsrAlertVo.COLUMN_USR_ID, usrId)
			.addValue(CliGenUsrAlertVo.COLUMN_CLI_GEN_ALERT_ID, cliGenAlertId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliGenUsrAlertRowWrapper.getInstance()); }
	public CliGenUsrAlertVo findVo(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, usrId, cliGenAlertId), CliGenUsrAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getUsrId(), vo.getCliGenAlertId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
