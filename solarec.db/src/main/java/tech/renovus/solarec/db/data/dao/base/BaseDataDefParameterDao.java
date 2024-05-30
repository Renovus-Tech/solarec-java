package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefParameterRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public abstract class BaseDataDefParameterDao <T extends DataDefParameterVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_def_parameter";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_def_parameter WHERE data_def_id = :data_def_id AND data_def_par_id_auto = :data_def_par_id_auto";
	protected String SQL_INSERT					= "INSERT INTO data_def_parameter (data_def_id, data_def_par_name, data_def_description) VALUES (:data_def_id, :data_def_par_name, :data_def_description)";
	protected String SQL_UPDATE					= "UPDATE data_def_parameter SET data_def_par_name = :data_def_par_name, data_def_description = :data_def_description WHERE data_def_id = :data_def_id AND data_def_par_id_auto = :data_def_par_id_auto";
	protected String SQL_DELETE					= "DELETE FROM data_def_parameter WHERE data_def_id = :data_def_id AND data_def_par_id_auto = :data_def_par_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id, data_def_par_id_auto) DO UPDATE SET data_def_par_name = EXCLUDED.data_def_par_name, data_def_description = EXCLUDED.data_def_description";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"data_def_par_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataDefParameterDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("data_def_par_id_auto", vo.getDataDefParId())
			.addValue("data_def_par_name", vo.getDataDefParName())
			.addValue("data_def_description", vo.getDataDefDescription());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_par_name", vo.getDataDefParName())
			.addValue("data_def_description", vo.getDataDefDescription())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("data_def_par_id_auto", vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId(), vo.getDataDefParId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId, Integer dataDefParId) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", dataDefId)
			.addValue("data_def_par_id_auto", dataDefParId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, DataDefParameterRowWrapper.getInstance()); }
	public DataDefParameterVo findVo(Integer dataDefId, Integer dataDefParId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId, dataDefParId), DataDefParameterRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setDataDefParId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getDataDefId(), vo.getDataDefParId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
