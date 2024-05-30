package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.WeaCallRowWrapper;
import tech.renovus.solarec.vo.db.data.WeaCallVo;

public abstract class BaseWeaCallDao <T extends WeaCallVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM wea_call";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM wea_call WHERE cli_id = :cli_id AND wea_id = :wea_id AND wea_call_id_auto = :wea_call_id_auto";
	protected String SQL_INSERT					= "INSERT INTO wea_call (cli_id, wea_id, wea_call_date, wea_call_resonse_status, wea_call_response) VALUES (:cli_id, :wea_id, :wea_call_date, :wea_call_resonse_status, :wea_call_response)";
	protected String SQL_UPDATE					= "UPDATE wea_call SET wea_call_date = :wea_call_date, wea_call_resonse_status = :wea_call_resonse_status, wea_call_response = :wea_call_response WHERE cli_id = :cli_id AND wea_id = :wea_id AND wea_call_id_auto = :wea_call_id_auto";
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
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_id", vo.getWeaId())
			.addValue("wea_call_id_auto", vo.getWeaCallId())
			.addValue("wea_call_date", vo.getWeaCallDate())
			.addValue("wea_call_resonse_status", vo.getWeaCallResonseStatus())
			.addValue("wea_call_response", vo.getWeaCallResponse());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("wea_call_date", vo.getWeaCallDate())
			.addValue("wea_call_resonse_status", vo.getWeaCallResonseStatus())
			.addValue("wea_call_response", vo.getWeaCallResponse())
			.addValue("cli_id", vo.getCliId())
			.addValue("wea_id", vo.getWeaId())
			.addValue("wea_call_id_auto", vo.getWeaCallId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getWeaId(), vo.getWeaCallId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer weaId, Integer weaCallId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("wea_id", weaId)
			.addValue("wea_call_id_auto", weaCallId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, WeaCallRowWrapper.getInstance()); }
	public WeaCallVo findVo(Integer cliId, Integer weaId, Integer weaCallId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, weaId, weaCallId), WeaCallRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setWeaCallId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getWeaId(), vo.getWeaCallId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
