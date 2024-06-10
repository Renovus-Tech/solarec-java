package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliDataDefTriggerRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;

public abstract class BaseCliDataDefTriggerDao <T extends CliDataDefTriggerVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM cli_data_def_trigger";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_data_def_trigger WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_INSERT					= "INSERT INTO cli_data_def_trigger (sta_id, data_def_id, tri_source, cli_id, loc_id, gen_id, tri_name, tri_value, tri_flags) VALUES (:sta_id, :data_def_id, :tri_source, :cli_id, :loc_id, :gen_id, :tri_name, :tri_value, :tri_flags)";
	protected String SQL_UPDATE					= "UPDATE cli_data_def_trigger SET sta_id = :sta_id, data_def_id = :data_def_id, tri_source = :tri_source, cli_id = :cli_id, loc_id = :loc_id, gen_id = :gen_id, tri_name = :tri_name, tri_value = :tri_value, tri_flags = :tri_flags WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_DELETE					= "DELETE FROM cli_data_def_trigger WHERE tri_id_auto = :tri_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (tri_id_auto) DO UPDATE SET sta_id = EXCLUDED.sta_id, data_def_id = EXCLUDED.data_def_id, tri_source = EXCLUDED.tri_source, cli_id = EXCLUDED.cli_id, loc_id = EXCLUDED.loc_id, gen_id = EXCLUDED.gen_id, tri_name = EXCLUDED.tri_name, tri_value = EXCLUDED.tri_value, tri_flags = EXCLUDED.tri_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"tri_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseCliDataDefTriggerDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_ID, vo.getTriId())
			.addValue(CliDataDefTriggerVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(CliDataDefTriggerVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_SOURCE, vo.getTriSource())
			.addValue(CliDataDefTriggerVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliDataDefTriggerVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliDataDefTriggerVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_NAME, vo.getTriName())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_VALUE, vo.getTriValue())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_FLAGS, vo.getTriFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefTriggerVo.COLUMN_STA_ID, vo.getStaId())
			.addValue(CliDataDefTriggerVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_SOURCE, vo.getTriSource())
			.addValue(CliDataDefTriggerVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliDataDefTriggerVo.COLUMN_LOC_ID, vo.getLocId())
			.addValue(CliDataDefTriggerVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_NAME, vo.getTriName())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_VALUE, vo.getTriValue())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_FLAGS, vo.getTriFlags())
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_ID, vo.getTriId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getTriId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer triId) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefTriggerVo.COLUMN_TRI_ID, triId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliDataDefTriggerRowWrapper.getInstance()); }
	public CliDataDefTriggerVo findVo(Integer triId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(triId), CliDataDefTriggerRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setTriId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getTriId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
