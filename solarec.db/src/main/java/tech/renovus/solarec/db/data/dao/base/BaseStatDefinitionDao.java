package tech.renovus.solarec.db.data.dao.base;

import java.util.Collection;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tech.renovus.solarec.db.data.dao.wrapper.StatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;

public abstract class BaseStatDefinitionDao <T extends StatDefinitionVo > {
	//--- Protected constants -------------------
	protected final String SQL_SELECT_ALL		= "SELECT * FROM stat_definition";
	protected final String SQL_SELECT_BY_ID		= "SELECT * FROM stat_definition WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_INSERT					= "INSERT INTO stat_definition (stat_def_type, stat_def_name, stat_def_description, stat_def_executable, stat_def_flags) VALUES (:stat_def_type, :stat_def_name, :stat_def_description, :stat_def_executable, :stat_def_flags)";
	protected String SQL_UPDATE					= "UPDATE stat_definition SET stat_def_type = :stat_def_type, stat_def_name = :stat_def_name, stat_def_description = :stat_def_description, stat_def_executable = :stat_def_executable, stat_def_flags = :stat_def_flags WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_DELETE					= "DELETE FROM stat_definition WHERE stat_def_id_auto = :stat_def_id_auto";
	protected String SQL_ON_CONFLICT_PK_UPDATE	= " ON CONFLICT (stat_def_id_auto) DO UPDATE SET stat_def_type = EXCLUDED.stat_def_type, stat_def_name = EXCLUDED.stat_def_name, stat_def_description = EXCLUDED.stat_def_description, stat_def_executable = EXCLUDED.stat_def_executable, stat_def_flags = EXCLUDED.stat_def_flags";

	protected String[] AUTO_INCREMENT_COLUMNS	= new String[] {"stat_def_id_auto"};

	//--- Protected properties ------------------
	protected NamedParameterJdbcTemplate jdbc;

	//--- Constructors --------------------------
	public BaseStatDefinitionDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//--- Protected methods ---------------------
	protected MapSqlParameterSource createInsertMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_ID, vo.getStatDefId())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_TYPE, vo.getStatDefType())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_NAME, vo.getStatDefName())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_DESCRIPTION, vo.getStatDefDescription())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_EXECUTABLE, vo.getStatDefExecutable())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_FLAGS, vo.getStatDefFlags());
	}
	
	protected MapSqlParameterSource craeteUpdateMapSqlParameterSource(T vo) {
		return new MapSqlParameterSource()
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_TYPE, vo.getStatDefType())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_NAME, vo.getStatDefName())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_DESCRIPTION, vo.getStatDefDescription())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_EXECUTABLE, vo.getStatDefExecutable())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_FLAGS, vo.getStatDefFlags())
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_ID, vo.getStatDefId());
	}
	
	protected MapSqlParameterSource craeteDeleteMapSqlParameterSource(T vo) {
		return this.createPkMapSqlParameterSource(vo.getStatDefId());
	}
	
	protected MapSqlParameterSource createPkMapSqlParameterSource(Integer statDefId) {
		return new MapSqlParameterSource()
			.addValue(StatDefinitionVo.COLUMN_STAT_DEF_ID, statDefId);
	}
	//--- Public methods ------------------------
	public Collection<T> findAll() { return (Collection<T>) this.jdbc.query(SQL_SELECT_ALL, StatDefinitionRowWrapper.getInstance()); }
	public StatDefinitionVo findVo(Integer statDefId) { try { return (T) this.jdbc.queryForObject(SQL_SELECT_BY_ID, this.createPkMapSqlParameterSource(statDefId), StatDefinitionRowWrapper.getInstance()); } catch (EmptyResultDataAccessException e) { return null; } }

	public void insert(T vo) {
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbc.update( SQL_INSERT, this.createInsertMapSqlParameterSource(vo), holder, AUTO_INCREMENT_COLUMNS);
		Number key = holder.getKey();
		if (key != null) vo.setStatDefId(Integer.valueOf(key.intValue()));
	}

	public void update(T vo) { this.jdbc.update(SQL_UPDATE, this.craeteUpdateMapSqlParameterSource(vo)); }
	public void delete(T vo) { this.jdbc.update(SQL_DELETE, this.craeteDeleteMapSqlParameterSource(vo)); }

	public void synchronize(T vo) {
		if (vo == null) return;
	
		if (T.SYNC_INSERT_UPDATE == vo.getSyncType()) vo.setSyncType(this.findVo(vo.getStatDefId()) == null ? T.SYNC_INSERT : T.SYNC_UPDATE);
	
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
