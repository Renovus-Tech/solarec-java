package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenAlarmRowWrapper;
import tech.renovus.solarec.db.data.vo.GenAlarmVo;

public abstract class BaseGenAlarmDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_alarm";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_alarm WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_INSERT					= "INSERT INTO gen_alarm (cli_id,gen_id,alarm_code,alarm_description,data_cat_id) VALUES (:cli_id,:gen_id,:alarm_code,:alarm_description,:data_cat_id)";
	protected String SQL_UPDATE					= "UPDATE gen_alarm SET alarm_description = :alarm_description,data_cat_id = :data_cat_id WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_DELETE					= "DELETE FROM gen_alarm WHERE cli_id = :cli_id AND gen_id = :gen_id AND alarm_code = :alarm_code";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, alarm_code) DO UPDATE SET alarm_description = EXCLUDED.alarm_description, data_cat_id = EXCLUDED.data_cat_id";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenAlarmDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(GenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("alarm_code", vo.getAlarmCode())
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(GenAlarmVo vo) {
		return new MapSqlParameterSource()
			.addValue("alarm_description", vo.getAlarmDescription())
			.addValue("data_cat_id", vo.getDataCatId())
			.addValue("cli_id", vo.getCliId())
			.addValue("gen_id", vo.getGenId())
			.addValue("alarm_code", vo.getAlarmCode());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(GenAlarmVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getAlarmCode());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Double alarmCode) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("gen_id", genId)
			.addValue("alarm_code", alarmCode);
	}

	//--- Public methods ------------------------
	public Collection<GenAlarmVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, GenAlarmRowWrapper.getInstance()); }
	public GenAlarmVo findVo(Integer cliId, Integer genId, Double alarmCode) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, alarmCode), GenAlarmRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(GenAlarmVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(GenAlarmVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(GenAlarmVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(GenAlarmVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case GenAlarmVo.SYNC_INSERT: this.insert(vo); break;
			case GenAlarmVo.SYNC_UPDATE: this.update(vo); break;
			case GenAlarmVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<GenAlarmVo> vos) {
		if (vos == null) return;
		for (GenAlarmVo vo : vos) this.synchronize(vo);
}


}

