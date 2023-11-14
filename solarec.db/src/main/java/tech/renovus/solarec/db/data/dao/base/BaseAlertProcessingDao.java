package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.AlertProcessingRowWrapper;
import tech.renovus.solarec.db.data.vo.AlertProcessingVo;

public abstract class BaseAlertProcessingDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM alert_processing";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM alert_processing WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_INSERT					= "INSERT INTO alert_processing (alert_def_id,cli_id,loc_id,alert_pro_file_name,alert_pro_date_start,alert_pro_date_end,alert_pro_result,alert_pro_file_log) VALUES (:alert_def_id,:cli_id,:loc_id,:alert_pro_file_name,:alert_pro_date_start,:alert_pro_date_end,:alert_pro_result,:alert_pro_file_log)";
	protected String SQL_UPDATE					= "UPDATE alert_processing SET alert_def_id = :alert_def_id,cli_id = :cli_id,loc_id = :loc_id,alert_pro_file_name = :alert_pro_file_name,alert_pro_date_start = :alert_pro_date_start,alert_pro_date_end = :alert_pro_date_end,alert_pro_result = :alert_pro_result,alert_pro_file_log = :alert_pro_file_log WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_DELETE					= "DELETE FROM alert_processing WHERE alert_pro_id_auto = :alert_pro_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (alert_pro_id_auto) DO UPDATE SET alert_def_id = EXCLUDED.alert_def_id, cli_id = EXCLUDED.cli_id, loc_id = EXCLUDED.loc_id, alert_pro_file_name = EXCLUDED.alert_pro_file_name, alert_pro_date_start = EXCLUDED.alert_pro_date_start, alert_pro_date_end = EXCLUDED.alert_pro_date_end, alert_pro_result = EXCLUDED.alert_pro_result, alert_pro_file_log = EXCLUDED.alert_pro_file_log";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"alert_pro_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseAlertProcessingDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(AlertProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_pro_file_name", vo.getAlertProFileName())
			.addValue("alert_pro_date_start", vo.getAlertProDateStart())
			.addValue("alert_pro_date_end", vo.getAlertProDateEnd())
			.addValue("alert_pro_result", vo.getAlertProResult())
			.addValue("alert_pro_file_log", vo.getAlertProFileLog());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(AlertProcessingVo vo) {
		return new MapSqlParameterSource()
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alert_pro_file_name", vo.getAlertProFileName())
			.addValue("alert_pro_date_start", vo.getAlertProDateStart())
			.addValue("alert_pro_date_end", vo.getAlertProDateEnd())
			.addValue("alert_pro_result", vo.getAlertProResult())
			.addValue("alert_pro_file_log", vo.getAlertProFileLog())
			.addValue("alert_pro_id_auto", vo.getAlertProId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(AlertProcessingVo vo) {
		return this.createPkMapSqlParameterSource(vo.getAlertProId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer alertProId) {
		return new MapSqlParameterSource()
			.addValue("alert_pro_id_auto", alertProId);
	}

	//--- Public methods ------------------------
	public Collection<AlertProcessingVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, AlertProcessingRowWrapper.getInstance()); }
	public AlertProcessingVo findVo(Integer alertProId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(alertProId), AlertProcessingRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(AlertProcessingVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setAlertProId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(AlertProcessingVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(AlertProcessingVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(AlertProcessingVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case AlertProcessingVo.SYNC_INSERT: this.insert(vo); break;
			case AlertProcessingVo.SYNC_UPDATE: this.update(vo); break;
			case AlertProcessingVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<AlertProcessingVo> vos) {
		if (vos == null) return;
		for (AlertProcessingVo vo : vos) this.synchronize(vo);
}


}

