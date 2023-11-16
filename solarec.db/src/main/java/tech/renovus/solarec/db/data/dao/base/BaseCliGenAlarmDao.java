package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliGenAlarmRowWrapper;
import tech.renovus.solarec.vo.db.data.CliGenAlarmVo;

public abstract class BaseCliGenAlarmDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_gen_alarm";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_gen_alarm WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_INSERT					= "INSERT INTO cli_gen_alarm (cli_id,alarm_code,alarm_description,data_cat_id) VALUES (:cli_id,:alarm_code,:alarm_description,:data_cat_id)";
	protected String SQL_UPDATE					= "UPDATE cli_gen_alarm SET alarm_description = :alarm_description,data_cat_id = :data_cat_id WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_DELETE					= "DELETE FROM cli_gen_alarm WHERE cli_id = :cli_id AND alarm_code = :alarm_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, alarm_code) DO UPDATE SET alarm_description = EXCLUDED.alarm_description, data_cat_id = EXCLUDED.data_cat_id";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliGenAlarmDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliGenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("alarm_code", vo.getAlarmCode())
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliGenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId())
			.addValue("cli_id", vo.getCliId())
			.addValue("alarm_code", vo.getAlarmCode());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliGenAlarmVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getAlarmCode());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Double alarmCode) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("alarm_code", alarmCode);
	}

	//--- Public methods ------------------------
	public Collection<CliGenAlarmVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliGenAlarmRowWrapper.getInstance()); }
	public CliGenAlarmVo findVo(Integer cliId, Double alarmCode) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, alarmCode), CliGenAlarmRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliGenAlarmVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(CliGenAlarmVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliGenAlarmVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliGenAlarmVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliGenAlarmVo.SYNC_INSERT: this.insert(vo); break;
			case CliGenAlarmVo.SYNC_UPDATE: this.update(vo); break;
			case CliGenAlarmVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliGenAlarmVo> vos) {
		if (vos == null) return;
		for (CliGenAlarmVo vo : vos) this.synchronize(vo);
}


}

