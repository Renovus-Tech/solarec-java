package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.LocDataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;

public abstract class BaseLocDataDefParameterDao <T extends LocDataDefParameterVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM loc_data_def_parameter";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM loc_data_def_parameter WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_INSERT					= "INSERT INTO loc_data_def_parameter (cli_id, loc_id, data_def_id, data_def_par_id, loc_data_def_par_value) VALUES (:cli_id, :loc_id, :data_def_id, :data_def_par_id, :loc_data_def_par_value)";
	protected String SQL_UPDATE					= "UPDATE loc_data_def_parameter SET loc_data_def_par_value = :loc_data_def_par_value WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_DELETE					= "DELETE FROM loc_data_def_parameter WHERE cli_id = :cli_id AND loc_id = :loc_id AND data_def_id = :data_def_id AND data_def_par_id = :data_def_par_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (cli_id, loc_id, data_def_id, data_def_par_id) DO UPDATE SET loc_data_def_par_value = EXCLUDED.loc_data_def_par_value";


	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseLocDataDefParameterDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("data_def_par_id", vo.getDataDefParId())
			.addValue("loc_data_def_par_value", vo.getLocDataDefParValue());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("loc_data_def_par_value", vo.getLocDataDefParValue())
			.addValue("cli_id", vo.getCliId())
			.addValue("loc_id", vo.getLocId())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("data_def_par_id", vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getCliId(), vo.getLocId(), vo.getDataDefId(), vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId) {
		return new MapSqlParameterSource()
			.addValue("cli_id", cliId)
			.addValue("loc_id", locId)
			.addValue("data_def_id", dataDefId)
			.addValue("data_def_par_id", dataDefParId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, LocDataDefParameterRowWrapper.getInstance()); }
	public LocDataDefParameterVo findVo(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(cliId, locId, dataDefId, dataDefParId), LocDataDefParameterRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder);
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getCliId(), vo.getLocId(), vo.getDataDefId(), vo.getDataDefParId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
