package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatTypeRowWrapper;
import tech.renovus.solarec.db.data.vo.StatTypeVo;

public abstract class BaseStatTypeDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_type";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_type WHERE stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_type (stat_type_id,stat_type_name,stat_type_unit,stat_type_description) VALUES (:stat_type_id,:stat_type_name,:stat_type_unit,:stat_type_description)";
	protected String SQL_UPDATE					= "UPDATE stat_type SET stat_type_name = :stat_type_name,stat_type_unit = :stat_type_unit,stat_type_description = :stat_type_description WHERE stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_type WHERE stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_type_id) DO UPDATE SET stat_type_name = EXCLUDED.stat_type_name, stat_type_unit = EXCLUDED.stat_type_unit, stat_type_description = EXCLUDED.stat_type_description";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatTypeDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StatTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_type_id", vo.getStatTypeId())
			.addValue("stat_type_name", vo.getStatTypeName())
			.addValue("stat_type_unit", vo.getStatTypeUnit())
			.addValue("stat_type_description", vo.getStatTypeDescription());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StatTypeVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_type_name", vo.getStatTypeName())
			.addValue("stat_type_unit", vo.getStatTypeUnit())
			.addValue("stat_type_description", vo.getStatTypeDescription())
			.addValue("stat_type_id", vo.getStatTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StatTypeVo vo) {
		return this.createPkMapSqlParameterSource(vo.getStatTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue("stat_type_id", statTypeId);
	}

	//--- Public methods ------------------------
	public Collection<StatTypeVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StatTypeRowWrapper.getInstance()); }
	public StatTypeVo findVo(Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statTypeId), StatTypeRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StatTypeVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StatTypeVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StatTypeVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StatTypeVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StatTypeVo.SYNC_INSERT: this.insert(vo); break;
			case StatTypeVo.SYNC_UPDATE: this.update(vo); break;
			case StatTypeVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StatTypeVo> vos) {
		if (vos == null) return;
		for (StatTypeVo vo : vos) this.synchronize(vo);
}


}

