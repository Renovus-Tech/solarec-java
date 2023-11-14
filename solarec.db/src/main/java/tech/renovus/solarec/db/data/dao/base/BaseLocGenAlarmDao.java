package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocGenAlarmRowWrapper;
import tech.renovus.solarec.db.data.vo.LocGenAlarmVo;

public abstract class BaseLocGenAlarmDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_gen_alarm";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_gen_alarm WHERE cli_id = :cli_id AND loc_id = :loc_id AND alarm_code = :alarm_code";
	protected String SQL_INSERT					= "INSERT INTO loc_gen_alarm (cli_id,loc_id,alarm_code,alarm_description,data_cat_id) VALUES (:cli_id,:loc_id,:alarm_code,:alarm_description,:data_cat_id)";
	protected String SQL_UPDATE					= "UPDATE loc_gen_alarm SET alarm_description = :alarm_description,data_cat_id = :data_cat_id WHERE cli_id = :cli_id AND loc_id = :loc_id AND alarm_code = :alarm_code";
	protected String SQL_DELETE					= "DELETE FROM loc_gen_alarm WHERE cli_id = :cli_id AND loc_id = :loc_id AND alarm_code = :alarm_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, alarm_code) DO UPDATE SET alarm_description = EXCLUDED.alarm_description, data_cat_id = EXCLUDED.data_cat_id";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocGenAlarmDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(LocGenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alarm_code", vo.getAlarmCode())
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(LocGenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("alarm_code", vo.getAlarmCode());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(LocGenAlarmVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getAlarmCode());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Double alarmCode) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("alarm_code", alarmCode);
	}

	//--- Public methods ------------------------
	public Collection<LocGenAlarmVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, LocGenAlarmRowWrapper.getInstance()); }
	public LocGenAlarmVo findVo(Integer cliId, Integer locId, Double alarmCode) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, alarmCode), LocGenAlarmRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(LocGenAlarmVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(LocGenAlarmVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(LocGenAlarmVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(LocGenAlarmVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case LocGenAlarmVo.SYNC_INSERT: this.insert(vo); break;
			case LocGenAlarmVo.SYNC_UPDATE: this.update(vo); break;
			case LocGenAlarmVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<LocGenAlarmVo> vos) {
		if (vos == null) return;
		for (LocGenAlarmVo vo : vos) this.synchronize(vo);
}


}

