package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefAlertDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefAlertDefinitionVo;

public abstract class BaseDataDefAlertDefinitionDao <T extends DataDefAlertDefinitionVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_def_alert_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_def_alert_definition WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_INSERT					= "INSERT INTO data_def_alert_definition (data_def_id, alert_def_id, alert_def_call_order) VALUES (:data_def_id, :alert_def_id, :alert_def_call_order)";
	protected String SQL_UPDATE					= "UPDATE data_def_alert_definition SET alert_def_call_order = :alert_def_call_order WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_DELETE					= "DELETE FROM data_def_alert_definition WHERE data_def_id = :data_def_id AND alert_def_id = :alert_def_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id, alert_def_id) DO UPDATE SET alert_def_call_order = EXCLUDED.alert_def_call_order";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataDefAlertDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataDefAlertDefinitionVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId())
			.addValue(DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_CALL_ORDER, vo.getAlertDefCallOrder());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_CALL_ORDER, vo.getAlertDefCallOrder())
			.addValue(DataDefAlertDefinitionVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId(), vo.getAlertDefId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId, Integer alertDefId) {
		return new MapSqlParameterSource()
			.addValue(DataDefAlertDefinitionVo.COLUMN_DATA_DEF_ID, dataDefId)
			.addValue(DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_ID, alertDefId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataDefAlertDefinitionRowWrapper.getInstance()); }
	public DataDefAlertDefinitionVo findVo(Integer dataDefId, Integer alertDefId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId, alertDefId), DataDefAlertDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataDefId(), vo.getAlertDefId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
