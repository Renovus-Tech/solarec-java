package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefSourceRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefSourceVo;

public abstract class BaseStatDefSourceDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_def_source";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_def_source WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_def_source (stat_def_id,data_type_id) VALUES (:stat_def_id,:data_type_id)";
	protected String SQL_UPDATE					= "UPDATE stat_def_source SET  WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_def_source WHERE stat_def_id = :stat_def_id AND data_type_id = :data_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id, data_type_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatDefSourceDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StatDefSourceVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StatDefSourceVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("data_type_id", vo.getDataTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StatDefSourceVo vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId(), vo.getDataTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId, Integer dataTypeId) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", statDefId)
			.addValue("data_type_id", dataTypeId);
	}

	//--- Public methods ------------------------
	public Collection<StatDefSourceVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StatDefSourceRowWrapper.getInstance()); }
	public StatDefSourceVo findVo(Integer statDefId, Integer dataTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId, dataTypeId), StatDefSourceRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StatDefSourceVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StatDefSourceVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StatDefSourceVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StatDefSourceVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StatDefSourceVo.SYNC_INSERT: this.insert(vo); break;
			case StatDefSourceVo.SYNC_UPDATE: this.update(vo); break;
			case StatDefSourceVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StatDefSourceVo> vos) {
		if (vos == null) return;
		for (StatDefSourceVo vo : vos) this.synchronize(vo);
}


}

