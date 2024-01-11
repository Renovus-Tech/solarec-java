package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.CliGenUsrAlertVo;
import tech.renovus.solarec.db.data.dao.wrapper.CliGenUsrAlertRowWrapper;

public abstract class BaseCliGenUsrAlertDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_usr_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_usr_alert WHERE cli_id = :cli_idAND gen_id = :gen_idAND usr_id = :usr_idAND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_usr_alert (cli_id, gen_id, usr_id, cli_gen_alert_id, cli_gen_usr_alert_send_date, cli_gen_usr_alert_send_result) VALUES (:cli_id, :gen_id, :usr_id, :cli_gen_alert_id, :cli_gen_usr_alert_send_date, :cli_gen_usr_alert_send_result)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_usr_alert SET cli_gen_usr_alert_send_date = :cli_gen_usr_alert_send_dateAND cli_gen_usr_alert_send_result = :cli_gen_usr_alert_send_result WHERE cli_id = :cli_idAND gen_id = :gen_idAND usr_id = :usr_idAND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_usr_alert WHERE cli_id = :cli_idAND gen_id = :gen_idAND usr_id = :usr_idAND cli_gen_alert_id = :cli_gen_alert_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, usr_id, cli_gen_alert_id) SET cli_gen_usr_alert_send_date = EXCLUDED.cli_gen_usr_alert_send_date, cli_gen_usr_alert_send_result = EXCLUDED.cli_gen_usr_alert_send_result";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliGenUsrAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliGenUsrAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_gen_alert_id", vo.getCliGenAlertId())
			.addValue("cli_gen_usr_alert_send_date", vo.getCliGenUsrAlertSendDate())
			.addValue("cli_gen_usr_alert_send_result", vo.getCliGenUsrAlertSendResult());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliGenUsrAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_gen_usr_alert_send_date", vo.getCliGenUsrAlertSendDate())
			.addValue("cli_gen_usr_alert_send_result", vo.getCliGenUsrAlertSendResult())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("usr_id", vo.getUsrId())
			.addValue("cli_gen_alert_id", vo.getCliGenAlertId());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliGenUsrAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getUsrId(), vo.getCliGenAlertId());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("usr_id", usrId)
			.addValue("cli_gen_alert_id", cliGenAlertId);
	}
	//--- Public methods ------------------------
	public Collection<CliGenUsrAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliGenUsrAlertRowWrapper.getInstance()); }
	public CliGenUsrAlertVo findVo(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, usrId, cliGenAlertId), CliGenUsrAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliGenUsrAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(CliGenUsrAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliGenUsrAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliGenUsrAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliGenUsrAlertVo.SYNC_INSERT: this.insert(vo); break;
			case CliGenUsrAlertVo.SYNC_UPDATE: this.update(vo); break;
			case CliGenUsrAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliGenUsrAlertVo> vos) {
		if (vos == null) return;
		for (CliGenUsrAlertVo vo : vos) this.synchronize(vo);
	}
}
