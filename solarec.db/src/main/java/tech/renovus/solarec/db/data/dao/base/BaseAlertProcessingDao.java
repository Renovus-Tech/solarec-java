package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.AlertProcessingRowWrapper;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;

public abstract class BaseAlertProcessingDao <T extends AlertProcessingVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM alert_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM alert_processing WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_INSERT					= "INSERT INTO alert_processing (alert_pro_result, cli_id, loc_id, alert_def_id, alert_pro_date_start, alert_pro_date_end, alert_pro_file_log, alert_pro_file_name) VALUES (:alert_pro_result, :cli_id, :loc_id, :alert_def_id, :alert_pro_date_start, :alert_pro_date_end, :alert_pro_file_log, :alert_pro_file_name)";
	protected String SQL_UPDATE					= "UPDATE alert_processing SET alert_pro_result = :alert_pro_result, cli_id = :cli_id, loc_id = :loc_id, alert_def_id = :alert_def_id, alert_pro_date_start = :alert_pro_date_start, alert_pro_date_end = :alert_pro_date_end, alert_pro_file_log = :alert_pro_file_log, alert_pro_file_name = :alert_pro_file_name WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_DELETE					= "DELETE FROM alert_processing WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (alert_pro_id_auto) DO UPDATE SET alert_pro_result = EXCLUDED.alert_pro_result, cli_id = EXCLUDED.cli_id, loc_id = EXCLUDED.loc_id, alert_def_id = EXCLUDED.alert_def_id, alert_pro_date_start = EXCLUDED.alert_pro_date_start, alert_pro_date_end = EXCLUDED.alert_pro_date_end, alert_pro_file_log = EXCLUDED.alert_pro_file_log, alert_pro_file_name = EXCLUDED.alert_pro_file_name";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"alert_pro_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseAlertProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("alert_pro_result", vo.getAlertProResult())
			.addValue("alert_pro_id_auto", vo.getAlertProId())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_pro_date_start", vo.getAlertProDateStart())
			.addValue("alert_pro_date_end", vo.getAlertProDateEnd())
			.addValue("alert_pro_file_log", vo.getAlertProFileLog())
			.addValue("alert_pro_file_name", vo.getAlertProFileName());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("alert_pro_result", vo.getAlertProResult())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_pro_date_start", vo.getAlertProDateStart())
			.addValue("alert_pro_date_end", vo.getAlertProDateEnd())
			.addValue("alert_pro_file_log", vo.getAlertProFileLog())
			.addValue("alert_pro_file_name", vo.getAlertProFileName())
			.addValue("alert_pro_id_auto", vo.getAlertProId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getAlertProId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer alertProId) {
		return new MapSqlParameterSource()
			.addValue("alert_pro_id_auto", alertProId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, AlertProcessingRowWrapper.getInstance()); }
	public AlertProcessingVo findVo(Integer alertProId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(alertProId), AlertProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		vo.setAlertProId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getAlertProId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
