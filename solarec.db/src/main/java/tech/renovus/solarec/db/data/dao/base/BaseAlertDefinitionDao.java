package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.AlertDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;

public abstract class BaseAlertDefinitionDao <T extends AlertDefinitionVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM alert_definition";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM alert_definition WHERE alert_def_id_auto = :alert_def_id_auto";
	protected String SQL_INSERT					= "INSERT INTO alert_definition (alert_def_name, alert_def_description, alert_def_executable, alert_def_flags) VALUES (:alert_def_name, :alert_def_description, :alert_def_executable, :alert_def_flags)";
	protected String SQL_UPDATE					= "UPDATE alert_definition SET alert_def_name = :alert_def_name, alert_def_description = :alert_def_description, alert_def_executable = :alert_def_executable, alert_def_flags = :alert_def_flags WHERE alert_def_id_auto = :alert_def_id_auto";
	protected String SQL_DELETE					= "DELETE FROM alert_definition WHERE alert_def_id_auto = :alert_def_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (alert_def_id_auto) DO UPDATE SET alert_def_name = EXCLUDED.alert_def_name, alert_def_description = EXCLUDED.alert_def_description, alert_def_executable = EXCLUDED.alert_def_executable, alert_def_flags = EXCLUDED.alert_def_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"alert_def_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseAlertDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_NAME, vo.getAlertDefName())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_DESCRIPTION, vo.getAlertDefDescription())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_EXECUTABLE, vo.getAlertDefExecutable())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_FLAGS, vo.getAlertDefFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_NAME, vo.getAlertDefName())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_DESCRIPTION, vo.getAlertDefDescription())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_EXECUTABLE, vo.getAlertDefExecutable())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_FLAGS, vo.getAlertDefFlags())
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_ID, vo.getAlertDefId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getAlertDefId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer alertDefId) {
		return new MapSqlParameterSource()
			.addValue(AlertDefinitionVo.COLUMN_ALERT_DEF_ID, alertDefId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, AlertDefinitionRowWrapper.getInstance()); }
	public AlertDefinitionVo findVo(Integer alertDefId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(alertDefId), AlertDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setAlertDefId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getAlertDefId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
