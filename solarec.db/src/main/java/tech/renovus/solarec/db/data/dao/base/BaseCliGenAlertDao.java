package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlertRowWrapper;

public abstract class BaseCliGenAlertDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_alert WHERE gen_id = :gen_idAND cli_gen_alert_id_auto = :cli_gen_alert_id_autoAND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_alert (cli_gen_alert_trigger, gen_id, cli_gen_alert_added, cli_gen_alert_type, cli_id, cli_gen_alert_flags, cli_gen_alert_data) VALUES (:cli_gen_alert_trigger, :gen_id, :cli_gen_alert_added, :cli_gen_alert_type, :cli_id, :cli_gen_alert_flags, :cli_gen_alert_data)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_alert SET cli_gen_alert_trigger = :cli_gen_alert_triggerAND cli_gen_alert_added = :cli_gen_alert_addedAND cli_gen_alert_type = :cli_gen_alert_typeAND cli_gen_alert_flags = :cli_gen_alert_flagsAND cli_gen_alert_data = :cli_gen_alert_data WHERE gen_id = :gen_idAND cli_gen_alert_id_auto = :cli_gen_alert_id_autoAND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_alert WHERE gen_id = :gen_idAND cli_gen_alert_id_auto = :cli_gen_alert_id_autoAND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (gen_id, cli_gen_alert_id_auto, cli_id) SET cli_gen_alert_trigger = EXCLUDED.cli_gen_alert_trigger, cli_gen_alert_added = EXCLUDED.cli_gen_alert_added, cli_gen_alert_type = EXCLUDED.cli_gen_alert_type, cli_gen_alert_flags = EXCLUDED.cli_gen_alert_flags, cli_gen_alert_data = EXCLUDED.cli_gen_alert_data";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_gen_alert_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliGenAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliGenAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_gen_alert_trigger", vo.getCliGenAlertTrigger())
			.addValue("gen_id", vo.getGenId())
			.addValue("cli_gen_alert_id_auto", vo.getCliGenAlertId())
			.addValue("cli_gen_alert_added", vo.getCliGenAlertAdded())
			.addValue("cli_gen_alert_type", vo.getCliGenAlertType())
			.addValue("cli_id", vo.getCliId())
			.addValue("cli_gen_alert_flags", vo.getCliGenAlertFlags())
			.addValue("cli_gen_alert_data", vo.getCliGenAlertData());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliGenAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_gen_alert_trigger", vo.getCliGenAlertTrigger())
			.addValue("cli_gen_alert_added", vo.getCliGenAlertAdded())
			.addValue("cli_gen_alert_type", vo.getCliGenAlertType())
			.addValue("cli_gen_alert_flags", vo.getCliGenAlertFlags())
			.addValue("cli_gen_alert_data", vo.getCliGenAlertData())
			.addValue("gen_id", vo.getGenId())
			.addValue("cli_gen_alert_id_auto", vo.getCliGenAlertId())
			.addValue("cli_id", vo.getCliId());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliGenAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getGenId(), vo.getCliGenAlertId(), vo.getCliId());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(Integer genId, Integer cliGenAlertId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue("gen_id", genId)
			.addValue("cli_gen_alert_id_auto", cliGenAlertId)
			.addValue("cli_id", cliId);
	}
	//--- Public methods ------------------------
	public Collection<CliGenAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliGenAlertRowWrapper.getInstance()); }
	public CliGenAlertVo findVo(Integer genId, Integer cliGenAlertId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(genId, cliGenAlertId, cliId), CliGenAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliGenAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setCliGenAlertId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(CliGenAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliGenAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliGenAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliGenAlertVo.SYNC_INSERT: this.insert(vo); break;
			case CliGenAlertVo.SYNC_UPDATE: this.update(vo); break;
			case CliGenAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliGenAlertVo> vos) {
		if (vos == null) return;
		for (CliGenAlertVo vo : vos) this.synchronize(vo);
	}
}
