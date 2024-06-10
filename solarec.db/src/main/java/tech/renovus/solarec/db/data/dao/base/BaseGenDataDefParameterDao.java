package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.GenDataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

public abstract class BaseGenDataDefParameterDao <T extends GenDataDefParameterVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM gen_data_def_parameter";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM gen_data_def_parameter WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_INSERT					= "INSERT INTO gen_data_def_parameter (cli_id, gen_id, data_def_id, data_def_par_id, gen_data_def_par_value) VALUES (:cli_id, :gen_id, :data_def_id, :data_def_par_id, :gen_data_def_par_value)";
	protected String SQL_UPDATE					= "UPDATE gen_data_def_parameter SET gen_data_def_par_value = :gen_data_def_par_value WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_DELETE					= "DELETE FROM gen_data_def_parameter WHERE cli_id = :cli_id AND gen_id = :gen_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, gen_id, data_def_id, data_def_par_id) DO UPDATE SET gen_data_def_par_value = EXCLUDED.gen_data_def_par_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseGenDataDefParameterDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenDataDefParameterVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenDataDefParameterVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, vo.getDataDefParId())
			.addValue(GenDataDefParameterVo.COLUMN_GEN_DATA_DEF_PAR_VALUE, vo.getGenDataDefParValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(GenDataDefParameterVo.COLUMN_GEN_DATA_DEF_PAR_VALUE, vo.getGenDataDefParValue())
			.addValue(GenDataDefParameterVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(GenDataDefParameterVo.COLUMN_GEN_ID, vo.getGenId())
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getGenId(), vo.getDataDefId(), vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId) {
		return new MapSqlParameterSource()
			.addValue(GenDataDefParameterVo.COLUMN_CLI_ID, cliId)
			.addValue(GenDataDefParameterVo.COLUMN_GEN_ID, genId)
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_ID, dataDefId)
			.addValue(GenDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, dataDefParId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, GenDataDefParameterRowWrapper.getInstance()); }
	public GenDataDefParameterVo findVo(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, genId, dataDefId, dataDefParId), GenDataDefParameterRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getGenId(), vo.getDataDefId(), vo.getDataDefParId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
