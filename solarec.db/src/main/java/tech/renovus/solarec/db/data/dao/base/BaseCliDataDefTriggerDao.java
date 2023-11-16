package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliDataDefTriggerRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;

public abstract class BaseCliDataDefTriggerDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_data_def_trigger";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_data_def_trigger WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_INSERT					= "INSERT INTO cli_data_def_trigger (cli_id,loc_id,gen_id,sta_id,data_def_id,tri_source,tri_value,tri_flags,tri_name) VALUES (:cli_id,:loc_id,:gen_id,:sta_id,:data_def_id,:tri_source,:tri_value,:tri_flags,:tri_name)";
	protected String SQL_UPDATE					= "UPDATE cli_data_def_trigger SET cli_id = :cli_id,loc_id = :loc_id,gen_id = :gen_id,sta_id = :sta_id,data_def_id = :data_def_id,tri_source = :tri_source,tri_value = :tri_value,tri_flags = :tri_flags,tri_name = :tri_name WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_DELETE					= "DELETE FROM cli_data_def_trigger WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (tri_id_auto) DO UPDATE SET cli_id = EXCLUDED.cli_id, loc_id = EXCLUDED.loc_id, gen_id = EXCLUDED.gen_id, sta_id = EXCLUDED.sta_id, data_def_id = EXCLUDED.data_def_id, tri_source = EXCLUDED.tri_source, tri_value = EXCLUDED.tri_value, tri_flags = EXCLUDED.tri_flags, tri_name = EXCLUDED.tri_name";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"tri_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliDataDefTriggerDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(CliDataDefTriggerVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("gen_id", vo.getGenId())
			.addValue("sta_id", vo.getStaId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("tri_source", vo.getTriSource())
			.addValue("tri_value", vo.getTriValue())
			.addValue("tri_flags", vo.getTriFlags())
			.addValue("tri_name", vo.getTriName());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(CliDataDefTriggerVo vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("gen_id", vo.getGenId())
			.addValue("sta_id", vo.getStaId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("tri_source", vo.getTriSource())
			.addValue("tri_value", vo.getTriValue())
			.addValue("tri_flags", vo.getTriFlags())
			.addValue("tri_name", vo.getTriName())
			.addValue("tri_id_auto", vo.getTriId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(CliDataDefTriggerVo vo) {
		return this.createPkMapSqlParameterSource(vo.getTriId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer triId) {
		return new MapSqlParameterSource()
			.addValue("tri_id_auto", triId);
	}

	//--- Public methods ------------------------
	public Collection<CliDataDefTriggerVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, CliDataDefTriggerRowWrapper.getInstance()); }
	public CliDataDefTriggerVo findVo(Integer triId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(triId), CliDataDefTriggerRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(CliDataDefTriggerVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setTriId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(CliDataDefTriggerVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(CliDataDefTriggerVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(CliDataDefTriggerVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case CliDataDefTriggerVo.SYNC_INSERT: this.insert(vo); break;
			case CliDataDefTriggerVo.SYNC_UPDATE: this.update(vo); break;
			case CliDataDefTriggerVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<CliDataDefTriggerVo> vos) {
		if (vos == null) return;
		for (CliDataDefTriggerVo vo : vos) this.synchronize(vo);
}


}

