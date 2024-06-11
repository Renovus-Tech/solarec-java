package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

public abstract class BaseCliGenAlertDao <T extends CliGenAlertVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_alert";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_alert WHERE gen_id = :gen_id AND cli_gen_alert_id_auto = :cli_gen_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_alert (cli_gen_alert_trigger, gen_id, cli_gen_alert_added, cli_gen_alert_type, cli_id, cli_gen_alert_flags, cli_gen_alert_data) VALUES (:cli_gen_alert_trigger, :gen_id, :cli_gen_alert_added, :cli_gen_alert_type, :cli_id, :cli_gen_alert_flags, :cli_gen_alert_data)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_alert SET cli_gen_alert_trigger = :cli_gen_alert_trigger, cli_gen_alert_added = :cli_gen_alert_added, cli_gen_alert_type = :cli_gen_alert_type, cli_gen_alert_flags = :cli_gen_alert_flags, cli_gen_alert_data = :cli_gen_alert_data WHERE gen_id = :gen_id AND cli_gen_alert_id_auto = :cli_gen_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_alert WHERE gen_id = :gen_id AND cli_gen_alert_id_auto = :cli_gen_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (gen_id, cli_gen_alert_id_auto, cli_id) DO UPDATE SET cli_gen_alert_trigger = EXCLUDED.cli_gen_alert_trigger, cli_gen_alert_added = EXCLUDED.cli_gen_alert_added, cli_gen_alert_type = EXCLUDED.cli_gen_alert_type, cli_gen_alert_flags = EXCLUDED.cli_gen_alert_flags, cli_gen_alert_data = EXCLUDED.cli_gen_alert_data";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_gen_alert_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliGenAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TRIGGER, vo.getCliGenAlertTrigger())
			.addValue(CliGenAlertVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ID, vo.getCliGenAlertId())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ADDED, vo.getCliGenAlertAdded())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TYPE, vo.getCliGenAlertType())
			.addValue(CliGenAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_FLAGS, vo.getCliGenAlertFlags())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_DATA, vo.getCliGenAlertData());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TRIGGER, vo.getCliGenAlertTrigger())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ADDED, vo.getCliGenAlertAdded())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TYPE, vo.getCliGenAlertType())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_FLAGS, vo.getCliGenAlertFlags())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_DATA, vo.getCliGenAlertData())
			.addValue(CliGenAlertVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ID, vo.getCliGenAlertId())
			.addValue(CliGenAlertVo.COLUMN_CLI_ID, vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getGenId(), vo.getCliGenAlertId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer genId, Integer cliGenAlertId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue(CliGenAlertVo.COLUMN_GEN_ID, genId)
			.addValue(CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ID, cliGenAlertId)
			.addValue(CliGenAlertVo.COLUMN_CLI_ID, cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliGenAlertRowWrapper.getInstance()); }
	public CliGenAlertVo findVo(Integer genId, Integer cliGenAlertId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(genId, cliGenAlertId, cliId), CliGenAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setCliGenAlertId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getGenId(), vo.getCliGenAlertId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
