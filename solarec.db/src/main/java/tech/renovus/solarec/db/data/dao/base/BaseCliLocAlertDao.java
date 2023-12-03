package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.db.data.dao.wrapper.CliLocAlertRowWrapper;

public abstract class BaseCliLocAlertDao {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_loc_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_loc_alert WHERE cli_id = :cli_idAND loc_id = :loc_idAND cli_loc_alert_id_auto = :cli_loc_alert_id_auto";
	protected String SQL_INSERT					= "INSERT INTO cli_loc_alert (cli_id, loc_id, cli_loc_alert_added, cli_loc_alert_type, cli_loc_alert_data, cli_loc_alert_flags) VALUES (:cli_id, :loc_id, :cli_loc_alert_added, :cli_loc_alert_type, :cli_loc_alert_data, :cli_loc_alert_flags)";
	protected String SQL_UPDATE					= "UPDATE cli_loc_alert SET cli_loc_alert_added = :cli_loc_alert_addedAND cli_loc_alert_type = :cli_loc_alert_typeAND cli_loc_alert_data = :cli_loc_alert_dataAND cli_loc_alert_flags = :cli_loc_alert_flags WHERE cli_id = :cli_idAND loc_id = :loc_idAND cli_loc_alert_id_auto = :cli_loc_alert_id_auto";
	protected String SQL_DELETE					= "DELETE FROM cli_loc_alert WHERE cli_id = :cli_idAND loc_id = :loc_idAND cli_loc_alert_id_auto = :cli_loc_alert_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, cli_loc_alert_id_auto) SET cli_loc_alert_added = EXCLUDED.cli_loc_alert_added, cli_loc_alert_type = EXCLUDED.cli_loc_alert_type, cli_loc_alert_data = EXCLUDED.cli_loc_alert_data, cli_loc_alert_flags = EXCLUDED.cli_loc_alert_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"cli_loc_alert_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliLocAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliLocAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("cli_loc_alert_id_auto", vo.getCliLocAlertId())
			.addValue("cli_loc_alert_added", vo.getCliLocAlertAdded())
			.addValue("cli_loc_alert_type", vo.getCliLocAlertType())
			.addValue("cli_loc_alert_data", vo.getCliLocAlertData())
			.addValue("cli_loc_alert_flags", vo.getCliLocAlertFlags());
	}
	
	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliLocAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_loc_alert_added", vo.getCliLocAlertAdded())
			.addValue("cli_loc_alert_type", vo.getCliLocAlertType())
			.addValue("cli_loc_alert_data", vo.getCliLocAlertData())
			.addValue("cli_loc_alert_flags", vo.getCliLocAlertFlags())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("cli_loc_alert_id_auto", vo.getCliLocAlertId());
	}
	
	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliLocAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getCliLocAlertId());
	}
	
	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer cliLocAlertId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("cli_loc_alert_id_auto", cliLocAlertId);
	}
	//--- Public methods ------------------------
	public Collection<CliLocAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliLocAlertRowWrapper.getInstance()); }
	public CliLocAlertVo findVo(Integer cliId, Integer locId, Integer cliLocAlertId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, cliLocAlertId), CliLocAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliLocAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setCliLocAlertId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(CliLocAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliLocAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliLocAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliLocAlertVo.SYNC_INSERT: this.insert(vo); break;
			case CliLocAlertVo.SYNC_UPDATE: this.update(vo); break;
			case CliLocAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliLocAlertVo> vos) {
		if (vos == null) return;
		for (CliLocAlertVo vo : vos) this.synchronize(vo);
	}
}
