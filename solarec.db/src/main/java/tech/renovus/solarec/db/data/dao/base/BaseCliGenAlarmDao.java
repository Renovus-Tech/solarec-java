package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlarmRowWrapper;
import tech.renovus.solarec.vo.db.data.CliGenAlarmVo;

public abstract class BaseCliGenAlarmDao <T extends CliGenAlarmVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_alarm";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_alarm WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_alarm (cli_id, alarm_code, data_cat_id, alarm_description) VALUES (:cli_id, :alarm_code, :data_cat_id, :alarm_description)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_alarm SET data_cat_id = :data_cat_id, alarm_description = :alarm_description WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_alarm WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, alarm_code) DO UPDATE SET data_cat_id = EXCLUDED.data_cat_id, alarm_description = EXCLUDED.alarm_description";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliGenAlarmDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("alarm_code", vo.getAlarmCode())
			.addValue("data_cat_id", vo.getDataCatId())
			.addValue("alarm_description", vo.getAlarmDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_cat_id", vo.getDataCatId())
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("cli_id", vo.getCliId())
			.addValue("alarm_code", vo.getAlarmCode());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getAlarmCode());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Double alarmCode) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("alarm_code", alarmCode);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliGenAlarmRowWrapper.getInstance()); }
	public CliGenAlarmVo findVo(Integer cliId, Double alarmCode) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, alarmCode), CliGenAlarmRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getAlarmCode()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
