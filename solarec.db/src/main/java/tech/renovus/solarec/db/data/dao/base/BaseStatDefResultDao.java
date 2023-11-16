package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefResultRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefResultVo;

public abstract class BaseStatDefResultDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_def_result";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_def_result WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_INSERT					= "INSERT INTO stat_def_result (stat_def_id,stat_type_id) VALUES (:stat_def_id,:stat_type_id)";
	protected String SQL_UPDATE					= "UPDATE stat_def_result SET  WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_DELETE					= "DELETE FROM stat_def_result WHERE stat_def_id = :stat_def_id AND stat_type_id = :stat_type_id";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id, stat_type_id) DO UPDATE SET ";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatDefResultDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StatDefResultVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("stat_type_id", vo.getStatTypeId());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StatDefResultVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", vo.getStatDefId())
			.addValue("stat_type_id", vo.getStatTypeId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StatDefResultVo vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId(), vo.getStatTypeId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId, Integer statTypeId) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id", statDefId)
			.addValue("stat_type_id", statTypeId);
	}

	//--- Public methods ------------------------
	public Collection<StatDefResultVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StatDefResultRowWrapper.getInstance()); }
	public StatDefResultVo findVo(Integer statDefId, Integer statTypeId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId, statTypeId), StatDefResultRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StatDefResultVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
	}

	public void update(StatDefResultVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StatDefResultVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StatDefResultVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StatDefResultVo.SYNC_INSERT: this.insert(vo); break;
			case StatDefResultVo.SYNC_UPDATE: this.update(vo); break;
			case StatDefResultVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StatDefResultVo> vos) {
		if (vos == null) return;
		for (StatDefResultVo vo : vos) this.synchronize(vo);
}


}

