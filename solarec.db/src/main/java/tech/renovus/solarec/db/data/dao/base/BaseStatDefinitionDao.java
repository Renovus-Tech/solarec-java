package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;

public abstract class BaseStatDefinitionDao {

	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_definition WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_INSERT					= "INSERT INTO stat_definition (stat_def_name,stat_def_description,stat_def_executable,stat_def_flags,stat_def_type) VALUES (:stat_def_name,:stat_def_description,:stat_def_executable,:stat_def_flags,:stat_def_type)";
	protected String SQL_UPDATE					= "UPDATE stat_definition SET stat_def_name = :stat_def_name,stat_def_description = :stat_def_description,stat_def_executable = :stat_def_executable,stat_def_flags = :stat_def_flags,stat_def_type = :stat_def_type WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_DELETE					= "DELETE FROM stat_definition WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id_auto) DO UPDATE SET stat_def_name = EXCLUDED.stat_def_name, stat_def_description = EXCLUDED.stat_def_description, stat_def_executable = EXCLUDED.stat_def_executable, stat_def_flags = EXCLUDED.stat_def_flags, stat_def_type = EXCLUDED.stat_def_type";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"stat_def_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc; 
	} 

	//--- Protected methods ---------------------
	private MapSqlParameterSource createInsertMapSqlParameterSource(StatDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_name", vo.getStatDefName())
			.addValue("stat_def_description", vo.getStatDefDescription())
			.addValue("stat_def_executable", vo.getStatDefExecutable())
			.addValue("stat_def_flags", vo.getStatDefFlags())
			.addValue("stat_def_type", vo.getStatDefType());
	}

	private MapSqlParameterSource craeteUpdateMapSqlParameterSource(StatDefinitionVo vo) {
		return new MapSqlParameterSource()
			.addValue("stat_def_name", vo.getStatDefName())
			.addValue("stat_def_description", vo.getStatDefDescription())
			.addValue("stat_def_executable", vo.getStatDefExecutable())
			.addValue("stat_def_flags", vo.getStatDefFlags())
			.addValue("stat_def_type", vo.getStatDefType())
			.addValue("stat_def_id_auto", vo.getStatDefId());
	}

	private MapSqlParameterSource craeteDeleteMapSqlParameterSource(StatDefinitionVo vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId());
	}

	private MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId) {
		return new MapSqlParameterSource()
			.addValue("stat_def_id_auto", statDefId);
	}

	//--- Public methods ------------------------
	public Collection<StatDefinitionVo> findAll() { return this.jdbc.query(SQL_SELECT_ALL, StatDefinitionRowWrapper.getInstance()); }
	public StatDefinitionVo findVo(Integer statDefId) { try { return this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId), StatDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(StatDefinitionVo vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS );
		vo.setStatDefId(Integer.valueOf(holder.getKey().intValue()));
	}

	public void update(StatDefinitionVo vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(StatDefinitionVo vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(StatDefinitionVo vo) {
		if (vo == null) return;
		switch (vo.getSyncType()) {
			case StatDefinitionVo.SYNC_INSERT: this.insert(vo); break;
			case StatDefinitionVo.SYNC_UPDATE: this.update(vo); break;
			case StatDefinitionVo.SYNC_DELETE: this.delete(vo); break;
		}
	}
	public void synchronize(Collection<StatDefinitionVo> vos) {
		if (vos == null) return;
		for (StatDefinitionVo vo : vos) this.synchronize(vo);
}


}

