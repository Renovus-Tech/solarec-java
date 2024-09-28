package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StaAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.StaAlertVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseStaAlertDao <T extends StaAlertVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM sta_alert";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM sta_alert WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_INSERT					= "INSERT INTO sta_alert (cli_id, sta_id, alert_def_id, alert_pro_id, alert_date_added, alert_date_send, alert_message) VALUES (:cli_id, :sta_id, :alert_def_id, :alert_pro_id, :alert_date_added, :alert_date_send, :alert_message)";
	protected String SQL_UPDATE					= "UPDATE sta_alert SET alert_pro_id = :alert_pro_id, alert_date_send = :alert_date_send, alert_message = :alert_message WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_DELETE					= "DELETE FROM sta_alert WHERE cli_id = :cli_id AND sta_id = :sta_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, sta_id, alert_def_id, alert_date_added) DO UPDATE SET alert_pro_id = EXCLUDED.alert_pro_id, alert_date_send = EXCLUDED.alert_date_send, alert_message = EXCLUDED.alert_message";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseStaAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaAlertVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaAlertVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId())
			.addValue(StaAlertVo.COLUMN_ALERT_PRO_ID, vo.getAlertProId())
			.addValue(StaAlertVo.COLUMN_ALERT_DATE_ADDED, vo.getAlertDateAdded())
			.addValue(StaAlertVo.COLUMN_ALERT_DATE_SEND, vo.getAlertDateSend())
			.addValue(StaAlertVo.COLUMN_ALERT_MESSAGE, vo.getAlertMessage());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StaAlertVo.COLUMN_ALERT_PRO_ID, vo.getAlertProId())
			.addValue(StaAlertVo.COLUMN_ALERT_DATE_SEND, vo.getAlertDateSend())
			.addValue(StaAlertVo.COLUMN_ALERT_MESSAGE, vo.getAlertMessage())
			.addValue(StaAlertVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(StaAlertVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(StaAlertVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId())
			.addValue(StaAlertVo.COLUMN_ALERT_DATE_ADDED, vo.getAlertDateAdded());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getStaId(), vo.getAlertDefId(), vo.getAlertDateAdded());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) {
		return new MapSqlParameterSource()
			.addValue(StaAlertVo.COLUMN_CLI_ID, cliId)
			.addValue(StaAlertVo.COLUMN_STA_ID, staId)
			.addValue(StaAlertVo.COLUMN_ALERT_DEF_ID, alertDefId)
			.addValue(StaAlertVo.COLUMN_ALERT_DATE_ADDED, alertDateAdded);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StaAlertRowWrapper.getInstance()); }
	public StaAlertVo findVo(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, staId, alertDefId, alertDateAdded), StaAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getStaId(), vo.getAlertDefId(), vo.getAlertDateAdded()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
