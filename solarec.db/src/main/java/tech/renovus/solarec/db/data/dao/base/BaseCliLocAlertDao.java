package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliLocAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

public abstract class BaseCliLocAlertDao <T extends CliLocAlertVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_alert";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_alert WHERE loc_id = :loc_id AND cli_loc_alert_id_auto = :cli_loc_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_alert (cli_loc_alert_trigger, loc_id, cli_loc_alert_added, cli_loc_alert_type, cli_id, cli_loc_alert_flags, cli_loc_alert_data) VALUES (:cli_loc_alert_trigger, :loc_id, :cli_loc_alert_added, :cli_loc_alert_type, :cli_id, :cli_loc_alert_flags, :cli_loc_alert_data)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_alert SET cli_loc_alert_trigger = :cli_loc_alert_trigger, cli_loc_alert_added = :cli_loc_alert_added, cli_loc_alert_type = :cli_loc_alert_type, cli_loc_alert_flags = :cli_loc_alert_flags, cli_loc_alert_data = :cli_loc_alert_data WHERE loc_id = :loc_id AND cli_loc_alert_id_auto = :cli_loc_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_alert WHERE loc_id = :loc_id AND cli_loc_alert_id_auto = :cli_loc_alert_id_auto AND cli_id = :cli_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (loc_id, cli_loc_alert_id_auto, cli_id) DO UPDATE SET cli_loc_alert_trigger = EXCLUDED.cli_loc_alert_trigger, cli_loc_alert_added = EXCLUDED.cli_loc_alert_added, cli_loc_alert_type = EXCLUDED.cli_loc_alert_type, cli_loc_alert_flags = EXCLUDED.cli_loc_alert_flags, cli_loc_alert_data = EXCLUDED.cli_loc_alert_data";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_loc_alert_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliLocAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_TRIGGER, vo.getCliLocAlertTrigger())
			.addValue(CliLocAlertVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ID, vo.getCliLocAlertId())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ADDED, vo.getCliLocAlertAdded())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_TYPE, vo.getCliLocAlertType())
			.addValue(CliLocAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_FLAGS, vo.getCliLocAlertFlags())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_DATA, vo.getCliLocAlertData());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_TRIGGER, vo.getCliLocAlertTrigger())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ADDED, vo.getCliLocAlertAdded())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_TYPE, vo.getCliLocAlertType())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_FLAGS, vo.getCliLocAlertFlags())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_DATA, vo.getCliLocAlertData())
			.addValue(CliLocAlertVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ID, vo.getCliLocAlertId())
			.addValue(CliLocAlertVo.COLUMN_CLI_ID, vo.getCliId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getLocId(), vo.getCliLocAlertId(), vo.getCliId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer locId, Integer cliLocAlertId, Integer cliId) {
		return new MapSqlParameterSource()
			.addValue(CliLocAlertVo.COLUMN_LOC_ID, locId)
			.addValue(CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ID, cliLocAlertId)
			.addValue(CliLocAlertVo.COLUMN_CLI_ID, cliId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliLocAlertRowWrapper.getInstance()); }
	public CliLocAlertVo findVo(Integer locId, Integer cliLocAlertId, Integer cliId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(locId, cliLocAlertId, cliId), CliLocAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setCliLocAlertId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getLocId(), vo.getCliLocAlertId(), vo.getCliId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
