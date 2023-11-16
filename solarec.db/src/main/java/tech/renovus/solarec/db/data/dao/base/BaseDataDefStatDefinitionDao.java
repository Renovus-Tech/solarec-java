package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefStatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

public abstract class BaseDataDefStatDefinitionDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM data_def_stat_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM data_def_stat_definition WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_INSERT					= "INSERT INTO data_def_stat_definition (data_def_id,stat_def_id,stat_def_call_order) VALUES (:data_def_id,:stat_def_id,:stat_def_call_order)";
	protected String SQL_UPDATE					= "UPDATE data_def_stat_definition SET stat_def_call_order = :stat_def_call_order WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_DELETE					= "DELETE FROM data_def_stat_definition WHERE data_def_id = :data_def_id AND stat_def_id = :stat_def_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (data_def_id, stat_def_id) DO UPDATE SET stat_def_call_order = EXCLUDED.stat_def_call_order";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseDataDefStatDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(DataDefStatDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("stat_def_call_order", vo.getStatDefCallOrder());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(DataDefStatDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_call_order", vo.getStatDefCallOrder())
			.addValue("data_def_id", vo.getDataDefId())
			.addValue("stat_def_id", vo.getStatDefId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(DataDefStatDefinitionVo vo) {
		return this.createPkMapSqlParameterSource(vo.getDataDefId(), vo.getStatDefId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer dataDefId, Integer statDefId) {
		return new MapSqlParameterSource()
			.addValue("data_def_id", dataDefId)
			.addValue("stat_def_id", statDefId);
	}

	//--- Public methods ------------------------
	public Collection<DataDefStatDefinitionVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, DataDefStatDefinitionRowWrapper.getInstance()); }
	public DataDefStatDefinitionVo findVo(Integer dataDefId, Integer statDefId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(dataDefId, statDefId), DataDefStatDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(DataDefStatDefinitionVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(DataDefStatDefinitionVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(DataDefStatDefinitionVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(DataDefStatDefinitionVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case DataDefStatDefinitionVo.SYNC_INSERT: this.insert(vo); break;
			case DataDefStatDefinitionVo.SYNC_UPDATE: this.update(vo); break;
			case DataDefStatDefinitionVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<DataDefStatDefinitionVo> vos) {
		if (vos == null) return;
		for (DataDefStatDefinitionVo vo : vos) this.synchronize(vo);
}


}

