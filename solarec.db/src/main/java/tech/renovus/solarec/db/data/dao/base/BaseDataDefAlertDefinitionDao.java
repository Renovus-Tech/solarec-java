package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefAlertDefinitionRowWrapper;
import tech.renovus.solarec.db.data.vo.DataDefAlertDefinitionVo;

public abstract class BaseDataDefAlertDefinitionDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_def_alert_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_def_alert_definition WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_INSERT					= "INSERT INTO data_def_alert_definition (data_def_id,alert_def_id,alert_def_call_order) VALUES (:data_def_id,:alert_def_id,:alert_def_call_order)";
	protected String SQL_UPDATE					= "UPDATE data_def_alert_definition SET alert_def_call_order = :alert_def_call_order WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_DELETE					= "DELETE FROM data_def_alert_definition WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id, alert_def_id) DO UPDATE SET alert_def_call_order = EXCLUDED.alert_def_call_order";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataDefAlertDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DataDefAlertDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("alert_def_id", vo.getAlertDefId())
			.addValue("alert_def_call_order", vo.getAlertDefCallOrder());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DataDefAlertDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("alert_def_call_order", vo.getAlertDefCallOrder())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("alert_def_id", vo.getAlertDefId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DataDefAlertDefinitionVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId(), vo.getAlertDefId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId, Integer alertDefId) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", dataDefId)
			.addValue("alert_def_id", alertDefId);
	}

	//--- Public methods ------------------------
	public Collection<DataDefAlertDefinitionVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DataDefAlertDefinitionRowWrapper.getInstance()); }
	public DataDefAlertDefinitionVo findVo(Integer dataDefId, Integer alertDefId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId, alertDefId), DataDefAlertDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DataDefAlertDefinitionVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DataDefAlertDefinitionVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DataDefAlertDefinitionVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DataDefAlertDefinitionVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DataDefAlertDefinitionVo.SYNC_INSERT: this.insert(vo); break;
			case DataDefAlertDefinitionVo.SYNC_UPDATE: this.update(vo); break;
			case DataDefAlertDefinitionVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DataDefAlertDefinitionVo> vos) {
		if (vos == null) return;
		for (DataDefAlertDefinitionVo vo : vos) this.synchronize(vo);
}


}

