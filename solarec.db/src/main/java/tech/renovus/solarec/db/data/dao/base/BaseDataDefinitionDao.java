package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

public abstract class BaseDataDefinitionDao <T extends DataDefinitionVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_definition WHERE data_def_id_auto = :data_def_id_auto";
	protected String SQL_INSERT					= "INSERT INTO data_definition (data_def_name, data_def_description, data_def_executable, data_def_flags) VALUES (:data_def_name, :data_def_description, :data_def_executable, :data_def_flags)";
	protected String SQL_UPDATE					= "UPDATE data_definition SET data_def_name = :data_def_name, data_def_description = :data_def_description, data_def_executable = :data_def_executable, data_def_flags = :data_def_flags WHERE data_def_id_auto = :data_def_id_auto";
	protected String SQL_DELETE					= "DELETE FROM data_definition WHERE data_def_id_auto = :data_def_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id_auto) DO UPDATE SET data_def_name = EXCLUDED.data_def_name, data_def_description = EXCLUDED.data_def_description, data_def_executable = EXCLUDED.data_def_executable, data_def_flags = EXCLUDED.data_def_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"data_def_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id_auto", vo.getDataDefId())
			.addValue("data_def_name", vo.getDataDefName())
			.addValue("data_def_description", vo.getDataDefDescription())
			.addValue("data_def_executable", vo.getDataDefExecutable())
			.addValue("data_def_flags", vo.getDataDefFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_name", vo.getDataDefName())
			.addValue("data_def_description", vo.getDataDefDescription())
			.addValue("data_def_executable", vo.getDataDefExecutable())
			.addValue("data_def_flags", vo.getDataDefFlags())
			.addValue("data_def_id_auto", vo.getDataDefId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId) {
		return new MapSqlParameterSource()
			.addValue("data_def_id_auto", dataDefId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataDefinitionRowWrapper.getInstance()); }
	public DataDefinitionVo findVo(Integer dataDefId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId), DataDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setDataDefId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataDefId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
