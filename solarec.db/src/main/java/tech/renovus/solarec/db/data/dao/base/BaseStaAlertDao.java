package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaAlertRowWrapper;
import tech.renovus.solarec.db.data.vo.StaAlertVo;

public abstract class BaseStaAlertDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM sta_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_alert WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_INSERT					= "INSERT INTO sta_alert (cli_id,sta_id,alert_def_id,alert_date_added,alert_date_send,alert_message,alert_pro_id) VALUES (:cli_id,:sta_id,:alert_def_id,:alert_date_added,:alert_date_send,:alert_message,:alert_pro_id)";
	protected String SQL_UPDATE					= "UPDATE sta_alert SET alert_date_send = :alert_date_send,alert_message = :alert_message,alert_pro_id = :alert_pro_id WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_DELETE					= "DELETE FROM sta_alert WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, alert_def_id, alert_date_added) DO UPDATE SET alert_date_send = EXCLUDED.alert_date_send, alert_message = EXCLUDED.alert_message, alert_pro_id = EXCLUDED.alert_pro_id";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStaAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StaAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_date_added", vo.getAlertDateAdded())
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage())
			.addValue("alert_pro_id", vo.getAlertProId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StaAlertVo vo) {
		return new MapSqlParameterSource()
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage())
			.addValue("alert_pro_id", vo.getAlertProId())
			.addValue("cli_id", vo.getCliId())
			.addValue("sta_id", vo.getStaId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_date_added", vo.getAlertDateAdded());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StaAlertVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getAlertDefId(), vo.getAlertDateAdded());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("sta_id", staId)
			.addValue("alert_def_id", alertDefId)
			.addValue("alert_date_added", alertDateAdded);
	}

	//--- Public methods ------------------------
	public Collection<StaAlertVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StaAlertRowWrapper.getInstance()); }
	public StaAlertVo findVo(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, alertDefId, alertDateAdded), StaAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StaAlertVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StaAlertVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StaAlertVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StaAlertVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StaAlertVo.SYNC_INSERT: this.insert(vo); break;
			case StaAlertVo.SYNC_UPDATE: this.update(vo); break;
			case StaAlertVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StaAlertVo> vos) {
		if (vos == null) return;
		for (StaAlertVo vo : vos) this.synchronize(vo);
}


}

