package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocAlertRowWrapper;
import tech.renovus.solarec.db.data.vo.LocAlertVo;

public abstract class BaseLocAlertDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_alert WHERE cli_id = :cli_id AND loc_id = :loc_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_INSERT					= "INSERT INTO loc_alert (cli_id,loc_id,alert_def_id,alert_date_added,alert_date_send,alert_message,alert_pro_id) VALUES (:cli_id,:loc_id,:alert_def_id,:alert_date_added,:alert_date_send,:alert_message,:alert_pro_id)";
	protected String SQL_UPDATE					= "UPDATE loc_alert SET alert_date_send = :alert_date_send,alert_message = :alert_message,alert_pro_id = :alert_pro_id WHERE cli_id = :cli_id AND loc_id = :loc_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_DELETE					= "DELETE FROM loc_alert WHERE cli_id = :cli_id AND loc_id = :loc_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, alert_def_id, alert_date_added) DO UPDATE SET alert_date_send = EXCLUDED.alert_date_send, alert_message = EXCLUDED.alert_message, alert_pro_id = EXCLUDED.alert_pro_id";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_date_added", vo.getAlertDateAdded())
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage())
			.addValue("alert_pro_id", vo.getAlertProId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage())
			.addValue("alert_pro_id", vo.getAlertProId())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_date_added", vo.getAlertDateAdded());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getAlertDefId(), vo.getAlertDateAdded());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("alert_def_id", alertDefId)
			.addValue("alert_date_added", alertDateAdded);
	}

	//--- Public methods ------------------------
	public Collection<LocAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocAlertRowWrapper.getInstance()); }
	public LocAlertVo findVo(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, alertDefId, alertDateAdded), LocAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocAlertVo.SYNC_INSERT: this.insert(vo); break;
			case LocAlertVo.SYNC_UPDATE: this.update(vo); break;
			case LocAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocAlertVo> vos) {
		if (vos == null) return;
		for (LocAlertVo vo : vos) this.synchronize(vo);
}


}

