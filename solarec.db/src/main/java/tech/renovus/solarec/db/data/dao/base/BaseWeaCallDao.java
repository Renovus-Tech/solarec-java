package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.WeaCallRowWrapper;
import tech.renovus.solarec.vo.db.data.WeaCallVo;

public abstract class BaseWeaCallDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM wea_call";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM wea_call WHERE cli_id = :cli_id AND wea_id = :wea_id AND wea_call_id_auto = :wea_call_id_auto";
	protected String SQL_INSERT					= "INSERT INTO wea_call (cli_id,wea_id,wea_call_date,wea_call_resonse_status,wea_call_response) VALUES (:cli_id,:wea_id,:wea_call_date,:wea_call_resonse_status,:wea_call_response)";
	protected String SQL_UPDATE					= "UPDATE wea_call SET wea_call_date = :wea_call_date,wea_call_resonse_status = :wea_call_resonse_status,wea_call_response = :wea_call_response WHERE cli_id = :cli_id AND wea_id = :wea_id AND wea_call_id_auto = :wea_call_id_auto";
	protected String SQL_DELETE					= "DELETE FROM wea_call WHERE cli_id = :cli_id AND wea_id = :wea_id AND wea_call_id_auto = :wea_call_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, wea_id, wea_call_id_auto) DO UPDATE SET wea_call_date = EXCLUDED.wea_call_date, wea_call_resonse_status = EXCLUDED.wea_call_resonse_status, wea_call_response = EXCLUDED.wea_call_response";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"wea_call_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseWeaCallDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(WeaCallVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_id", vo.getWeaId())
			.addValue("wea_call_date", vo.getWeaCallDate())
			.addValue("wea_call_resonse_status", vo.getWeaCallResonseStatus())
			.addValue("wea_call_response", vo.getWeaCallResponse());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(WeaCallVo vo) {
		return new MapSqlParameterSource()
			.addValue("wea_call_date", vo.getWeaCallDate())
			.addValue("wea_call_resonse_status", vo.getWeaCallResonseStatus())
			.addValue("wea_call_response", vo.getWeaCallResponse())
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_id", vo.getWeaId())
			.addValue("wea_call_id_auto", vo.getWeaCallId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(WeaCallVo vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getWeaId(), vo.getWeaCallId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer weaId, Integer weaCallId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("wea_id", weaId)
			.addValue("wea_call_id_auto", weaCallId);
	}

	//--- Public methods ------------------------
	public Collection<WeaCallVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, WeaCallRowWrapper.getInstance()); }
	public WeaCallVo findVo(Integer cliId, Integer weaId, Integer weaCallId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, weaId, weaCallId), WeaCallRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(WeaCallVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setWeaCallId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(WeaCallVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(WeaCallVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(WeaCallVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case WeaCallVo.SYNC_INSERT: this.insert(vo); break;
			case WeaCallVo.SYNC_UPDATE: this.update(vo); break;
			case WeaCallVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<WeaCallVo> vos) {
		if (vos == null) return;
		for (WeaCallVo vo : vos) this.synchronize(vo);
}


}

