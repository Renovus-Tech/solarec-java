package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenAlertRowWrapper;
import tech.renovus.solarec.vo.db.data.GenAlertVo;

public abstract class BaseGenAlertDao <T extends GenAlertVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_alert";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_alert WHERE cli_id = :cli_id AND gen_id = :gen_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_INSERT					= "INSERT INTO gen_alert (cli_id, gen_id, alert_def_id, alert_pro_id, alert_date_added, alert_date_send, alert_message) VALUES (:cli_id, :gen_id, :alert_def_id, :alert_pro_id, :alert_date_added, :alert_date_send, :alert_message)";
	protected String SQL_UPDATE					= "UPDATE gen_alert SET alert_pro_id = :alert_pro_id, alert_date_send = :alert_date_send, alert_message = :alert_message WHERE cli_id = :cli_id AND gen_id = :gen_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_DELETE					= "DELETE FROM gen_alert WHERE cli_id = :cli_id AND gen_id = :gen_id AND alert_def_id = :alert_def_id AND alert_date_added = :alert_date_added";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, alert_def_id, alert_date_added) DO UPDATE SET alert_pro_id = EXCLUDED.alert_pro_id, alert_date_send = EXCLUDED.alert_date_send, alert_message = EXCLUDED.alert_message";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenAlertDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_pro_id", vo.getAlertProId())
			.addValue("alert_date_added", vo.getAlertDateAdded())
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("alert_pro_id", vo.getAlertProId())
			.addValue("alert_date_send", vo.getAlertDateSend())
			.addValue("alert_message", vo.getAlertMessage())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_date_added", vo.getAlertDateAdded());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getAlertDefId(), vo.getAlertDateAdded());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer alertDefId, java.util.Date alertDateAdded) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("alert_def_id", alertDefId)
			.addValue("alert_date_added", alertDateAdded);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenAlertRowWrapper.getInstance()); }
	public GenAlertVo findVo(Integer cliId, Integer genId, Integer alertDefId, java.util.Date alertDateAdded) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, alertDefId, alertDateAdded), GenAlertRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
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
