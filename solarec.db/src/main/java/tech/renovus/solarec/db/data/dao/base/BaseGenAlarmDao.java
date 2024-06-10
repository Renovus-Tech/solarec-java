package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenAlarmRowWrapper;
import tech.renovus.solarec.vo.db.data.GenAlarmVo;

public abstract class BaseGenAlarmDao <T extends GenAlarmVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_alarm";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_alarm WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_INSERT					= "INSERT INTO gen_alarm (cli_id, gen_id, alarm_code, data_cat_id, alarm_description) VALUES (:cli_id, :gen_id, :alarm_code, :data_cat_id, :alarm_description)";
	protected String SQL_UPDATE					= "UPDATE gen_alarm SET data_cat_id = :data_cat_id, alarm_description = :alarm_description WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_DELETE					= "DELETE FROM gen_alarm WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, alarm_code) DO UPDATE SET data_cat_id = EXCLUDED.data_cat_id, alarm_description = EXCLUDED.alarm_description";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenAlarmDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenAlarmVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenAlarmVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenAlarmVo.COLUMN_ALARM_CODE, vo.getAlarmCode())
			.addValue(GenAlarmVo.COLUMN_DATA_CAT_ID, vo.getDataCatId())
			.addValue(GenAlarmVo.COLUMN_ALARM_DESCRIPTION, vo.getAlarmDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenAlarmVo.COLUMN_DATA_CAT_ID, vo.getDataCatId())
			.addValue(GenAlarmVo.COLUMN_ALARM_DESCRIPTION, vo.getAlarmDescription())
			.addValue(GenAlarmVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenAlarmVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenAlarmVo.COLUMN_ALARM_CODE, vo.getAlarmCode());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getAlarmCode());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Double alarmCode) {
		return new MapSqlParameterSource()
			.addValue(GenAlarmVo.COLUMN_CLI_ID, cliId)
			.addValue(GenAlarmVo.COLUMN_GEN_ID, genId)
			.addValue(GenAlarmVo.COLUMN_ALARM_CODE, alarmCode);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenAlarmRowWrapper.getInstance()); }
	public GenAlarmVo findVo(Integer cliId, Integer genId, Double alarmCode) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, alarmCode), GenAlarmRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getAlarmCode()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
