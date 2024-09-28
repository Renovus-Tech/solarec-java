package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.CliDataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;

@javax.annotation.Generated(value = "Renovus") public abstract class BaseCliDataDefParameterDao <T extends CliDataDefParameterVo > {
	//--- Protected constants -------------------
	protected static final String SQL_SELECT_ALL		= "SELECT * FROM cli_data_def_parameter";
	protected static final String SQL_SELECT_BY_ID		= "SELECT * FROM cli_data_def_parameter WHERE cli_id = :cli_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_INSERT					= "INSERT INTO cli_data_def_parameter (cli_id, data_def_id, data_def_par_id, cli_data_def_par_value) VALUES (:cli_id, :data_def_id, :data_def_par_id, :cli_data_def_par_value)";
	protected String SQL_UPDATE					= "UPDATE cli_data_def_parameter SET cli_data_def_par_value = :cli_data_def_par_value WHERE cli_id = :cli_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_DELETE					= "DELETE FROM cli_data_def_parameter WHERE cli_id = :cli_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, data_def_id, data_def_par_id) DO UPDATE SET cli_data_def_par_value = EXCLUDED.cli_data_def_par_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	protected BaseCliDataDefParameterDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefParameterVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, vo.getDataDefParId())
			.addValue(CliDataDefParameterVo.COLUMN_CLI_DATA_DEF_PAR_VALUE, vo.getCliDataDefParValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefParameterVo.COLUMN_CLI_DATA_DEF_PAR_VALUE, vo.getCliDataDefParValue())
			.addValue(CliDataDefParameterVo.COLUMN_CLI_ID, vo.getCliId())
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_ID, vo.getDataDefId())
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getDataDefId(), vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer dataDefId, Integer dataDefParId) {
		return new MapSqlParameterSource()
			.addValue(CliDataDefParameterVo.COLUMN_CLI_ID, cliId)
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_ID, dataDefId)
			.addValue(CliDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID, dataDefParId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, CliDataDefParameterRowWrapper.getInstance()); }
	public CliDataDefParameterVo findVo(Integer cliId, Integer dataDefId, Integer dataDefParId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, dataDefId, dataDefParId), CliDataDefParameterRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getDataDefId(), vo.getDataDefParId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
		switch (vo.getSyncType()) {
			case T.SYNC_INSERT: this.insert(vo); break;
			case T.SYNC_UPDATE: this.update(vo); break;
			case T.SYNC_DELETE: this.delete(vo); break;
			default: 
		}
	}
	public void synchronize(Collection<T> vos) {
		if (vos == null) return;
		for (T vo : vos) this.synchronize(vo);
	}
}
